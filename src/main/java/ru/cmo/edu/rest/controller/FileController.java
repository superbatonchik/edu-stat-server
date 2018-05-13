package ru.cmo.edu.rest.controller;

import org.apache.commons.io.IOUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import ru.cmo.edu.data.entity.File;
import ru.cmo.edu.service.FileService;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.FileInputStream;
import java.io.InputStream;

@RestController
@RequestMapping(value = "/file")
public class FileController extends BaseController {

    private Logger logger = LoggerFactory.getLogger(FileController.class);

    private FileService fileService;

    @Autowired
    public FileController(FileService fileService) {
        this.fileService = fileService;
    }

    @PreAuthorize("hasAnyAuthority('region','ministry','municipality','edu')")
    @RequestMapping(method = RequestMethod.GET)
    public void downloadFile(@RequestParam int id, HttpServletResponse response) throws IOException {
        java.io.File file = fileService.getFile(id);
        if (!file.exists()) {
            logger.error("File not found");
            response.sendError(404, "File not found");
        }
        response.setContentLength((int) file.length());
        response.setContentType(MediaType.APPLICATION_OCTET_STREAM_VALUE);
        FileInputStream stream = new FileInputStream(file);
        IOUtils.copy(stream, response.getOutputStream());
    }

    @PreAuthorize("hasAnyAuthority('region','municipality','edu')")
    @RequestMapping(method = RequestMethod.POST)
    public ResponseEntity uploadFile(@RequestParam MultipartFile upload,
                                     @RequestParam String documentFormat,
                                     @RequestParam String type,
                                     @RequestParam(required = false) Integer fileId) throws IOException {
        try (InputStream is = upload.getInputStream()) {
            File result = fileService.saveFile(is, documentFormat, type, fileId);
            if (result == null) {
                throw new IOException(strings.get("message.error-upload"));
            }
            return ResponseEntity.ok(result.getId());
        } catch (IOException e) {
            throw e;
        }
    }
}
