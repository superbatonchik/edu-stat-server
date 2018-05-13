package ru.cmo.edu.data.entity;

import javax.persistence.*;

/**
 * Created by to on 11.07.2017.
 */
@Entity
@Table(name = "municipality_form_data", schema = "public", catalog = "edu_forms_test")
public class MunicipalityFormData extends BaseFormData {
    private int municipalityId;
    private Municipality municipality;

    @Id
    @SequenceGenerator(name="municipality_form_data_generator", sequenceName = "municipality_form_data_id_seq", allocationSize=50)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "municipality_form_data_generator")
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
    @Column(name = "municipality_id", insertable = false, updatable = false)
    public int getMunicipalityId() {
        return municipalityId;
    }

    public void setMunicipalityId(int municipalityId) {
        this.municipalityId = municipalityId;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        MunicipalityFormData that = (MunicipalityFormData) o;

        if (municipalityId != that.municipalityId && super.equals(o)) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = super.hashCode();
        result = 31 * result + municipalityId;
        return result;
    }

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "municipality_id", referencedColumnName = "id", nullable = false)
    public Municipality getMunicipality() {
        return municipality;
    }

    public void setMunicipality(Municipality municipality) {
        this.municipality = municipality;
    }
}
