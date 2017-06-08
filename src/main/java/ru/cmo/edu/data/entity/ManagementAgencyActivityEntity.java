package ru.cmo.edu.data.entity;

import javax.persistence.*;

/**
 * Created by to on 06.06.2017.
 */
@Entity
@Table(name = "management_agency_activity", schema = "public", catalog = "edu_forms_test")
public class ManagementAgencyActivityEntity {
    private int managementAgencyActivityId;
    private String name;

    @Id
    @Column(name = "management_agency_activity_id")
    public int getManagementAgencyActivityId() {
        return managementAgencyActivityId;
    }

    public void setManagementAgencyActivityId(int managementAgencyActivityId) {
        this.managementAgencyActivityId = managementAgencyActivityId;
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

        ManagementAgencyActivityEntity that = (ManagementAgencyActivityEntity) o;

        if (managementAgencyActivityId != that.managementAgencyActivityId) return false;
        if (name != null ? !name.equals(that.name) : that.name != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = managementAgencyActivityId;
        result = 31 * result + (name != null ? name.hashCode() : 0);
        return result;
    }
}
