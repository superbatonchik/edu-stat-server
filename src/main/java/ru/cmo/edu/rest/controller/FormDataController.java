package ru.cmo.edu.rest.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import ru.cmo.edu.data.dto.EduWithFormDataDto;
import ru.cmo.edu.data.dto.FormDataCoreDto;
import ru.cmo.edu.data.dto.MunicipalityWithFormDataDto;
import ru.cmo.edu.rest.security.Role;
import ru.cmo.edu.service.EduService;
import ru.cmo.edu.service.FormDataService;
import ru.cmo.edu.service.MunicipalityService;

import java.util.List;

import static org.springframework.hateoas.mvc.ControllerLinkBuilder.linkTo;

/**
 * Created by to on 05.06.2017.
 */

@RestController
@RequestMapping(value = "/formdata")
public class FormDataController extends BaseController {

    private Logger logger = LoggerFactory.getLogger(FormDataController.class);

    private final FormDataService formDataService;
    private final EduService eduService;
    private final MunicipalityService municipalityService;

    public FormDataController(FormDataService formDataService, EduService eduService, MunicipalityService municipalityService) {
        this.formDataService = formDataService;
        this.eduService = eduService;
        this.municipalityService = municipalityService;
    }

    @PreAuthorize("hasAnyAuthority('region', 'municipality', 'edu')")
    @RequestMapping(method = RequestMethod.POST)
    public ResponseEntity edit(@RequestBody FormDataCoreDto formData) {
        Role role = getRole();
        if (role == null) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).build();
        }
        if (!role.canUpload(formData.getForm().getFormTypeId())) {
            return ResponseEntity.status(HttpStatus.NOT_ACCEPTABLE).build();
        }

        formDataService.editFormData(formData);
        return ResponseEntity.status(HttpStatus.OK).build();
    }

    @PreAuthorize("hasAnyAuthority('region', 'ministry', 'municipality')")
    @RequestMapping(value = "/edu/haveform", method = RequestMethod.GET)
    public ResponseEntity getEduListHaveFormData(@RequestParam(required = false) Integer municipalityId,
                                                 @RequestParam Integer formId,
                                                 @RequestParam Integer year) {
        List<EduWithFormDataDto> dtos = eduService.getAllByFormDto(EduWithFormDataDto.class, municipalityId, formId, year);
        return ResponseEntity.ok(dtos);
    }

    @PreAuthorize("hasAnyAuthority('region', 'ministry')")
    @RequestMapping(value = "/municipality/haveform", method = RequestMethod.GET)
    public ResponseEntity getMunicipalityListHaveFormData(@RequestParam(required = false) Integer regionId,
                                                          @RequestParam Integer formId,
                                                          @RequestParam Integer year) {
        List<MunicipalityWithFormDataDto> dtos = municipalityService.getAllByFormDto(MunicipalityWithFormDataDto.class, formId, year);
        return ResponseEntity.ok(dtos);
    }
}
