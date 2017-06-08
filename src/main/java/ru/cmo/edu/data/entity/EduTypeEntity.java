package ru.cmo.edu.data.entity;

import org.hibernate.annotations.LazyCollection;

import javax.persistence.*;
import java.util.Collection;

/**
 * Created by to on 06.06.2017.
 */
@Entity
@Table(name = "edu_type", schema = "public", catalog = "edu_forms_test")
public class EduTypeEntity {
    private int eduTypeId;
    private String name;
    private Collection<EduEntity> edus;

    @Id
    @Column(name = "edu_type_id")
    public int getEduTypeId() {
        return eduTypeId;
    }

    public void setEduTypeId(int eduTypeId) {
        this.eduTypeId = eduTypeId;
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

        EduTypeEntity that = (EduTypeEntity) o;

        if (eduTypeId != that.eduTypeId) return false;
        if (name != null ? !name.equals(that.name) : that.name != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = eduTypeId;
        result = 31 * result + (name != null ? name.hashCode() : 0);
        return result;
    }

    @OneToMany(mappedBy = "eduType", fetch = FetchType.LAZY)
    public Collection<EduEntity> getEdus() {
        return edus;
    }

    public void setEdus(Collection<EduEntity> edusByEduTypeId) {
        this.edus = edusByEduTypeId;
    }
}
