package ru.cmo.edu.data.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * Created by to on 06.06.2017.
 */
@Entity
@Table(name = "query_autocomplete_part", schema = "public", catalog = "edu_forms_test")
public class QueryAutocompletePartEntity {
    private String title;

    @Id
    @Column(name = "title")
    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        QueryAutocompletePartEntity that = (QueryAutocompletePartEntity) o;

        if (title != null ? !title.equals(that.title) : that.title != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        return title != null ? title.hashCode() : 0;
    }
}
