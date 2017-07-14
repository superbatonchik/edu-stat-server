package ru.cmo.edu.rest.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.Link;
import org.springframework.hateoas.Resources;
import org.springframework.http.HttpEntity;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import ru.cmo.edu.data.dto.EduCoreDto;
import ru.cmo.edu.data.dto.EduFormDataCoreDto;
import ru.cmo.edu.data.dto.EduKindCoreDto;
import ru.cmo.edu.data.dto.MunicipalityCoreDto;
import ru.cmo.edu.data.entity.Edu;
import ru.cmo.edu.data.entity.EduFormData;
import ru.cmo.edu.data.entity.EduKind;
import ru.cmo.edu.data.entity.Municipality;
import ru.cmo.edu.data.resource.EduKindResource;
import ru.cmo.edu.data.resource.EduResource;
import ru.cmo.edu.data.resource.MunicipalityResource;
import ru.cmo.edu.rest.security.Role;
import ru.cmo.edu.service.FormDataService;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import static org.springframework.hateoas.mvc.ControllerLinkBuilder.linkTo;
import static org.springframework.hateoas.mvc.ControllerLinkBuilder.methodOn;

/**
 * Created by to on 05.06.2017.
 */

@RestController
@RequestMapping(value = "/eduformdata")
public class EduFormDataController {

    Logger logger = LoggerFactory.getLogger(EduFormDataController.class);

    @Autowired
    private FormDataService formDataService;

    @RequestMapping(value = "/municipality", method = RequestMethod.GET)
    public ResponseEntity getMunicipalityList(@RequestParam Role viewingBy, @RequestParam int id) {
        switch (viewingBy) {
            case region: {
                List<Municipality> municipalities = formDataService.getMunicipalities(id);
                List<MunicipalityResource> resources = municipalities.stream().map(t ->
                        {
                            MunicipalityResource resource = new MunicipalityResource(new MunicipalityCoreDto(t));
                            Link navigateToEduKindLink = linkTo(
                                    methodOn(EduFormDataController.class).getEduKindList(viewingBy, t.getId())
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

    @RequestMapping(value = "/municipality/edukind", method = RequestMethod.GET)
    public ResponseEntity getEduKindList(@RequestParam Role viewingBy, @RequestParam int municipalityId) {
        switch (viewingBy) {
            case region: {
                List<EduKind> eduKinds = formDataService.getEduKinds(municipalityId);
                List<EduKindResource> resources = eduKinds.stream().map(t -> {
                    EduKindResource resource = new EduKindResource(new EduKindCoreDto(t));
                    Link navigateToEduLink = linkTo(
                            methodOn(EduFormDataController.class).getEduList(viewingBy, municipalityId, t.getId())
                    ).withSelfRel();
                    resource.add(navigateToEduLink);
                    return resource;
                }).collect(Collectors.toList());
                return ResponseEntity.ok(resources);
            }
            default:
                return ResponseEntity.badRequest().build();
        }
    }

    @RequestMapping(value = "/municipality/edukind/edu", method = RequestMethod.GET)
    public ResponseEntity getEduList(@RequestParam Role viewingBy, @RequestParam int municipalityId, @RequestParam int eduKindId) {
        switch (viewingBy) {
            case region: {
                List<Edu> edus = formDataService.getEdus(municipalityId, eduKindId);
                List<EduResource> resources = edus.stream().map(t -> {
                    EduResource resource = new EduResource(new EduCoreDto(t));
                    List<Link> navigateToFormLinks = new ArrayList<Link>() {
                        { add(linkTo(methodOn(EduFormDataController.class).getEduFormList(viewingBy, t.getId(), false)).withRel("actual")); }
                        { add(linkTo(methodOn(EduFormDataController.class).getEduFormList(viewingBy, t.getId(), true)).withRel("archived")); }
                    };
                    resource.add(navigateToFormLinks);
                    return resource;
                }).collect(Collectors.toList());
                return ResponseEntity.ok(resources);
            }
            default:
                return ResponseEntity.badRequest().build();
        }
    }

    @RequestMapping(value = "/municipality/edukind/edu/form", method = RequestMethod.GET)
    public ResponseEntity getEduFormList(@RequestParam Role viewingBy, @RequestParam int eduId, @RequestParam boolean isArchived) {
        List<EduFormData> eduFormDatas = formDataService.getEduFormDatas(eduId, isArchived);
        List<EduFormDataCoreDto> formDataDtos = eduFormDatas.stream().map(EduFormDataCoreDto::new).collect(Collectors.toList());
        return ResponseEntity.ok(formDataDtos);
    }
}
