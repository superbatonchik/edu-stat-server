package ru.cmo.edu.data.entity;

import com.fasterxml.jackson.annotation.JsonBackReference;

import javax.persistence.*;
import java.sql.Date;

/**
 * Created by to on 06.06.2017.
 */
@Entity
@Table(name = "region_form_data", schema = "public", catalog = "edu_forms_test")
public class RegionFormDataEntity {
    private int id;
    private Date sendDate;
    private int status;
    private DocumentFormatEntity documentFormatByDocumentFormatId;
    private FormEntity formByFormId;
    private RegionEntity regionByRegionId;
    private FileEntity fileByFileId;

    @Id
    @Column(name = "id")
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    @Basic
    @Column(name = "send_date")
    public Date getSendDate() {
        return sendDate;
    }

    public void setSendDate(Date sendDate) {
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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        RegionFormDataEntity that = (RegionFormDataEntity) o;

        if (id != that.id) return false;
        if (status != that.status) return false;
        if (sendDate != null ? !sendDate.equals(that.sendDate) : that.sendDate != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = id;
        result = 31 * result + (sendDate != null ? sendDate.hashCode() : 0);
        result = 31 * result + status;
        return result;
    }

    @ManyToOne
    @JoinColumn(name = "document_format_id", referencedColumnName = "document_format_id", nullable = false)
    public DocumentFormatEntity getDocumentFormatByDocumentFormatId() {
        return documentFormatByDocumentFormatId;
    }

    public void setDocumentFormatByDocumentFormatId(DocumentFormatEntity documentFormatByDocumentFormatId) {
        this.documentFormatByDocumentFormatId = documentFormatByDocumentFormatId;
    }

    @JsonBackReference
    @ManyToOne
    @JoinColumn(name = "form_id", referencedColumnName = "form_id", nullable = false)
    public FormEntity getFormByFormId() {
        return formByFormId;
    }

    public void setFormByFormId(FormEntity formByFormId) {
        this.formByFormId = formByFormId;
    }

    @ManyToOne
    @JoinColumn(name = "region_id", referencedColumnName = "region_id", nullable = false)
    public RegionEntity getRegionByRegionId() {
        return regionByRegionId;
    }

    public void setRegionByRegionId(RegionEntity regionByRegionId) {
        this.regionByRegionId = regionByRegionId;
    }

    @ManyToOne
    @JoinColumn(name = "file_id", referencedColumnName = "file_id", nullable = false)
    public FileEntity getFileByFileId() {
        return fileByFileId;
    }

    public void setFileByFileId(FileEntity fileByFileId) {
        this.fileByFileId = fileByFileId;
    }
}
