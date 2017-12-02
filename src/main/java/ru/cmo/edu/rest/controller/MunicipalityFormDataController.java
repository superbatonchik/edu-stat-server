package ru.cmo.edu.rest.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.Link;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import ru.cmo.edu.data.dto.*;
import ru.cmo.edu.data.resource.MunicipalityResource;
import ru.cmo.edu.rest.security.Role;
import ru.cmo.edu.service.FormDataService;
import ru.cmo.edu.service.MunicipalityService;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import static org.springframework.hateoas.mvc.ControllerLinkBuilder.linkTo;
import static org.springframework.hateoas.mvc.ControllerLinkBuilder.methodOn;

/**
 * Created by to on 05.06.2017.
 */

@RestController
@RequestMapping(value = "/view/municipalityformdata")
public class MunicipalityFormDataController extends BaseController {

    Logger logger = LoggerFactory.getLogger(MunicipalityFormDataController.class);

    private final FormDataService formDataService;
    private final MunicipalityService municipalityService;

    @Autowired
    public MunicipalityFormDataController(FormDataService formDataService, MunicipalityService municipalityService) {
        this.formDataService = formDataService;
        this.municipalityService = municipalityService;
    }

    @PreAuthorize("hasAnyRole('region', 'ministry', 'municipality')")
    @RequestMapping(method = RequestMethod.GET)
    public ResponseEntity index(@RequestParam int id,
                                @RequestParam(required = false, defaultValue = "false") boolean isArchived) {
        Role role = getRole();
        if (role == null) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).build();
        }
        Link navLink = linkTo(MunicipalityFormDataController.class).withSelfRel();
        switch(role) {
            case region:
            case ministry:
                navLink = linkTo(methodOn(MunicipalityFormDataController.class).getMunicipalityList(id)).withSelfRel();
                break;
            case municipality:
                navLink = linkTo(methodOn(MunicipalityFormDataController.class).getFormList(id, isArchived)).withSelfRel();
                break;
            case edu:
                throw new AccessDeniedException("Edu can not access municipality forms");
        }
        return ResponseEntity.ok(navLink);
    }

    @PreAuthorize("hasAnyRole('region', 'ministry')")
    @RequestMapping(value = "/municipality", method = RequestMethod.GET)
    public ResponseEntity getMunicipalityList(@RequestParam int regionId) {
        List<MunicipalityCoreDto> dtos = municipalityService.getAllDto(MunicipalityCoreDto.class, regionId);
        List<MunicipalityResource> resources = dtos.stream().map(t ->
        {
            MunicipalityResource resource = new MunicipalityResource(t);
            List<Link> navigateToFormLinks = new ArrayList<>();
            navigateToFormLinks.add(linkTo(methodOn(MunicipalityFormDataController.class).getFormList(t.getId(), false)).withRel("actual"));
            navigateToFormLinks.add(linkTo(methodOn(MunicipalityFormDataController.class).getFormList(t.getId(), true)).withRel("archived"));
            resource.add(navigateToFormLinks);
            return resource;
        }).collect(Collectors.toList());
        return ResponseEntity.ok(resources);
    }

    @PreAuthorize("hasAnyRole('region', 'ministry', 'municipality')")
    @RequestMapping(value = "/form", method = RequestMethod.GET)
    public ResponseEntity getFormList(@RequestParam int municipalityId,
                                      @RequestParam boolean isArchived) {
        List<MunicipalityFormDataCoreDto> dtos = formDataService.getMunicipalityFormDataDto(municipalityId, isArchived);
        return ResponseEntity.ok(dtos);
    }
}
