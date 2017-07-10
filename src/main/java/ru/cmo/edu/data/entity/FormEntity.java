package ru.cmo.edu.data.entity;

import com.fasterxml.jackson.annotation.JsonManagedReference;

import javax.persistence.*;
import java.sql.Date;
import java.util.Collection;

/**
 * Created by to on 06.06.2017.
 */
@Entity
@Table(name = "form", schema = "public", catalog = "edu_forms_test")
public class FormEntity {
    private int formId;
    private String name;
    private Date submissionDate;
    private String checkFileMd5;
    private boolean isCheckRequired;
    private Boolean isNotificationHidden;
    @JsonManagedReference
    private Collection<EduFormDataEntity> eduFormDatas;
    private FormTypeEntity formType;
    private FileEntity template;
    private FileEntity checkFile;
    private FileEntity checkDataFile;
    @JsonManagedReference
    private Collection<MunicipalityFormDataEntity> municipalityFormDatas;
    @JsonManagedReference
    private Collection<QueryEntity> queries;
    @JsonManagedReference
    private Collection<RegionFormDataEntity> regionFormDatas;

    @Id
    @Column(name = "form_id")
    public int getFormId() {
        return formId;
    }

    public void setFormId(int formId) {
        this.formId = formId;
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
    public Date getSubmissionDate() {
        return submissionDate;
    }

    public void setSubmissionDate(Date submissionDate) {
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

        FormEntity that = (FormEntity) o;

        if (formId != that.formId) return false;
        if (isCheckRequired != that.isCheckRequired) return false;
        if (name != null ? !name.equals(that.name) : that.name != null) return false;
        if (submissionDate != null ? !submissionDate.equals(that.submissionDate) : that.submissionDate != null)
            return false;
        if (checkFileMd5 != null ? !checkFileMd5.equals(that.checkFileMd5) : that.checkFileMd5 != null) return false;
        if (isNotificationHidden != null ? !isNotificationHidden.equals(that.isNotificationHidden) : that.isNotificationHidden != null)
            return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = formId;
        result = 31 * result + (name != null ? name.hashCode() : 0);
        result = 31 * result + (submissionDate != null ? submissionDate.hashCode() : 0);
        result = 31 * result + (checkFileMd5 != null ? checkFileMd5.hashCode() : 0);
        result = 31 * result + (isCheckRequired ? 1 : 0);
        result = 31 * result + (isNotificationHidden != null ? isNotificationHidden.hashCode() : 0);
        return result;
    }

    @OneToMany(mappedBy = "form", fetch = FetchType.LAZY)
    public Collection<EduFormDataEntity> getEduFormDatas() {
        return eduFormDatas;
    }

    public void setEduFormDatas(Collection<EduFormDataEntity> eduFormDatasByFormId) {
        this.eduFormDatas = eduFormDatasByFormId;
    }

    @ManyToOne
    @JoinColumn(name = "form_type_id", referencedColumnName = "form_type_id", nullable = false)
    public FormTypeEntity getFormType() {
        return formType;
    }

    public void setFormType(FormTypeEntity formTypeByFormTypeId) {
        this.formType = formTypeByFormTypeId;
    }

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "template_file_id", referencedColumnName = "file_id")
    public FileEntity getTemplate() {
        return template;
    }

    public void setTemplate(FileEntity fileByTemplateFileId) {
        this.template = fileByTemplateFileId;
    }

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "check_file_id", referencedColumnName = "file_id")
    public FileEntity getCheckFile() {
        return checkFile;
    }

    public void setCheckFile(FileEntity fileByCheckFileId) {
        this.checkFile = fileByCheckFileId;
    }

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "check_data_file_id", referencedColumnName = "file_id")
    public FileEntity getCheckDataFile() {
        return checkDataFile;
    }

    public void setCheckDataFile(FileEntity fileByCheckDataFileId) {
        this.checkDataFile = fileByCheckDataFileId;
    }

    @OneToMany(mappedBy = "formByFormId", fetch = FetchType.LAZY)
    public Collection<MunicipalityFormDataEntity> getMunicipalityFormDatas() {
        return municipalityFormDatas;
    }

    public void setMunicipalityFormDatas(Collection<MunicipalityFormDataEntity> municipalityFormDatasByFormId) {
        this.municipalityFormDatas = municipalityFormDatasByFormId;
    }

    @OneToMany(mappedBy = "formByFormId", fetch = FetchType.LAZY)
    public Collection<QueryEntity> getQueries() {
        return queries;
    }

    public void setQueries(Collection<QueryEntity> queriesByFormId) {
        this.queries = queriesByFormId;
    }

    @OneToMany(mappedBy = "formByFormId", fetch = FetchType.LAZY)
    public Collection<RegionFormDataEntity> getRegionFormDatas() {
        return regionFormDatas;
    }

    public void setRegionFormDatas(Collection<RegionFormDataEntity> regionFormDatasByFormId) {
        this.regionFormDatas = regionFormDatasByFormId;
    }
}
