package ru.cmo.edu.data.dto;

import ru.cmo.edu.data.entity.BaseFormData;

import java.sql.Date;

/**
 * Created by to on 12.07.2017.
 */
public class BaseFormDataCoreDto {
    private int id;
    private Date sendDate;
    private int status;
    private int fileId;
    private String documentFormat;
    private FormCoreDto form;

    public BaseFormDataCoreDto(BaseFormData f) {
        this.id = f.getId();
        this.sendDate = f.getSendDate();
        this.status = f.getStatus();
        this.fileId = f.getFileId();
        this.documentFormat = f.getDocumentFormat().getName();
        this.form = new FormCoreDto(f.getForm());
    }

    public BaseFormDataCoreDto() {
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Date getSendDate() {
        return sendDate;
    }

    public void setSendDate(Date sendDate) {
        this.sendDate = sendDate;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public int getFileId() {
        return fileId;
    }

    public void setFileId(int fileId) {
        this.fileId = fileId;
    }

    public String getDocumentFormat() {
        return documentFormat;
    }

    public void setDocumentFormat(String documentFormat) {
        this.documentFormat = documentFormat;
    }

    public FormCoreDto getForm() {
        return form;
    }

    public void setForm(FormCoreDto form) {
        this.form = form;
    }
}
