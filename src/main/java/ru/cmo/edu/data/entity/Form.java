package ru.cmo.edu.data.entity;

import org.hibernate.annotations.Fetch;
import org.hibernate.annotations.FetchMode;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.Set;

/**
 * Created by to on 11.07.2017.
 */
@Entity
@Table(name = "form", schema = "public", catalog = "edu_forms_test")
public class Form {
    private int id;
    private String name;
    private LocalDateTime submissionDate;
    private String checkFileMd5;
    private int formTypeId;
    private Integer templateFileId;
    private Integer checkFileId;
    private boolean isCheckRequired;
    private Boolean isNotificationHidden;
    private FormType formType;
    private Set<Query> queries;
    private Set<Edu> blockedEdus;
    private Set<Edu> hiddenEdus;
    private Set<Municipality> blockedMunicipalities;

    @Id
    @Column(name = "id")
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    @Basic
    @Column(name = "name")
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Basic
    @Column(name = "submission_date")
    public LocalDateTime getSubmissionDate() {
        return submissionDate;
    }

    public void setSubmissionDate(LocalDateTime submissionDate) {
        this.submissionDate = submissionDate;
    }

    @Basic
    @Column(name = "check_file_md5")
    public String getCheckFileMd5() {
        return checkFileMd5;
    }

    public void setCheckFileMd5(String checkFileMd5) {
        this.checkFileMd5 = checkFileMd5;
    }

    @Basic
    @Column(name = "form_type_id", insertable = false, updatable = false)
    public int getFormTypeId() {
        return formTypeId;
    }

    public void setFormTypeId(int formTypeId) {
        this.formTypeId = formTypeId;
    }

    @Basic
    @Column(name = "template_file_id", insertable = false, updatable = false)
    public Integer getTemplateFileId() {
        return templateFileId;
    }

    public void setTemplateFileId(Integer templateFileId) {
        this.templateFileId = templateFileId;
    }

    @Basic
    @Column(name = "check_file_id", insertable = false, updatable = false)
    public Integer getCheckFileId() {
        return checkFileId;
    }

    public void setCheckFileId(Integer checkFileId) {
        this.checkFileId = checkFileId;
    }

    @Basic
    @Column(name = "is_check_required")
    public boolean isCheckRequired() {
        return isCheckRequired;
    }

    public void setCheckRequired(boolean checkRequired) {
        isCheckRequired = checkRequired;
    }

    @Basic
    @Column(name = "is_notification_hidden")
    public Boolean getNotificationHidden() {
        return isNotificationHidden;
    }

    public void setNotificationHidden(Boolean notificationHidden) {
        isNotificationHidden = notificationHidden;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Form form = (Form) o;

        if (id != form.id) return false;
        if (formTypeId != form.formTypeId) return false;
        if (isCheckRequired != form.isCheckRequired) return false;
        if (name != null ? !name.equals(form.name) : form.name != null) return false;
        if (submissionDate != null ? !submissionDate.equals(form.submissionDate) : form.submissionDate != null)
            return false;
        if (checkFileMd5 != null ? !checkFileMd5.equals(form.checkFileMd5) : form.checkFileMd5 != null) return false;
        if (templateFileId != null ? !templateFileId.equals(form.templateFileId) : form.templateFileId != null)
            return false;
        if (checkFileId != null ? !checkFileId.equals(form.checkFileId) : form.checkFileId != null) return false;
        if (isNotificationHidden != null ? !isNotificationHidden.equals(form.isNotificationHidden) : form.isNotificationHidden != null)
            return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = id;
        result = 31 * result + (name != null ? name.hashCode() : 0);
        result = 31 * result + (submissionDate != null ? submissionDate.hashCode() : 0);
        result = 31 * result + (checkFileMd5 != null ? checkFileMd5.hashCode() : 0);
        result = 31 * result + formTypeId;
        result = 31 * result + (templateFileId != null ? templateFileId.hashCode() : 0);
        result = 31 * result + (checkFileId != null ? checkFileId.hashCode() : 0);
        result = 31 * result + (isCheckRequired ? 1 : 0);
        result = 31 * result + (isNotificationHidden != null ? isNotificationHidden.hashCode() : 0);
        return result;
    }

    @ManyToOne
    @JoinColumn(name = "form_type_id", referencedColumnName = "id", nullable = false)
    public FormType getFormType() {
        return formType;
    }

    public void setFormType(FormType formType) {
        this.formType = formType;
    }

    @OneToMany(mappedBy = "form")
    public Set<Query> getQueries() {
        return queries;
    }

    public void setQueries(Set<Query> queries) {
        this.queries = queries;
    }

    @ManyToMany(mappedBy = "blockedForms")
    public Set<Edu> getBlockedEdus() {
        return blockedEdus;
    }

    public void setBlockedEdus(Set<Edu> blockedEdus) {
        this.blockedEdus = blockedEdus;
    }

    @ManyToMany(mappedBy = "hiddenForms")
    public Set<Edu> getHiddenEdus() {
        return hiddenEdus;
    }

    public void setHiddenEdus(Set<Edu> hiddenEdus) {
        this.hiddenEdus = hiddenEdus;
    }

    @ManyToMany(mappedBy = "blockedForms")
    public Set<Municipality> getBlockedMunicipalities() {
        return blockedMunicipalities;
    }

    public void setBlockedMunicipalities(Set<Municipality> blockedMunicipalities) {
        this.blockedMunicipalities = blockedMunicipalities;
    }
}
