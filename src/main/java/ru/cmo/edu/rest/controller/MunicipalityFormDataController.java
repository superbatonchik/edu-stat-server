package ru.cmo.edu.rest.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import ru.cmo.edu.data.dto.MunicipalityCoreDto;
import ru.cmo.edu.data.dto.MunicipalityFormDataCoreDto;
import ru.cmo.edu.data.entity.enumerable.FormTypeEnum;
import ru.cmo.edu.data.resource.BaseResource;
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

    @PreAuthorize("hasAnyAuthority('region', 'ministry', 'municipality')")
    @RequestMapping(method = RequestMethod.GET)
    public ResponseEntity index(@RequestParam int id) {
        Role role = getRole();
        if (role == null) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).build();
        }
        List<BaseResource> resources = new ArrayList<>();
        switch(role) {
            case region:
            case ministry: {
                BaseResource resource = new BaseResource();
                resource.add(linkTo(methodOn(MunicipalityFormDataController.class).getMunicipalityList(id, FormTypeEnum.MUNICIPALITY, false)).withRel("current"));
                resource.setLinkCaption(strings.get("title.municipality"));
                resources.add(resource);

                BaseResource resourceArchive = new BaseResource();
                resourceArchive.add(linkTo(methodOn(MunicipalityFormDataController.class).getMunicipalityList(id, FormTypeEnum.MUNICIPALITY, true)).withRel("archive"));
                resourceArchive.setLinkCaption(strings.get("title.municipality"));
                resourceArchive.setLinkSubCaption(strings.get("title.archive"));
                resources.add(resourceArchive);

                BaseResource resourceAdditional = new BaseResource();
                resourceAdditional.add(linkTo(methodOn(MunicipalityFormDataController.class).getMunicipalityList(id, FormTypeEnum.ADD_MUNICIPALITY, false)).withRel("additional"));
                resourceAdditional.setLinkCaption(strings.get("title.municipality"));
                resourceAdditional.setLinkSubCaption(strings.get("title.additional"));
                resources.add(resourceAdditional);

                BaseResource resourceAdditionalArchive = new BaseResource();
                resourceAdditionalArchive.add(linkTo(methodOn(MunicipalityFormDataController.class).getMunicipalityList(id, FormTypeEnum.ADD_MUNICIPALITY, true)).withRel("additional-archive"));
                resourceAdditionalArchive.setLinkCaption(strings.get("title.municipality"));
                resourceAdditionalArchive.setLinkSubCaption(strings.get("title.archive") + " " + strings.get("title.additional"));
                resources.add(resourceAdditionalArchive);
                break;
            }
            case municipality: {
                BaseResource resource = new BaseResource();
                resource.add(linkTo(methodOn(MunicipalityFormDataController.class).getFormList(id, FormTypeEnum.MUNICIPALITY, false)).withRel("current"));
                resource.setLinkCaption(strings.get("title.municipality-form"));
                resources.add(resource);

                BaseResource resourceArchive = new BaseResource();
                resourceArchive.add(linkTo(methodOn(MunicipalityFormDataController.class).getFormList(id, FormTypeEnum.MUNICIPALITY, true)).withRel("archive"));
                resourceArchive.setLinkCaption(strings.get("title.archive-municipality-form"));
                resources.add(resourceArchive);

                BaseResource resourceAdditional = new BaseResource();
                resourceAdditional.add(linkTo(methodOn(MunicipalityFormDataController.class).getFormList(id, FormTypeEnum.ADD_MUNICIPALITY, false)).withRel("additional"));
                resourceAdditional.setLinkCaption(strings.get("title.add-municipality-form"));
                resources.add(resourceAdditional);

                BaseResource resourceAdditionalArchive = new BaseResource();
                resourceAdditionalArchive.add(linkTo(methodOn(EduFormDataController.class).getFormList(id, FormTypeEnum.ADD_MUNICIPALITY, true)).withRel("additional-archive"));
                resourceAdditionalArchive.setLinkCaption(strings.get("title.archive-municipality-edu-form"));
                resources.add(resourceAdditionalArchive);
                break;
            }
            case edu:
                throw new AccessDeniedException("Edu can not access municipality forms");
        }
        return ResponseEntity.ok(resources);
    }

    @PreAuthorize("hasAnyAuthority('region', 'ministry')")
    @RequestMapping(value = "/municipality", method = RequestMethod.GET)
    public ResponseEntity getMunicipalityList(@RequestParam int regionId,
                                              @RequestParam int formTypeId,
                                              @RequestParam boolean isArchived) {
        List<MunicipalityCoreDto> dtos = municipalityService.getAllDto(MunicipalityCoreDto.class, regionId);
        List<MunicipalityResource> resources = dtos.stream().map(t ->
        {
            MunicipalityResource resource = new MunicipalityResource(t);
            resource.add(linkTo(methodOn(MunicipalityFormDataController.class).getFormList(t.getId(), formTypeId, isArchived)).withSelfRel());
            String caption = isArchived ? strings.get("title.archive-municipality-form") : strings.get("title.municipality-form");
            if (formTypeId == FormTypeEnum.ADD_MUNICIPALITY) {
                caption = isArchived ? strings.get("title.archive-add-municipality-form") : strings.get("title.add-municipality-form");
            }
            resource.setLinkCaption(caption);
            resource.setLinkSubCaption(t.getName());
            return resource;
        }).collect(Collectors.toList());
        return ResponseEntity.ok(resources);
    }

    @PreAuthorize("hasAnyAuthority('region', 'ministry', 'municipality')")
    @RequestMapping(value = "/form", method = RequestMethod.GET)
    public ResponseEntity getFormList(@RequestParam int municipalityId,
                                      @RequestParam int formTypeId,
                                      @RequestParam boolean isArchived) {
        List<MunicipalityFormDataCoreDto> dtos = formDataService.getMunicipalityFormDataDto(municipalityId, formTypeId, isArchived);
        return ResponseEntity.ok(dtos);
    }
}
