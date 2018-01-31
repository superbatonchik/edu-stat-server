package ru.cmo.edu.rest.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import ru.cmo.edu.data.dto.EduCoreDto;
import ru.cmo.edu.data.dto.EduFormDataCoreDto;
import ru.cmo.edu.data.dto.EduKindCoreDto;
import ru.cmo.edu.data.dto.MunicipalityCoreDto;
import ru.cmo.edu.data.entity.enumerable.FormTypeEnum;
import ru.cmo.edu.data.resource.BaseResource;
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
                resource.add(linkTo(methodOn(EduFormDataController.class).getMunicipalityList(id, FormTypeEnum.EDU, false)).withRel("current"));
                resource.setLinkCaption(strings.get("title.municipality"));
                resources.add(resource);

                BaseResource resourceArchive = new BaseResource();
                resourceArchive.add(linkTo(methodOn(EduFormDataController.class).getMunicipalityList(id, FormTypeEnum.EDU, true)).withRel("archive"));
                resourceArchive.setLinkCaption(strings.get("title.municipality"));
                resourceArchive.setLinkSubCaption(strings.get("title.archive"));
                resources.add(resourceArchive);

                BaseResource resourceAdditional = new BaseResource();
                resourceAdditional.add(linkTo(methodOn(EduFormDataController.class).getMunicipalityList(id, FormTypeEnum.ADD_EDU, false)).withRel("additional"));
                resourceAdditional.setLinkCaption(strings.get("title.municipality"));
                resourceAdditional.setLinkSubCaption(strings.get("title.additional"));
                resources.add(resourceAdditional);

                BaseResource resourceAdditionalArchive = new BaseResource();
                resourceAdditionalArchive.add(linkTo(methodOn(EduFormDataController.class).getMunicipalityList(id, FormTypeEnum.ADD_EDU, true)).withRel("additional-archive"));
                resourceAdditionalArchive.setLinkCaption(strings.get("title.municipality"));
                resourceAdditionalArchive.setLinkSubCaption(strings.get("title.archive") + " " + strings.get("title.additional"));
                resources.add(resourceAdditionalArchive);

                break;
            }
            case municipality: {
                BaseResource resource = new BaseResource();
                resource.add(linkTo(methodOn(EduFormDataController.class).getEduKindList(id, FormTypeEnum.EDU, false)).withRel("current"));
                resource.setLinkCaption(strings.get("title.edu-kind"));
                resources.add(resource);

                BaseResource resourceArchive = new BaseResource();
                resourceArchive.add(linkTo(methodOn(EduFormDataController.class).getEduKindList(id, FormTypeEnum.EDU, true)).withRel("archive"));
                resourceArchive.setLinkCaption(strings.get("title.edu-kind"));
                resourceArchive.setLinkSubCaption(strings.get("title.archive"));
                resources.add(resourceArchive);

                BaseResource resourceAdditional = new BaseResource();
                resourceAdditional.add(linkTo(methodOn(EduFormDataController.class).getEduKindList(id, FormTypeEnum.ADD_EDU, false)).withRel("additional"));
                resourceAdditional.setLinkCaption(strings.get("title.edu-kind"));
                resourceAdditional.setLinkSubCaption(strings.get("title.additional"));
                resources.add(resourceAdditional);

                BaseResource resourceAdditionalArchive = new BaseResource();
                resourceAdditionalArchive.add(linkTo(methodOn(EduFormDataController.class).getEduKindList(id, FormTypeEnum.ADD_EDU, true)).withRel("additional-archive"));
                resourceAdditionalArchive.setLinkCaption(strings.get("title.edu-kind"));
                resourceAdditionalArchive.setLinkSubCaption(strings.get("title.archive") + " " + strings.get("title.additional"));
                resources.add(resourceAdditionalArchive);

                break;
            }
            case edu: {
                BaseResource resource = new BaseResource();
                resource.add(linkTo(methodOn(EduFormDataController.class).getFormList(id, FormTypeEnum.EDU, false)).withRel("current"));
                resource.setLinkCaption(strings.get("title.edu-form"));
                resources.add(resource);

                BaseResource resourceArchive = new BaseResource();
                resourceArchive.add(linkTo(methodOn(EduFormDataController.class).getFormList(id, FormTypeEnum.EDU, true)).withRel("archive"));
                resourceArchive.setLinkCaption(strings.get("title.archive-edu-form"));
                resources.add(resourceArchive);

                BaseResource resourceAdditional = new BaseResource();
                resourceAdditional.add(linkTo(methodOn(EduFormDataController.class).getFormList(id, FormTypeEnum.ADD_EDU, false)).withRel("additional"));
                resourceAdditional.setLinkCaption(strings.get("title.add-edu-form"));
                resources.add(resourceAdditional);

                BaseResource resourceAdditionalArchive = new BaseResource();
                resourceAdditionalArchive.add(linkTo(methodOn(EduFormDataController.class).getFormList(id, FormTypeEnum.ADD_EDU, true)).withRel("additional-archive"));
                resourceAdditionalArchive.setLinkCaption(strings.get("title.archive-add-edu-form"));
                resources.add(resourceAdditionalArchive);

                break;
            }
        }
        return ResponseEntity.ok(resources);
    }

    @PreAuthorize("hasAnyAuthority('region', 'ministry')")
    @RequestMapping(value = "/municipality", method = RequestMethod.GET)
    public ResponseEntity getMunicipalityList(@RequestParam int regionId, @RequestParam int formTypeId, @RequestParam boolean isArchived) {
        List<MunicipalityCoreDto> dtos = municipalityService.getAllDto(MunicipalityCoreDto.class, regionId);
        List<MunicipalityResource> resources = dtos.stream().map(t ->
        {
            MunicipalityResource resource = new MunicipalityResource(t);
            resource.add(linkTo(methodOn(EduFormDataController.class).getEduKindList(t.getId(), formTypeId, isArchived)).withSelfRel());
            resource.setLinkCaption(strings.get("title.edu-kind"));
            resource.setLinkSubCaption(t.getName());
            return resource;
        }).collect(Collectors.toList());
        return ResponseEntity.ok(resources);
    }

    @PreAuthorize("hasAnyAuthority('region', 'ministry', 'municipality')")
    @RequestMapping(value = "/edukind", method = RequestMethod.GET)
    public ResponseEntity getEduKindList(@RequestParam int municipalityId, @RequestParam int formTypeId, @RequestParam boolean isArchived) {
        List<EduKindCoreDto> dtos = eduKindService.getAllDto(EduKindCoreDto.class, municipalityId);
        List<EduKindResource> resources = dtos.stream().map(t -> {
            EduKindResource resource = new EduKindResource(t);
            resource.add(linkTo(methodOn(EduFormDataController.class).getEduList(municipalityId, t.getId(), formTypeId, isArchived)).withSelfRel());
            resource.setLinkCaption(strings.get("title.edu"));
            resource.setLinkSubCaption(t.getName());
            return resource;
        }).collect(Collectors.toList());
        return ResponseEntity.ok(resources);
    }

    @PreAuthorize("hasAnyAuthority('region', 'ministry', 'municipality')")
    @RequestMapping(value = "/edu", method = RequestMethod.GET)
    public ResponseEntity getEduList(@RequestParam int municipalityId, @RequestParam int eduKindId, @RequestParam int formTypeId, @RequestParam boolean isArchived) {
        List<EduCoreDto> dtos = eduService.getAllDto(EduCoreDto.class, municipalityId, eduKindId);
        List<EduResource> resources = dtos.stream().map(t -> {
            EduResource resource = new EduResource(t);
            resource.add(linkTo(methodOn(EduFormDataController.class).getFormList(t.getId(), formTypeId, isArchived)).withSelfRel());
            String caption = isArchived ? strings.get("title.archive-edu-form") : strings.get("title.edu-form");
            if (formTypeId == FormTypeEnum.ADD_EDU) {
                caption = isArchived ? strings.get("title.archive-add-edu-form") : strings.get("title.add-edu-form");
            }
            resource.setLinkCaption(caption);
            resource.setLinkSubCaption(t.getSysName());
            return resource;
        }).collect(Collectors.toList());
        return ResponseEntity.ok(resources);
    }

    @PreAuthorize("hasAnyAuthority('region', 'ministry', 'municipality', 'edu')")
    @RequestMapping(value = "/form", method = RequestMethod.GET)
    public ResponseEntity getFormList(@RequestParam int eduId, @RequestParam int formTypeId, @RequestParam boolean isArchived) {
        List<EduFormDataCoreDto> dtos = formDataService.getEduFormDataDto(eduId, formTypeId, isArchived);
        return ResponseEntity.ok(dtos);
    }
}
