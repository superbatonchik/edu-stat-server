package ru.cmo.edu.data.entity;

import javax.persistence.*;
import java.time.LocalDateTime;

/**
 * Created by to on 11.07.2017.
 */
@MappedSuperclass
public class BaseFormData {
    private int id;
    private int documentFormatId;
    private int formId;
    private LocalDateTime sendDate;
    private int status;
    private int fileId;
    private DocumentFormat documentFormat;
    private Form form;
    private File file;

    @Id
    @Column(name = "id")
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    @Basic
    @Column(name = "document_format_id", insertable = false, updatable = false)
    public int getDocumentFormatId() {
        return documentFormatId;
    }

    public void setDocumentFormatId(int documentFormatId) {
        this.documentFormatId = documentFormatId;
    }

    @Basic
    @Column(name = "form_id", insertable = false, updatable = false)
    public int getFormId() {
        return formId;
    }

    public void setFormId(int formId) {
        this.formId = formId;
    }

    @Basic
    @Column(name = "send_date")
    public LocalDateTime getSendDate() {
        return sendDate;
    }

    public void setSendDate(LocalDateTime sendDate) {
        this.sendDate = sendDate;
    }

    @Basic
    @Column(name = "status")
    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    @Basic
    @Column(name = "file_id", insertable = false, updatable = false)
    public int getFileId() {
        return fileId;
    }

    public void setFileId(int fileId) {
        this.fileId = fileId;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        BaseFormData that = (BaseFormData) o;

        if (id != that.id) return false;
        if (documentFormatId != that.documentFormatId) return false;
        if (formId != that.formId) return false;
        if (status != that.status) return false;
        if (fileId != that.fileId) return false;
        if (sendDate != null ? !sendDate.equals(that.sendDate) : that.sendDate != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = id;
        result = 31 * result + documentFormatId;
        result = 31 * result + formId;
        result = 31 * result + (sendDate != null ? sendDate.hashCode() : 0);
        result = 31 * result + status;
        result = 31 * result + fileId;
        return result;
    }

    @ManyToOne
    @JoinColumn(name = "document_format_id", referencedColumnName = "id", nullable = false)
    public DocumentFormat getDocumentFormat() {
        return documentFormat;
    }

    public void setDocumentFormat(DocumentFormat documentFormat) {
        this.documentFormat = documentFormat;
    }

    @ManyToOne
    @JoinColumn(name = "form_id", referencedColumnName = "id", nullable = false)
    public Form getForm() {
        return form;
    }

    public void setForm(Form form) {
        this.form = form;
    }

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "file_id", referencedColumnName = "id", nullable = false)
    public File getFile() {
        return file;
    }

    public void setFile(File file) {
        this.file = file;
    }
}
