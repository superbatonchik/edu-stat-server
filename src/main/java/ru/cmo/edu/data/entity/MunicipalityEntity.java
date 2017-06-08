package ru.cmo.edu.data.entity;

import com.fasterxml.jackson.annotation.JsonManagedReference;

import javax.persistence.*;
import java.util.Collection;

/**
 * Created by to on 06.06.2017.
 */
@Entity
@Table(name = "municipality", schema = "public", catalog = "edu_forms_test")
public class MunicipalityEntity {
    private int municipalityId;
    private String name;
    private String operator;
    private String remark;
    private String phone;
    private String selfManagementAgency;
    private String selfManagementAgencyDirector;
    private String directorPhone;
    private Collection<EduEntity> edus;
    private Collection<MunicipalityFormDataEntity> municipalityFormDatas;

    @Id
    @Column(name = "municipality_id")
    public int getMunicipalityId() {
        return municipalityId;
    }

    public void setMunicipalityId(int municipalityId) {
        this.municipalityId = municipalityId;
    }

    @Basic
    @Column(name = "name")
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Basic
    @Column(name = "operator")
    public String getOperator() {
        return operator;
    }

    public void setOperator(String operator) {
        this.operator = operator;
    }

    @Basic
    @Column(name = "remark")
    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }

    @Basic
    @Column(name = "phone")
    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    @Basic
    @Column(name = "self_management_agency")
    public String getSelfManagementAgency() {
        return selfManagementAgency;
    }

    public void setSelfManagementAgency(String selfManagementAgency) {
        this.selfManagementAgency = selfManagementAgency;
    }

    @Basic
    @Column(name = "self_management_agency_director")
    public String getSelfManagementAgencyDirector() {
        return selfManagementAgencyDirector;
    }

    public void setSelfManagementAgencyDirector(String selfManagementAgencyDirector) {
        this.selfManagementAgencyDirector = selfManagementAgencyDirector;
    }

    @Basic
    @Column(name = "director_phone")
    public String getDirectorPhone() {
        return directorPhone;
    }

    public void setDirectorPhone(String directorPhone) {
        this.directorPhone = directorPhone;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        MunicipalityEntity that = (MunicipalityEntity) o;

        if (municipalityId != that.municipalityId) return false;
        if (name != null ? !name.equals(that.name) : that.name != null) return false;
        if (operator != null ? !operator.equals(that.operator) : that.operator != null) return false;
        if (remark != null ? !remark.equals(that.remark) : that.remark != null) return false;
        if (phone != null ? !phone.equals(that.phone) : that.phone != null) return false;
        if (selfManagementAgency != null ? !selfManagementAgency.equals(that.selfManagementAgency) : that.selfManagementAgency != null)
            return false;
        if (selfManagementAgencyDirector != null ? !selfManagementAgencyDirector.equals(that.selfManagementAgencyDirector) : that.selfManagementAgencyDirector != null)
            return false;
        if (directorPhone != null ? !directorPhone.equals(that.directorPhone) : that.directorPhone != null)
            return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = municipalityId;
        result = 31 * result + (name != null ? name.hashCode() : 0);
        result = 31 * result + (operator != null ? operator.hashCode() : 0);
        result = 31 * result + (remark != null ? remark.hashCode() : 0);
        result = 31 * result + (phone != null ? phone.hashCode() : 0);
        result = 31 * result + (selfManagementAgency != null ? selfManagementAgency.hashCode() : 0);
        result = 31 * result + (selfManagementAgencyDirector != null ? selfManagementAgencyDirector.hashCode() : 0);
        result = 31 * result + (directorPhone != null ? directorPhone.hashCode() : 0);
        return result;
    }

    @JsonManagedReference
    @OneToMany(mappedBy = "municipality", fetch = FetchType.LAZY)
    public Collection<EduEntity> getEdus() {
        return edus;
    }

    public void setEdus(Collection<EduEntity> edusByMunicipalityId) {
        this.edus = edusByMunicipalityId;
    }

    @OneToMany(mappedBy = "municipalityByMunicipalityId", fetch = FetchType.LAZY)
    public Collection<MunicipalityFormDataEntity> getMunicipalityFormDatas() {
        return municipalityFormDatas;
    }

    public void setMunicipalityFormDatas(Collection<MunicipalityFormDataEntity> municipalityFormDatasByMunicipalityId) {
        this.municipalityFormDatas = municipalityFormDatasByMunicipalityId;
    }
}
