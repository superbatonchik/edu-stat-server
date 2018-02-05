package ru.cmo.edu.data.dto;

import ru.cmo.edu.data.entity.Form;

import java.time.LocalDateTime;

/**
 * Created by to on 12.07.2017.
 */
public class FormCoreDto {
    private int id;
    private String name;
    private LocalDateTime submissionDate;
    private int formTypeId;
    private boolean isCheckRequired;
    private Boolean isNotificationHidden;

    public FormCoreDto(Form f) {
        this.id = f.getId();
        this.name = f.getName();
        this.submissionDate = f.getSubmissionDate();
        this.formTypeId = f.getFormTypeId();
        this.isCheckRequired = f.isCheckRequired();
        this.isNotificationHidden = f.getNotificationHidden();
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
}
