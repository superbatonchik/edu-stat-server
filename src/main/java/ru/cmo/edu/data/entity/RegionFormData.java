package ru.cmo.edu.data.entity;

import javax.persistence.*;

/**
 * Created by to on 11.07.2017.
 */
@Entity
@Table(name = "region_form_data", schema = "public", catalog = "edu_forms_test")
public class RegionFormData extends BaseFormData {
    private int regionId;
    private Region region;

    @Id
    @SequenceGenerator(name="region_form_data_generator", sequenceName = "region_form_data_id_seq", allocationSize=50)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "region_form_data_generator")
    @Column(name = "id")
    @Override
    public int getId() {
        return super.id;
    }

    @Override
    public void setId(int id) {
        super.id = id;
    }

    @Basic
    @Column(name = "region_id", insertable = false, updatable = false)
    public int getRegionId() {
        return regionId;
    }

    public void setRegionId(int regionId) {
        this.regionId = regionId;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        RegionFormData that = (RegionFormData) o;
        if (regionId != that.regionId && super.equals(o)) return false;
        return true;
    }

    @Override
    public int hashCode() {
        int result = super.hashCode();
        result = 31 * result + regionId;
        return result;
    }

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "region_id", referencedColumnName = "id", nullable = false)
    public Region getRegion() {
        return region;
    }

    public void setRegion(Region region) {
        this.region = region;
    }
}
