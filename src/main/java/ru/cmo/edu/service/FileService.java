package ru.cmo.edu.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import ru.cmo.edu.config.Messages;
import ru.cmo.edu.data.entity.File;
import ru.cmo.edu.data.entity.enumerable.DocumentFormatEnum;
import ru.cmo.edu.data.repository.FileRepository;

import javax.xml.bind.DatatypeConverter;
import java.io.*;
import java.nio.file.*;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.UUID;

@Service
public class FileService {

    Logger logger = LoggerFactory.getLogger(FileService.class);

    private FileRepository fileRepository;
    private final Messages strings;

    @Value("${app.storage-path}")
    String storagePathStr;

    @Autowired
    public FileService(FileRepository fileRepository, Messages strings) {
        this.fileRepository = fileRepository;
        this.strings = strings;
    }

    public java.io.File getFile(int id) {
        File fileObj = fileRepository.findById(id);
        java.io.File file = Paths.get(storagePathStr, fileObj.getFilePath()).toFile();
        logger.debug("Getting file {}...", file.getAbsolutePath());
        return file;
    }

    public File saveFile(InputStream is, String documentFormat, String storageDirPostfix, Integer fileId) throws IOException {
        java.io.File tempFile = null;
        try {
            tempFile = java.io.File.createTempFile("upload_", ".tmp");
        } catch (IOException e) {
            logger.error("Error occurred while creating temp file", e);
            throw new IOException(strings.get("message.error-upload"), e);
        }
        MessageDigest md = null;
        try {
            md = MessageDigest.getInstance("MD5");
        } catch (NoSuchAlgorithmException e) {
            logger.error("", e);
            return null;
        }
        byte[] buffer = new byte[4096];
        int bytesRead;
        try (FileOutputStream outStream = new FileOutputStream(tempFile.getPath())) {
            while ((bytesRead = is.read(buffer)) > 0) {
                outStream.write(buffer, 0, bytesRead);
                md.update(buffer, 0, bytesRead);
            }
        } catch (IOException e) {
            logger.error("Error occurred while reading http stream", e);
            throw new IOException(strings.get("message.error-upload"), e);
        }
        logger.debug("Bytes read {}", tempFile.length());
        byte[] digest = md.digest();
        String hash = DatatypeConverter.printHexBinary(digest).toUpperCase();
        if (DocumentFormatEnum.value(documentFormat) == null) {
            documentFormat = "xlsx";
        }
        String relativePathStr = getRelativePath(hash, documentFormat);

        Path targetPath = Paths.get(storagePathStr, storageDirPostfix, relativePathStr);
        Path storagePath = Paths.get(storagePathStr);
        try {
            targetPath.toFile().mkdirs();
            Files.copy(tempFile.toPath(), targetPath, StandardCopyOption.REPLACE_EXISTING);
        } catch (IOException e) {
            logger.error("Error occurred while creating file at storage", e);
            throw new IOException(strings.get("message.error-upload"), e);
        }

        File fileObj = null;
        if (fileId != null) {
            java.io.File existingFile = getFile(fileId);
            if (existingFile.exists()) {
                existingFile.delete();
            }
            java.io.File parent = existingFile.getParentFile();
            while (parent.list().length == 0) {
                parent.delete();
                parent = parent.getParentFile();
            }
            fileObj = fileRepository.findById(fileId);
        }
        if (fileObj == null) {
            fileObj = new File();
        }
        fileObj.setCodePage(65001);
        fileObj.setFileName(targetPath.getFileName().toString());
        fileObj.setFilePath(storagePath.relativize(targetPath).toString());

        fileRepository.save(fileObj);
        return fileObj;
    }

    private static String getRelativePath(String hash, String ext) {
        Path newPath = Paths.get(hash.substring(0, 2).toLowerCase(),  hash.substring(2,4).toLowerCase(), UUID.randomUUID() + "." + ext);
        return newPath.toString();
    }
}
