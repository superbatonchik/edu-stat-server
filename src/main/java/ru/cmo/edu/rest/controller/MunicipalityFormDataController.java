package ru.cmo.edu.rest.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.Link;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import ru.cmo.edu.data.dto.EduCoreDto;
import ru.cmo.edu.data.dto.EduFormDataCoreDto;
import ru.cmo.edu.data.dto.EduKindCoreDto;
import ru.cmo.edu.data.dto.MunicipalityCoreDto;
import ru.cmo.edu.data.resource.EduKindResource;
import ru.cmo.edu.data.resource.EduResource;
import ru.cmo.edu.data.resource.MunicipalityResource;
import ru.cmo.edu.rest.security.Role;
import ru.cmo.edu.service.FormDataService;
import ru.cmo.edu.service.MunicipalityService;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import static org.springframework.hateoas.mvc.ControllerLinkBuilder.linkTo;
import static org.springframework.hateoas.mvc.ControllerLinkBuilder.methodOn;

@RestController
@RequestMapping(value = "/view/municipalityformdata")
public class MunicipalityFormDataController {

    Logger logger = LoggerFactory.getLogger(MunicipalityFormDataController.class);

    @Autowired
    private FormDataService formDataService;
    @Autowired
    private MunicipalityService municipalityService;

    @PreAuthorize("hasAnyRole('admin', 'ministry', 'municipality')")
    @RequestMapping(method = RequestMethod.GET)
    public ResponseEntity index(@RequestParam int id,
                                @RequestParam(required = false, defaultValue = "false") boolean isArchived) {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        Role role = null;
        for (GrantedAuthority authority : auth.getAuthorities()) {
            try {
                role = Role.valueOf(authority.getAuthority());
                break;
            } catch (IllegalArgumentException e) {}
        }
        if (role == null) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).build();
        }
        Link navLink = linkTo(EduFormDataController.class).withSelfRel();
        switch(role) {
            case region:
            case ministry:
                navLink = linkTo(methodOn(MunicipalityFormDataController.class).getMunicipalityList(id)).withSelfRel();
                break;
            case municipality:
                navLink = linkTo(methodOn(MunicipalityFormDataController.class).getFormList(id)).withSelfRel();
                break;
        }
        return ResponseEntity.ok(navLink);
    }

    @RequestMapping(value = "/municipality", method = RequestMethod.GET)
    public ResponseEntity getMunicipalityList(@RequestParam Role viewingBy, @RequestParam int id) {
        switch (viewingBy) {
            case region: {
                List<MunicipalityCoreDto> dtos = municipalityService.getAllDto(MunicipalityCoreDto.class, id);
                List<MunicipalityResource> resources = dtos.stream().map(t ->
                {
                    MunicipalityResource resource = new MunicipalityResource(t);
                    Link navigateToEduKindLink = linkTo(
                            methodOn(MunicipalityFormDataController.class).getEduKindList(viewingBy, t.getId())
                    ).withSelfRel();
                    resource.add(navigateToEduKindLink);
                    return resource;
                }).collect(Collectors.toList());
                return ResponseEntity.ok(resources);
            }
            default:
                return null;
        }
    }

    @RequestMapping(value = "/form", method = RequestMethod.GET)
    public ResponseEntity getFormList(@RequestParam int municipalityId, @RequestParam boolean isArchived) {
        List<EduFormDataCoreDto> dtos = formDataService.getMun(eduId, isArchived);
        return ResponseEntity.ok(dtos);
    }
}
