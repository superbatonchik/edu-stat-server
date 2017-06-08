package ru.cmo.edu.data.entity;

import javax.persistence.*;
import java.util.Collection;

/**
 * Created by to on 06.06.2017.
 */
@Entity
@Table(name = "edu_kind", schema = "public", catalog = "edu_forms_test")
public class EduKindEntity {
    private int eduKindId;
    private String name;
    private Collection<EduEntity> edus;

    @Id
    @Column(name = "edu_kind_id")
    public int getEduKindId() {
        return eduKindId;
    }

    public void setEduKindId(int eduKindId) {
        this.eduKindId = eduKindId;
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

        EduKindEntity that = (EduKindEntity) o;

        if (eduKindId != that.eduKindId) return false;
        if (name != null ? !name.equals(that.name) : that.name != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = eduKindId;
        result = 31 * result + (name != null ? name.hashCode() : 0);
        return result;
    }

    @OneToMany(mappedBy = "eduKind", fetch = FetchType.LAZY)
    public Collection<EduEntity> getEdus() {
        return edus;
    }

    public void setEdus(Collection<EduEntity> edusByEduKindId) {
        this.edus = edusByEduKindId;
    }
}
