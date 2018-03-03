package ru.cmo.edu.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import ru.cmo.edu.data.entity.File;
import ru.cmo.edu.data.repository.FileRepository;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.nio.file.Paths;

@Service
public class FileService {

    Logger logger = LoggerFactory.getLogger(FileService.class);

    private FileRepository fileRepository;

    @Value("${app.storage-path}")
    String storagePath;

    @Autowired
    public FileService(FileRepository fileRepository) {
        this.fileRepository = fileRepository;
    }

    public java.io.File getFile(int id) {
        File fileObj = fileRepository.findById(id);
        java.io.File file = Paths.get(storagePath, fileObj.getFilePath()).toFile();
        logger.debug("Getting file {}...", file.getAbsolutePath());
        return file;
    }
}
