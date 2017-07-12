package ru.cmo.edu.data.entity;

import javax.persistence.*;
import java.util.Arrays;

/**
 * Created by to on 11.07.2017.
 */
@Entity
@Table(name = "mm_regular__summary_form", schema = "public", catalog = "edu_forms_test")
public class MmRegularSummaryForm {
    private int regularFormId;
    private int summaryFormId;
    private byte[] fileData;
    private int id;
    private Form regularForm;
    private Form summaryForm;

    @Basic
    @Column(name = "regular_form_id", insertable = false, updatable = false)
    public int getRegularFormId() {
        return regularFormId;
    }

    public void setRegularFormId(int regularFormId) {
        this.regularFormId = regularFormId;
    }

    @Basic
    @Column(name = "summary_form_id", insertable = false, updatable = false)
    public int getSummaryFormId() {
        return summaryFormId;
    }

    public void setSummaryFormId(int summaryFormId) {
        this.summaryFormId = summaryFormId;
    }

    @Basic
    @Column(name = "file_data")
    public byte[] getFileData() {
        return fileData;
    }

    public void setFileData(byte[] fileData) {
        this.fileData = fileData;
    }

    @Id
    @Column(name = "id")
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        MmRegularSummaryForm that = (MmRegularSummaryForm) o;

        if (regularFormId != that.regularFormId) return false;
        if (summaryFormId != that.summaryFormId) return false;
        if (id != that.id) return false;
        if (!Arrays.equals(fileData, that.fileData)) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = regularFormId;
        result = 31 * result + summaryFormId;
        result = 31 * result + Arrays.hashCode(fileData);
        result = 31 * result + id;
        return result;
    }

    @ManyToOne
    @JoinColumn(name = "regular_form_id", referencedColumnName = "id", nullable = false)
    public Form getRegularForm() {
        return regularForm;
    }

    public void setRegularForm(Form regularForm) {
        this.regularForm = regularForm;
    }

    @ManyToOne
    @JoinColumn(name = "summary_form_id", referencedColumnName = "id", nullable = false)
    public Form getSummaryForm() {
        return summaryForm;
    }

    public void setSummaryForm(Form summaryForm) {
        this.summaryForm = summaryForm;
    }
}
