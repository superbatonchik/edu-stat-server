package ru.cmo.edu.data.entity;

import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

/**
 * Created by to on 11.07.2017.
 */
@Entity
@Table(name = "detailed_form_statistics", schema = "public", catalog = "edu_forms_test")
public class VDetailedFormStatistics {
    private Long rowId;
    private Integer formTypeId;
    private String formType;
    private String eduKind;
    private Integer municipalityId;
    private String municipality;
    private String form;
    private Integer formId;
    private String eduName;
    private Integer isUploaded;
    private Integer withErrors;
    private Integer year;

    @Basic
    @Column(name = "row_id")
    public Long getRowId() {
        return rowId;
    }

    public void setRowId(Long rowId) {
        this.rowId = rowId;
    }

    @Basic
    @Column(name = "form_type_id")
    public Integer getFormTypeId() {
        return formTypeId;
    }

    public void setFormTypeId(Integer formTypeId) {
        this.formTypeId = formTypeId;
    }

    @Basic
    @Column(name = "form_type")
    public String getFormType() {
        return formType;
    }

    public void setFormType(String formType) {
        this.formType = formType;
    }

    @Basic
    @Column(name = "edu_kind")
    public String getEduKind() {
        return eduKind;
    }

    public void setEduKind(String eduKind) {
        this.eduKind = eduKind;
    }

    @Basic
    @Column(name = "municipality_id")
    public Integer getMunicipalityId() {
        return municipalityId;
    }

    public void setMunicipalityId(Integer municipalityId) {
        this.municipalityId = municipalityId;
    }

    @Basic
    @Column(name = "municipality")
    public String getMunicipality() {
        return municipality;
    }

    public void setMunicipality(String municipality) {
        this.municipality = municipality;
    }

    @Basic
    @Column(name = "form")
    public String getForm() {
        return form;
    }

    public void setForm(String form) {
        this.form = form;
    }

    @Basic
    @Column(name = "form_id")
    public Integer getFormId() {
        return formId;
    }

    public void setFormId(Integer formId) {
        this.formId = formId;
    }

    @Basic
    @Column(name = "edu_name")
    public String getEduName() {
        return eduName;
    }

    public void setEduName(String eduName) {
        this.eduName = eduName;
    }

    @Basic
    @Column(name = "is_uploaded")
    public Integer getIsUploaded() {
        return isUploaded;
    }

    public void setIsUploaded(Integer isUploaded) {
        this.isUploaded = isUploaded;
    }

    @Basic
    @Column(name = "with_errors")
    public Integer getWithErrors() {
        return withErrors;
    }

    public void setWithErrors(Integer withErrors) {
        this.withErrors = withErrors;
    }

    @Basic
    @Column(name = "year")
    public Integer getYear() {
        return year;
    }

    public void setYear(Integer year) {
        this.year = year;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        VDetailedFormStatistics that = (VDetailedFormStatistics) o;

        if (rowId != null ? !rowId.equals(that.rowId) : that.rowId != null) return false;
        if (formTypeId != null ? !formTypeId.equals(that.formTypeId) : that.formTypeId != null) return false;
        if (formType != null ? !formType.equals(that.formType) : that.formType != null) return false;
        if (eduKind != null ? !eduKind.equals(that.eduKind) : that.eduKind != null) return false;
        if (municipalityId != null ? !municipalityId.equals(that.municipalityId) : that.municipalityId != null)
            return false;
        if (municipality != null ? !municipality.equals(that.municipality) : that.municipality != null) return false;
        if (form != null ? !form.equals(that.form) : that.form != null) return false;
        if (formId != null ? !formId.equals(that.formId) : that.formId != null) return false;
        if (eduName != null ? !eduName.equals(that.eduName) : that.eduName != null) return false;
        if (isUploaded != null ? !isUploaded.equals(that.isUploaded) : that.isUploaded != null) return false;
        if (withErrors != null ? !withErrors.equals(that.withErrors) : that.withErrors != null) return false;
        if (year != null ? !year.equals(that.year) : that.year != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = rowId != null ? rowId.hashCode() : 0;
        result = 31 * result + (formTypeId != null ? formTypeId.hashCode() : 0);
        result = 31 * result + (formType != null ? formType.hashCode() : 0);
        result = 31 * result + (eduKind != null ? eduKind.hashCode() : 0);
        result = 31 * result + (municipalityId != null ? municipalityId.hashCode() : 0);
        result = 31 * result + (municipality != null ? municipality.hashCode() : 0);
        result = 31 * result + (form != null ? form.hashCode() : 0);
        result = 31 * result + (formId != null ? formId.hashCode() : 0);
        result = 31 * result + (eduName != null ? eduName.hashCode() : 0);
        result = 31 * result + (isUploaded != null ? isUploaded.hashCode() : 0);
        result = 31 * result + (withErrors != null ? withErrors.hashCode() : 0);
        result = 31 * result + (year != null ? year.hashCode() : 0);
        return result;
    }
}
