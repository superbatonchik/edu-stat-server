package ru.cmo.edu.rest.controller.dictionary;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import ru.cmo.edu.data.dto.FormCoreDto;
import ru.cmo.edu.data.entity.MmRegularSummaryForm;
import ru.cmo.edu.data.entity.enumerable.FormTypeEnum;
import ru.cmo.edu.data.repository.FormRepository;
import ru.cmo.edu.exception.InvalidTokenException;
import ru.cmo.edu.rest.controller.BaseController;
import ru.cmo.edu.rest.security.Role;
import ru.cmo.edu.service.EduService;
import ru.cmo.edu.service.FormService;
import ru.cmo.edu.service.JwtService;

import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@RestController
@RequestMapping(value = "/dictionary/edu")
public class EduController extends BaseController {

    Logger logger = LoggerFactory.getLogger(EduController.class);

    final private JwtService jwtService;
    final private EduService eduService;

    public EduController(JwtService jwtService, EduService eduService) {
        this.jwtService = jwtService;
        this.eduService = eduService;
    }
}
