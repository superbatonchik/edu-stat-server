package ru.cmo.edu.rest.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.FileSystemResource;
import org.springframework.core.io.InputStreamResource;
import org.springframework.core.io.Resource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import ru.cmo.edu.data.dto.FormCoreDto;
import ru.cmo.edu.data.entity.MmRegularSummaryForm;
import ru.cmo.edu.data.entity.enumerable.FormTypeEnum;
import ru.cmo.edu.data.repository.FormRepository;
import ru.cmo.edu.exception.InvalidTokenException;
import ru.cmo.edu.rest.security.Role;
import ru.cmo.edu.service.FileService;
import ru.cmo.edu.service.FormService;
import ru.cmo.edu.service.JwtService;

import javax.servlet.http.HttpServletResponse;
import java.io.*;
import java.net.URLEncoder;
import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping(value = "/file")
public class FileController extends BaseController {

    Logger logger = LoggerFactory.getLogger(FileController.class);

    private FileService fileService;

    @Autowired
    public FileController(FileService fileService) {
        this.fileService = fileService;
    }

    @PreAuthorize("hasAnyAuthority('region','ministry','municipality','edu')")
    @RequestMapping(value = "/download", method = RequestMethod.GET)
    public void getFile(@RequestParam int id, HttpServletResponse response) throws IOException {
        File file = fileService.getFile(id);
        if (!file.exists()) {
            logger.error("File not found");
            response.sendError(404, "File not found");
        }
        //response.addHeader("Encoding", "UTF-8");
        //response.addHeader("Content-Disposition", "attachment; filename=" + URLEncoder.encode(file.getName(), "UTF-8"));
        response.setContentLength((int) file.length());
        response.setContentType(MediaType.APPLICATION_OCTET_STREAM_VALUE);
        FileInputStream stream = new FileInputStream(file);
        org.apache.commons.io.IOUtils.copy(stream, response.getOutputStream());
    }
}
