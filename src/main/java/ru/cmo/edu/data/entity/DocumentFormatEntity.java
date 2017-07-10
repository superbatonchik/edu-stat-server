package ru.cmo.edu.data.entity;

import javax.persistence.*;
import java.util.Collection;

/**
 * Created by to on 06.06.2017.
 */
@Entity
@Table(name = "document_format", schema = "public", catalog = "edu_forms_test")
public class DocumentFormatEntity {
    private int documentFormatId;
    private String name;

    @Id
    @Column(name = "document_format_id")
    public int getDocumentFormatId() {
        return documentFormatId;
    }

    public void setDocumentFormatId(int documentFormatId) {
        this.documentFormatId = documentFormatId;
    }

    @Basic
    @Column(name = "name")
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        DocumentFormatEntity that = (DocumentFormatEntity) o;

        if (documentFormatId != that.documentFormatId) return false;
        if (name != null ? !name.equals(that.name) : that.name != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = documentFormatId;
        result = 31 * result + (name != null ? name.hashCode() : 0);
        return result;
    }
}
