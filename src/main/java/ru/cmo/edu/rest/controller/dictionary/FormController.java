package ru.cmo.edu.rest.controller.dictionary;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import ru.cmo.edu.data.dto.FormCoreDto;
import ru.cmo.edu.data.entity.enumerable.FormTypeEnum;
import ru.cmo.edu.rest.controller.BaseController;
import ru.cmo.edu.service.FormService;

import java.util.List;
import java.util.Set;

/**
 * Created by to on 05.06.2017.
 */

@RestController
@RequestMapping(value = "/dictionary/forms")
public class FormController extends BaseController {

    Logger logger = LoggerFactory.getLogger(FormController.class);

    final private FormService formService;

    public FormController(FormService formService) {
        this.formService = formService;
    }

    @PreAuthorize("hasAnyAuthority('region','ministry','municipality','edu')")
    @RequestMapping(value = "/list", method = RequestMethod.GET)
    public ResponseEntity getFormList(@RequestParam(required = false) Set<Integer> formTypeIds,
                                      @RequestParam(required = false) Integer eduId) {
        List<FormCoreDto> dtos;
        if (eduId != null) {
            dtos = formService.getAllDtoByEdu(FormCoreDto.class, eduId);
        } else {
            Integer[] formTypeIdList = formTypeIds == null || formTypeIds.isEmpty()
                    ? FormTypeEnum.ALL : formTypeIds.stream().toArray(Integer[]::new);
            dtos = formService.getAllDto(FormCoreDto.class, formTypeIdList);
        }
        return ResponseEntity.ok(dtos);
    }
}
