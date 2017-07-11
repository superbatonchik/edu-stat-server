package ru.cmo.edu.data.entity;

import javax.persistence.*;

/**
 * Created by to on 11.07.2017.
 */
@Entity
@Table(name = "t_detailed_municipality_form_statistics", schema = "public", catalog = "edu_forms_test")
public class TDetailedMunicipalityFormStatistics {
    private long rowId;
    private int formTypeId;
    private String formType;
    private String form;
    private int formId;
    private int municipalityId;
    private String municipality;
    private int uploadStatus;
    private int year;

    @Id
    @Column(name = "row_id")
    public long getRowId() {
        return rowId;
    }

    public void setRowId(long rowId) {
        this.rowId = rowId;
    }

    @Basic
    @Column(name = "form_type_id")
    public int getFormTypeId() {
        return formTypeId;
    }

    public void setFormTypeId(int formTypeId) {
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
    @Column(name = "form")
    public String getForm() {
        return form;
    }

    public void setForm(String form) {
        this.form = form;
    }

    @Basic
    @Column(name = "form_id")
    public int getFormId() {
        return formId;
    }

    public void setFormId(int formId) {
        this.formId = formId;
    }

    @Basic
    @Column(name = "municipality_id")
    public int getMunicipalityId() {
        return municipalityId;
    }

    public void setMunicipalityId(int municipalityId) {
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
    @Column(name = "upload_status")
    public int getUploadStatus() {
        return uploadStatus;
    }

    public void setUploadStatus(int uploadStatus) {
        this.uploadStatus = uploadStatus;
    }

    @Basic
    @Column(name = "year")
    public int getYear() {
        return year;
    }

    public void setYear(int year) {
        this.year = year;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        TDetailedMunicipalityFormStatistics that = (TDetailedMunicipalityFormStatistics) o;

        if (rowId != that.rowId) return false;
        if (formTypeId != that.formTypeId) return false;
        if (formId != that.formId) return false;
        if (municipalityId != that.municipalityId) return false;
        if (uploadStatus != that.uploadStatus) return false;
        if (year != that.year) return false;
        if (formType != null ? !formType.equals(that.formType) : that.formType != null) return false;
        if (form != null ? !form.equals(that.form) : that.form != null) return false;
        if (municipality != null ? !municipality.equals(that.municipality) : that.municipality != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = (int) (rowId ^ (rowId >>> 32));
        result = 31 * result + formTypeId;
        result = 31 * result + (formType != null ? formType.hashCode() : 0);
        result = 31 * result + (form != null ? form.hashCode() : 0);
        result = 31 * result + formId;
        result = 31 * result + municipalityId;
        result = 31 * result + (municipality != null ? municipality.hashCode() : 0);
        result = 31 * result + uploadStatus;
        result = 31 * result + year;
        return result;
    }
}
