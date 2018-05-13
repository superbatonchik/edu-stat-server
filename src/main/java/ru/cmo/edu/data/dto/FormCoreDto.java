package ru.cmo.edu.data.dto;

import ru.cmo.edu.data.entity.Form;

import java.time.LocalDateTime;

/**
 * Created by to on 12.07.2017.
 */
public class FormCoreDto {
    private Integer templateFileId;
    private Integer checkFileId;
    private int id;
    private String name;
    private LocalDateTime submissionDate;
    private int formTypeId;
    private String formType;
    private boolean isCheckRequired;
    private boolean isNotificationHidden;
    private long remainingTimeSeconds;
    private boolean isBlocked;

    public FormCoreDto() {
    }

    public FormCoreDto(Form f) {
        this.id = f.getId();
        this.name = f.getName();
        this.submissionDate = f.getSubmissionDate();
        this.formTypeId = f.getFormTypeId();
        this.formType = f.getFormType().getName();
        this.isCheckRequired = f.isCheckRequired();
        this.isNotificationHidden = f.getNotificationHidden();
        this.templateFileId = f.getTemplateFileId();
        this.checkFileId = f.getCheckFileId();
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public LocalDateTime getSubmissionDate() {
        return submissionDate;
    }

    public void setSubmissionDate(LocalDateTime submissionDate) {
        this.submissionDate = submissionDate;
    }

    public int getFormTypeId() {
        return formTypeId;
    }

    public void setFormTypeId(int formTypeId) {
        this.formTypeId = formTypeId;
    }

    public String getFormType() {
        return formType;
    }

    public void setFormType(String formType) {
        this.formType = formType;
    }

    public boolean isCheckRequired() {
        return isCheckRequired;
    }

    public void setCheckRequired(boolean checkRequired) {
        isCheckRequired = checkRequired;
    }

    public Boolean getNotificationHidden() {
        return isNotificationHidden;
    }

    public void setNotificationHidden(Boolean notificationHidden) {
        isNotificationHidden = notificationHidden;
    }

    public long getTemplateFileId() {
        return templateFileId;
    }

    public void setTemplateFileId(Integer templateFileId) {
        this.templateFileId = templateFileId;
    }

    public long getCheckFileId() {
        return checkFileId;
    }

    public void setCheckFileId(Integer checkFileId) {
        this.checkFileId = checkFileId;
    }

    public long getRemainingTimeSeconds() {
        return remainingTimeSeconds;
    }

    public void setRemainingTimeSeconds(long remainingTimeSeconds) {
        this.remainingTimeSeconds = remainingTimeSeconds;
    }

    public boolean isBlocked() {
        return isBlocked;
    }

    public void setBlocked(boolean blocked) {
        isBlocked = blocked;
    }
}
