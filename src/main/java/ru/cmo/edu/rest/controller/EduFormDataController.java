package ru.cmo.edu.rest.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.Link;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import ru.cmo.edu.data.dto.EduCoreDto;
import ru.cmo.edu.data.dto.EduFormDataCoreDto;
import ru.cmo.edu.data.dto.EduKindCoreDto;
import ru.cmo.edu.data.dto.MunicipalityCoreDto;
import ru.cmo.edu.data.resource.EduKindResource;
import ru.cmo.edu.data.resource.EduResource;
import ru.cmo.edu.data.resource.MunicipalityResource;
import ru.cmo.edu.rest.security.Role;
import ru.cmo.edu.service.EduKindService;
import ru.cmo.edu.service.EduService;
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
@RequestMapping(value = "/view/eduformdata")
public class EduFormDataController extends BaseController {

    Logger logger = LoggerFactory.getLogger(EduFormDataController.class);

    private final FormDataService formDataService;
    private final MunicipalityService municipalityService;
    private final EduKindService eduKindService;
    private final EduService eduService;

    @Autowired
    public EduFormDataController(FormDataService formDataService, MunicipalityService municipalityService, EduKindService eduKindService, EduService eduService) {
        this.formDataService = formDataService;
        this.municipalityService = municipalityService;
        this.eduKindService = eduKindService;
        this.eduService = eduService;
    }

    @RequestMapping(method = RequestMethod.GET)
    public ResponseEntity index(@RequestParam int id,
                                @RequestParam(required = false, defaultValue = "false") boolean isArchived) {
        Role role = getRole();
        if (role == null) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).build();
        }
        Link navLink = linkTo(EduFormDataController.class).withSelfRel();
        switch(role) {
            case region:
            case ministry:
                navLink = linkTo(methodOn(EduFormDataController.class).getMunicipalityList(id)).withSelfRel();
                break;
            case municipality:
                navLink = linkTo(methodOn(EduFormDataController.class).getEduKindList(id)).withSelfRel();
                break;
            case edu:
                navLink = linkTo(methodOn(EduFormDataController.class).getFormList(id, isArchived)).withSelfRel();
                break;
        }
        return ResponseEntity.ok(navLink);
    }

    @PreAuthorize("hasAnyAuthority('region', 'ministry')")
    @RequestMapping(value = "/municipality", method = RequestMethod.GET)
    public ResponseEntity getMunicipalityList(@RequestParam int regionId) {
        List<MunicipalityCoreDto> dtos = municipalityService.getAllDto(MunicipalityCoreDto.class, regionId);
        List<MunicipalityResource> resources = dtos.stream().map(t ->
        {
            MunicipalityResource resource = new MunicipalityResource(t);
            Link navigateToEduKindLink = linkTo(
                    methodOn(EduFormDataController.class).getEduKindList(t.getId())
            ).withSelfRel();
            resource.add(navigateToEduKindLink);
            return resource;
        }).collect(Collectors.toList());
        return ResponseEntity.ok(resources);
    }

    @PreAuthorize("hasAnyAuthority('region', 'ministry', 'municipality')")
    @RequestMapping(value = "/edukind", method = RequestMethod.GET)
    public ResponseEntity getEduKindList(@RequestParam int municipalityId) {
        List<EduKindCoreDto> dtos = eduKindService.getAllDto(EduKindCoreDto.class, municipalityId);
        List<EduKindResource> resources = dtos.stream().map(t -> {
            EduKindResource resource = new EduKindResource(t);
            Link navigateToEduLink = linkTo(
                    methodOn(EduFormDataController.class).getEduList(municipalityId, t.getId())
            ).withSelfRel();
            resource.add(navigateToEduLink);
            return resource;
        }).collect(Collectors.toList());
        return ResponseEntity.ok(resources);
    }

    @PreAuthorize("hasAnyAuthority('region', 'ministry', 'municipality')")
    @RequestMapping(value = "/edu", method = RequestMethod.GET)
    public ResponseEntity getEduList(@RequestParam int municipalityId, @RequestParam int eduKindId) {
        List<EduCoreDto> dtos = eduService.getAllDto(EduCoreDto.class, municipalityId, eduKindId);
        List<EduResource> resources = dtos.stream().map(t -> {
            EduResource resource = new EduResource(t);
            List<Link> navigateToFormLinks = new ArrayList<>();
            navigateToFormLinks.add(linkTo(methodOn(EduFormDataController.class).getFormList(t.getId(), false)).withRel("actual"));
            navigateToFormLinks.add(linkTo(methodOn(EduFormDataController.class).getFormList(t.getId(), true)).withRel("archived"));
            resource.add(navigateToFormLinks);
            return resource;
        }).collect(Collectors.toList());
        return ResponseEntity.ok(resources);
    }

    @PreAuthorize("hasAnyAuthority('region', 'ministry', 'municipality', 'edu')")
    @RequestMapping(value = "/form", method = RequestMethod.GET)
    public ResponseEntity getFormList(@RequestParam int eduId, @RequestParam boolean isArchived) {
        List<EduFormDataCoreDto> dtos = formDataService.getEduFormDataDto(eduId, isArchived);
        return ResponseEntity.ok(dtos);
    }
}
