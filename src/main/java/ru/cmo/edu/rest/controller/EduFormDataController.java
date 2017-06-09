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

import ru.cmo.edu.rest.json.JsonEduFormDataResponseFactory;

import java.util.Map;

/**
 * Created by to on 05.06.2017.
 */

@RestController
@RequestMapping("/eduformdata")
public class EduFormDataController {

    Logger logger = LoggerFactory.getLogger(EduFormDataController.class);

    @Autowired
    private JsonEduFormDataResponseFactory jsonEduFormDataResponseFactory;

    @RequestMapping(value = "/municipality", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public Map<String, Object> GetMunicipalityList(@RequestParam String viewingBy, @RequestParam int id) {
        switch (viewingBy) {
            case "region": {
                return jsonEduFormDataResponseFactory.createMunicipalityListForRegion(id);
            }
            default:
                return null;
        }
    }

    @RequestMapping(value = "/municipality/edukind", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public Map<String, Object> GetEduKindList(@RequestParam String viewingBy, @RequestParam int id) {
        switch (viewingBy) {
            case "region": {
                return jsonEduFormDataResponseFactory.createEduKindListByMunicipalityForRegion(id);
            }
            default:
                return null;
        }
    }

    @RequestMapping(value = "/municipality/edukind/edu", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public Map<String, Object> GetEduList(@RequestParam String viewingBy, @RequestParam int id, @RequestParam int municipalityId, @RequestParam int eduKindId) {
        switch (viewingBy) {
            case "region": {
                return jsonEduFormDataResponseFactory.createEduListByEduKindForRegion(municipalityId, eduKindId);
            }
            default:
                return null;
        }
    }
}
