package ru.cmo.edu.data.entity;

import javax.persistence.*;
import java.util.Collection;

/**
 * Created by to on 06.06.2017.
 */
@Entity
@Table(name = "edu_status", schema = "public", catalog = "edu_forms_test")
public class EduStatusEntity {
    private int eduStatusId;
    private String name;
    private Collection<EduEntity> edus;

    @Id
    @Column(name = "edu_status_id")
    public int getEduStatusId() {
        return eduStatusId;
    }

    public void setEduStatusId(int eduStatusId) {
        this.eduStatusId = eduStatusId;
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

        EduStatusEntity that = (EduStatusEntity) o;

        if (eduStatusId != that.eduStatusId) return false;
        if (name != null ? !name.equals(that.name) : that.name != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = eduStatusId;
        result = 31 * result + (name != null ? name.hashCode() : 0);
        return result;
    }

    @OneToMany(mappedBy = "eduStatus", fetch = FetchType.LAZY)
    public Collection<EduEntity> getEdus() {
        return edus;
    }

    public void setEdus(Collection<EduEntity> edusByEduStatusId) {
        this.edus = edusByEduStatusId;
    }
}
