package ru.cmo.edu.rest.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.Link;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import ru.cmo.edu.data.dto.MunicipalityCoreDto;
import ru.cmo.edu.data.entity.Edu;
import ru.cmo.edu.data.entity.EduKind;
import ru.cmo.edu.data.entity.Municipality;
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
@RequestMapping("/eduformdata")
public class EduFormDataController {

    Logger logger = LoggerFactory.getLogger(EduFormDataController.class);

    @Autowired
    private FormDataService formDataService;

    @RequestMapping(value = "/municipality", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public ResponseEntity getMunicipalityList(@RequestParam Role viewingBy, @RequestParam int id) {
        switch (viewingBy) {
            case REGION: {
                List<Municipality> municipalities = formDataService.getMunicipalities(id);
                List<MunicipalityResource> resources = municipalities.stream().map(t ->
                        {
                            MunicipalityResource resource = new MunicipalityResource(new MunicipalityCoreDto(t));
                            Link navigationLink = linkTo(
                                    methodOn(EduFormDataController.class).getEduKindList(viewingBy, t.getId())
                            ).withSelfRel();
                            resource.add(navigationLink);
                            return resource;
                        }).collect(Collectors.toList());
                return ResponseEntity.ok(resources);
            }
            default:
                return ResponseEntity.badRequest().build();
        }
    }

    @RequestMapping(value = "/municipality/edukind", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public ResponseEntity<List<EduKind>> getEduKindList(@RequestParam Role viewingBy, @RequestParam int municipalityId) {
        switch (viewingBy) {
            case REGION: {
                return ResponseEntity.ok(formDataService.getEduKinds(municipalityId));
            }
            default:
                return ResponseEntity.badRequest().build();
        }
    }

    @RequestMapping(value = "/municipality/edukind/edu", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public ResponseEntity<List<Edu>> getEduList(@RequestParam Role viewingBy, @RequestParam int id, @RequestParam int municipalityId, @RequestParam int eduKindId) {
        switch (viewingBy) {
            case REGION: {
                return ResponseEntity.ok(formDataService.getEdus(municipalityId, eduKindId));
            }
            default:
                return ResponseEntity.badRequest().build();
        }
    }
}
