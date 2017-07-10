package ru.cmo.edu.data.entity;

import javax.persistence.*;
import java.util.Collection;

/**
 * Created by to on 06.06.2017.
 */
@Entity
@Table(name = "ownership_type", schema = "public", catalog = "edu_forms_test")
public class OwnershipTypeEntity {
    private int ownershipTypeId;
    private String name;

    @Id
    @Column(name = "ownership_type_id")
    public int getOwnershipTypeId() {
        return ownershipTypeId;
    }

    public void setOwnershipTypeId(int ownershipTypeId) {
        this.ownershipTypeId = ownershipTypeId;
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

        OwnershipTypeEntity that = (OwnershipTypeEntity) o;

        if (ownershipTypeId != that.ownershipTypeId) return false;
        if (name != null ? !name.equals(that.name) : that.name != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = ownershipTypeId;
        result = 31 * result + (name != null ? name.hashCode() : 0);
        return result;
    }

}
