package ru.cmo.edu.data.entity;

import javax.persistence.*;

/**
 * Created by to on 06.06.2017.
 */
@Entity
@Table(name = "management_agency", schema = "public", catalog = "edu_forms_test")
public class ManagementAgencyEntity {
    private int managementAgencyId;
    private String name;

    @Id
    @Column(name = "management_agency_id")
    public int getManagementAgencyId() {
        return managementAgencyId;
    }

    public void setManagementAgencyId(int managementAgencyId) {
        this.managementAgencyId = managementAgencyId;
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

        ManagementAgencyEntity that = (ManagementAgencyEntity) o;

        if (managementAgencyId != that.managementAgencyId) return false;
        if (name != null ? !name.equals(that.name) : that.name != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = managementAgencyId;
        result = 31 * result + (name != null ? name.hashCode() : 0);
        return result;
    }
}
