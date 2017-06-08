package ru.cmo.edu.data.entity;

import javax.persistence.*;

/**
 * Created by to on 06.06.2017.
 */
@Entity
@Table(name = "activity_type", schema = "public", catalog = "edu_forms_test")
public class ActivityTypeEntity {
    private int activityTypeId;
    private String name;

    @Id
    @Column(name = "activity_type_id")
    public int getActivityTypeId() {
        return activityTypeId;
    }

    public void setActivityTypeId(int activityTypeId) {
        this.activityTypeId = activityTypeId;
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

        ActivityTypeEntity that = (ActivityTypeEntity) o;

        if (activityTypeId != that.activityTypeId) return false;
        if (name != null ? !name.equals(that.name) : that.name != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = activityTypeId;
        result = 31 * result + (name != null ? name.hashCode() : 0);
        return result;
    }
}
