package ru.cmo.edu.data.entity;

import javax.persistence.*;
import java.util.Set;

/**
 * Created by to on 11.07.2017.
 */
@Entity
@Table(name = "edu_kind", schema = "public", catalog = "edu_forms_test")
public class EduKind {
    private int id;
    private String name;
    private Set<Edu> edus;

    @Id
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

        EduKind eduKind = (EduKind) o;

        if (id != eduKind.id) return false;
        if (name != null ? !name.equals(eduKind.name) : eduKind.name != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = id;
        result = 31 * result + (name != null ? name.hashCode() : 0);
        return result;
    }

    @OneToMany(mappedBy = "eduKind")
    public Set<Edu> getEdus() {
        return edus;
    }

    public void setEdus(Set<Edu> edus) {
        this.edus = edus;
    }
}
