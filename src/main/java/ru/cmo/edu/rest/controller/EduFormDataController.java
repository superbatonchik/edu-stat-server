package ru.cmo.edu.rest.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.User;
import org.springframework.web.bind.annotation.*;

import ru.cmo.edu.data.entity.Edu;
import ru.cmo.edu.data.entity.EduKind;
import ru.cmo.edu.data.entity.Municipality;
import ru.cmo.edu.rest.json.JsonEduFormDataResponseFactory;
import ru.cmo.edu.service.FormDataService;

import java.util.List;
import java.util.Map;

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
    public ResponseEntity<List<Municipality>> GetMunicipalityList(@RequestParam String viewingBy, @RequestParam int id) {
        switch (viewingBy) {
            case "region": {
                return ResponseEntity.ok(formDataService.getMunicipalities(id));
            }
            default:
                return ResponseEntity.badRequest().build();
        }
    }

    @RequestMapping(value = "/municipality/edukind", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public ResponseEntity<List<EduKind>> GetEduKindList(@RequestParam String viewingBy, @RequestParam int municipalityId) {
        switch (viewingBy) {
            case "region": {
                return ResponseEntity.ok(formDataService.getEduKinds(municipalityId));
            }
            default:
                return ResponseEntity.badRequest().build();
        }
    }

    @RequestMapping(value = "/municipality/edukind/edu", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public ResponseEntity<List<Edu>> GetEduList(@RequestParam String viewingBy, @RequestParam int id, @RequestParam int municipalityId, @RequestParam int eduKindId) {
        switch (viewingBy) {
            case "region": {
                return ResponseEntity.ok(formDataService.getEdus(municipalityId, eduKindId));
            }
            default:
                return ResponseEntity.badRequest().build();
        }
    }
}
