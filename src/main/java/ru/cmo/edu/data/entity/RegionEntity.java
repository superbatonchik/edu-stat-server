package ru.cmo.edu.data.entity;

import javax.persistence.*;
import java.util.Collection;

/**
 * Created by to on 06.06.2017.
 */
@Entity
@Table(name = "region", schema = "public", catalog = "edu_forms_test")
public class RegionEntity {
    private int regionId;
    private String name;
    private Collection<RegionFormDataEntity> regionFormDatas;

    @Id
    @Column(name = "region_id")
    public int getRegionId() {
        return regionId;
    }

    public void setRegionId(int regionId) {
        this.regionId = regionId;
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

        RegionEntity that = (RegionEntity) o;

        if (regionId != that.regionId) return false;
        if (name != null ? !name.equals(that.name) : that.name != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = regionId;
        result = 31 * result + (name != null ? name.hashCode() : 0);
        return result;
    }

    @OneToMany(mappedBy = "regionByRegionId", fetch = FetchType.LAZY)
    public Collection<RegionFormDataEntity> getRegionFormDatas() {
        return regionFormDatas;
    }

    public void setRegionFormDatas(Collection<RegionFormDataEntity> regionFormDatasByRegionId) {
        this.regionFormDatas = regionFormDatasByRegionId;
    }
}
