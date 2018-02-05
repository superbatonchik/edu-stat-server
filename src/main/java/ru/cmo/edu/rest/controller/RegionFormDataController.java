package ru.cmo.edu.rest.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.hateoas.Link;
import org.springframework.hateoas.LinkBuilder;
import org.springframework.hateoas.core.LinkBuilderSupport;
import org.springframework.hateoas.mvc.ControllerLinkBuilder;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import ru.cmo.edu.data.dto.RegionCoreDto;
import ru.cmo.edu.data.dto.RegionFormDataCoreDto;
import ru.cmo.edu.data.entity.enumerable.FormTypeEnum;
import ru.cmo.edu.data.resource.BaseResource;
import ru.cmo.edu.data.resource.FormDataResource;
import ru.cmo.edu.data.resource.RegionResource;
import ru.cmo.edu.rest.security.Role;
import ru.cmo.edu.service.FormDataService;
import ru.cmo.edu.service.RegionService;

import java.text.MessageFormat;
import java.text.SimpleDateFormat;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import static org.springframework.hateoas.mvc.ControllerLinkBuilder.linkTo;
import static org.springframework.hateoas.mvc.ControllerLinkBuilder.methodOn;

/**
 * Created by to on 05.06.2017.
 */

@RestController
@RequestMapping(value = "/view/regionformdata")
public class RegionFormDataController extends BaseController {

    Logger logger = LoggerFactory.getLogger(RegionFormDataController.class);

    private final FormDataService formDataService;
    private final RegionService regionService;

    public RegionFormDataController(FormDataService formDataService, RegionService regionService) {
        this.formDataService = formDataService;
        this.regionService = regionService;
    }

    @PreAuthorize("hasAnyAuthority('region', 'ministry')")
    @RequestMapping(method = RequestMethod.GET)
    public ResponseEntity index(@RequestParam int id) {
        Role role = getRole();
        if (role == null) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).build();
        }
        List<BaseResource> resources = new ArrayList<>();
        switch(role) {
            case ministry: {
                BaseResource resource = new BaseResource();
                resource.add(linkTo(methodOn(RegionFormDataController.class).getRegionList(FormTypeEnum.REGION, false)).withRel("current"));
                resource.setLinkCaption(strings.get("title.region"));
                resources.add(resource);

                BaseResource resourceArchive = new BaseResource();
                resourceArchive.add(linkTo(methodOn(RegionFormDataController.class).getRegionList(FormTypeEnum.REGION, true)).withRel("archive"));
                resourceArchive.setLinkCaption(strings.get("title.region"));
                resourceArchive.setLinkSubCaption(strings.get("title.archive"));
                resources.add(resourceArchive);

                BaseResource resourceAdditional = new BaseResource();
                resourceAdditional.add(linkTo(methodOn(RegionFormDataController.class).getRegionList(FormTypeEnum.ADD_REGION, false)).withRel("additional"));
                resourceAdditional.setLinkCaption(strings.get("title.region"));
                resourceAdditional.setLinkSubCaption(strings.get("title.additional"));
                resources.add(resourceAdditional);

                BaseResource resourceAdditionalArchive = new BaseResource();
                resourceAdditionalArchive.add(linkTo(methodOn(RegionFormDataController.class).getRegionList(FormTypeEnum.ADD_REGION, true)).withRel("additional-archive"));
                resourceAdditionalArchive.setLinkCaption(strings.get("title.region"));
                resourceAdditionalArchive.setLinkSubCaption(strings.get("title.archive") + " " + strings.get("title.additional"));
                resources.add(resourceAdditionalArchive);
                break;
            }
            case region: {
                BaseResource resource = new BaseResource();
                resource.add(linkTo(methodOn(RegionFormDataController.class).getFormList(id, FormTypeEnum.REGION, false)).withRel("current"));
                resource.setLinkCaption(strings.get("title.region-form"));
                resources.add(resource);

                BaseResource resourceArchive = new BaseResource();
                resourceArchive.add(linkTo(methodOn(RegionFormDataController.class).getFormList(id, FormTypeEnum.REGION, true)).withRel("archive"));
                resourceArchive.setLinkCaption(strings.get("title.archive-region-form"));
                resources.add(resourceArchive);

                BaseResource resourceAdditional = new BaseResource();
                resourceAdditional.add(linkTo(methodOn(RegionFormDataController.class).getFormList(id, FormTypeEnum.ADD_REGION, false)).withRel("additional"));
                resourceAdditional.setLinkCaption(strings.get("title.add-region-form"));
                resources.add(resourceAdditional);

                BaseResource resourceAdditionalArchive = new BaseResource();
                resourceAdditionalArchive.add(linkTo(methodOn(RegionFormDataController.class).getFormList(id, FormTypeEnum.ADD_REGION, true)).withRel("additional-archive"));
                resourceAdditionalArchive.setLinkCaption(strings.get("title.archive-add-region-form"));
                resources.add(resourceAdditionalArchive);
                break;
            }
            case municipality:
            case edu:
                throw new AccessDeniedException("Edu, municipality can not access region forms");
        }
        return ResponseEntity.ok(resources);
    }

    @PreAuthorize("hasAnyAuthority('region','ministry')")
    @RequestMapping(value = "/region", method = RequestMethod.GET)
    public ResponseEntity getRegionList(@RequestParam int formTypeId,
                                        @RequestParam boolean isArchived) {
        List<RegionCoreDto> dtos = regionService.getAllDto(RegionCoreDto.class);
        List<RegionResource> resources = dtos.stream().map(t ->
        {
            RegionResource resource = new RegionResource(t);
            resource.add(linkTo(methodOn(RegionFormDataController.class).getFormList(t.getId(), formTypeId, isArchived)).withSelfRel());
            String caption = isArchived ? strings.get("title.archive-region-form") : strings.get("title.region-form");
            if (formTypeId == FormTypeEnum.ADD_REGION) {
                caption = isArchived ? strings.get("title.archive-add-region-form") : strings.get("title.add-region-form");
            }
            resource.setLinkCaption(caption);
            resource.setLinkSubCaption(t.getName());
            return resource;
        }).collect(Collectors.toList());
        return ResponseEntity.ok(resources);
    }

    @PreAuthorize("hasAnyAuthority('region', 'ministry')")
    @RequestMapping(value = "/form", method = RequestMethod.GET)
    public ResponseEntity getFormList(@RequestParam int regionId,
                                      @RequestParam int formTypeId,
                                      @RequestParam boolean isArchived) {
        List<RegionFormDataCoreDto> dtos = formDataService.getRegionFormDataDto(regionId, formTypeId, isArchived);
        RegionCoreDto regionDto = regionService.getDto(regionId, RegionCoreDto.class);
        List<FormDataResource> resources = dtos.stream().map(t -> {
            FormDataResource resource = new FormDataResource(t);
            resource.add(linkTo(RegionFormDataController.class).slash("link_to_file?id=" + t.getFileId()).withRel("file"));
            resource.setLinkCaption(regionDto.getName());
            resource.setLinkSubCaption(MessageFormat.format("{0}, {1}", t.getForm().getName(), DateTimeFormatter.ofPattern("d MMMM yyyy HH:mm").format(t.getSendDate())));
            return resource;
        }).collect(Collectors.toList());
        return ResponseEntity.ok(resources);
    }
}
