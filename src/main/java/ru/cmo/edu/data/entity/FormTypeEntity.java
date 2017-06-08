package ru.cmo.edu.data.entity;

import javax.persistence.*;
import java.util.Collection;

/**
 * Created by to on 06.06.2017.
 */
@Entity
@Table(name = "form_type", schema = "public", catalog = "edu_forms_test")
public class FormTypeEntity {
    private int formTypeId;
    private String name;
    private Collection<FormEntity> forms;

    @Id
    @Column(name = "form_type_id")
    public int getFormTypeId() {
        return formTypeId;
    }

    public void setFormTypeId(int formTypeId) {
        this.formTypeId = formTypeId;
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

        FormTypeEntity that = (FormTypeEntity) o;

        if (formTypeId != that.formTypeId) return false;
        if (name != null ? !name.equals(that.name) : that.name != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = formTypeId;
        result = 31 * result + (name != null ? name.hashCode() : 0);
        return result;
    }

    @OneToMany(mappedBy = "formType", fetch = FetchType.LAZY)
    public Collection<FormEntity> getForms() {
        return forms;
    }

    public void setForms(Collection<FormEntity> formsByFormTypeId) {
        this.forms = formsByFormTypeId;
    }
}
