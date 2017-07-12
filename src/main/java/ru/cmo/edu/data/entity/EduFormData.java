package ru.cmo.edu.data.entity;

import javax.persistence.*;
import java.sql.Date;

/**
 * Created by to on 11.07.2017.
 */
@Entity
@Table(name = "edu_form_data", schema = "public", catalog = "edu_forms_test")
public class EduFormData extends BaseFormData {
    private int eduId;
    private Edu edu;

    @Basic
    @Column(name = "edu_id", insertable = false, updatable = false)
    public int getEduId() {
        return eduId;
    }

    public void setEduId(int eduId) {
        this.eduId = eduId;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        EduFormData that = (EduFormData) o;

        if (eduId != that.eduId && super.equals(o)) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = super.hashCode();
        result = 31 * result + eduId;
        return result;
    }

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "edu_id", referencedColumnName = "id", nullable = false)
    public Edu getEdu() {
        return edu;
    }

    public void setEdu(Edu edu) {
        this.edu = edu;
    }
}
