package ru.cmo.edu.data.entity;

import javax.persistence.*;

/**
 * Created by to on 11.07.2017.
 */
@Entity
@Table(name = "form_type", schema = "public", catalog = "edu_forms_test")
public class FormType {
    private int id;
    private String name;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        FormType formType = (FormType) o;

        if (id != formType.id) return false;
        if (name != null ? !name.equals(formType.name) : formType.name != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = id;
        result = 31 * result + (name != null ? name.hashCode() : 0);
        return result;
    }
}
