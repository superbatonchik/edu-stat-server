package ru.cmo.edu.data.entity;

import javax.persistence.*;
import java.util.Set;

/**
 * Created by to on 11.07.2017.
 */
@Entity
@Table(name = "region", schema = "public", catalog = "edu_forms_test")
public class Region extends BaseUser {
    private Set<RegionFormData> regionFormDatas;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Region region = (Region) o;

        if (super.getId() != region.getId()) return false;
        if (super.getName() != null ? !super.getName().equals(region.getName()) : region.getName() != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = super.hashCode();
        return result;
    }

    @OneToMany(mappedBy = "region")
    public Set<RegionFormData> getRegionFormDatas() {
        return regionFormDatas;
    }

    public void setRegionFormDatas(Set<RegionFormData> regionFormDatas) {
        this.regionFormDatas = regionFormDatas;
    }
}
