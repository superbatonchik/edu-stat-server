package ru.cmo.edu.data.entity;

import javax.persistence.*;
import java.util.Set;

/**
 * Created by to on 11.07.2017.
 */
@Entity
public class Region {
    private int id;
    private String name;
    private Set<RegionFormData> regionFormDatas;

    @Id
    @Column(name = "id")
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
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

        Region region = (Region) o;

        if (id != region.id) return false;
        if (name != null ? !name.equals(region.name) : region.name != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = id;
        result = 31 * result + (name != null ? name.hashCode() : 0);
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
