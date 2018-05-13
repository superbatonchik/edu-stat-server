package ru.cmo.edu.data.dto;

import ru.cmo.edu.data.entity.File;

public class FileDto {
    private int id;
    private int codePage;
    private String filePath;
    private String fileName;

    public FileDto() {
    }

    public FileDto(File f) {
        this.id = f.getId();
        this.codePage = f.getCodePage();
        this.filePath = f.getFilePath();
        this.fileName = f.getFileName();
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getCodePage() {
        return codePage;
    }

    public void setCodePage(int codePage) {
        this.codePage = codePage;
    }

    public String getFilePath() {
        return filePath;
    }

    public void setFilePath(String filePath) {
        this.filePath = filePath;
    }

    public String getFileName() {
        return fileName;
    }

    public void setFileName(String fileName) {
        this.fileName = fileName;
    }
}
