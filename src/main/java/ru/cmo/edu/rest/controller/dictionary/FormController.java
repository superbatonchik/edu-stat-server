package ru.cmo.edu.rest.controller.dictionary;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import ru.cmo.edu.data.dto.FormCoreDto;
import ru.cmo.edu.data.entity.enumerable.FormTypeEnum;
import ru.cmo.edu.data.repository.FormRepository;
import ru.cmo.edu.exception.InvalidTokenException;
import ru.cmo.edu.rest.controller.BaseController;
import ru.cmo.edu.rest.security.Role;
import ru.cmo.edu.service.FormService;
import ru.cmo.edu.service.JwtService;

import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

/**
 * Created by to on 05.06.2017.
 */

@RestController
@RequestMapping(value = "/dictionary/forms")
public class FormController extends BaseController {

    Logger logger = LoggerFactory.getLogger(FormController.class);

    final private JwtService jwtService;
    final private FormService formService;

    final private FormRepository formRepository;

    public FormController(JwtService jwtService, FormService formService, FormRepository formRepository) {
        this.jwtService = jwtService;
        this.formService = formService;
        this.formRepository = formRepository;
    }

    @PreAuthorize("hasAnyAuthority('region','ministry','municipality','edu')")
    @RequestMapping(value = "/list", method = RequestMethod.GET)
    public ResponseEntity getFormList(@RequestParam(required = false) Set<Integer> formTypeIds, @RequestHeader HttpHeaders headers) throws InvalidTokenException {
        Role role = getRole();
        String token = headers.getFirst("Authorization");
        int userId = jwtService.getUserId(token);
        List<FormCoreDto> dtos = null;
        List<Integer> blockedIds = null;
        switch (role) {
            case region:
            case ministry:
            case municipality:
                Integer[] formTypeIdList = formTypeIds == null || formTypeIds.isEmpty()
                        ? FormTypeEnum.ALL : formTypeIds.stream().toArray(Integer[]::new);
                dtos = formService.getAllDto(FormCoreDto.class, formTypeIdList);
                if (role.equals(Role.municipality)) {
                    blockedIds = formRepository.findBlockedIdsByMunicipality(userId).stream()
                            .map(t -> (int)t[0])
                            .collect(Collectors.toList());
                }
                break;
            case edu:
                dtos = formService.getAllDtoByEdu(FormCoreDto.class, userId);
                blockedIds = formRepository.findBlockedIdsByEdu(userId).stream()
                        .map(t -> (int)t[0])
                        .collect(Collectors.toList());
                break;
        }
        for (FormCoreDto dto : dtos) {
            if (blockedIds.contains(dto.getId())) {
                dto.setBlocked(true);
            }
            LocalDateTime now = LocalDateTime.now();
            long remaining = ChronoUnit.SECONDS.between(now, dto.getSubmissionDate().withYear(now.getYear()));
            dto.setRemainingTimeSeconds(remaining);
        }
        return ResponseEntity.ok(dtos);
    }
}
