package ru.cmo.edu.data.entity;

import javax.persistence.*;
import java.util.Collection;

/**
 * Created by to on 06.06.2017.
 */
@Entity
@Table(name = "settlement_type", schema = "public", catalog = "edu_forms_test")
public class SettlementTypeEntity {
    private int settlementTypeId;
    private String name;

    @Id
    @Column(name = "settlement_type_id")
    public int getSettlementTypeId() {
        return settlementTypeId;
    }

    public void setSettlementTypeId(int settlementTypeId) {
        this.settlementTypeId = settlementTypeId;
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

        SettlementTypeEntity that = (SettlementTypeEntity) o;

        if (settlementTypeId != that.settlementTypeId) return false;
        if (name != null ? !name.equals(that.name) : that.name != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = settlementTypeId;
        result = 31 * result + (name != null ? name.hashCode() : 0);
        return result;
    }

}
