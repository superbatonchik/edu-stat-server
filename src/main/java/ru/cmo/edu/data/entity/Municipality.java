package ru.cmo.edu.data.entity;

import javax.persistence.*;
import java.util.Set;

/**
 * Created by to on 11.07.2017.
 */
@Entity
@Table(name = "municipality", schema = "public", catalog = "edu_forms_test")
public class Municipality extends BaseUser {
    private String operator;
    private String remark;
    private String phone;
    private String selfManagementAgency;
    private String selfManagementAgencyDirector;
    private String directorPhone;
    private Set<Edu> edus;
    private Set<MunicipalityFormData> municipalityFormDatas;
    private Set<Form> blockedForms;

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

        Municipality that = (Municipality) o;

        if (super.getId() != that.getId()) return false;
        if (super.getName() != null ? !super.getName().equals(that.getName()) : that.getName() != null) return false;
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
        int result = super.hashCode();
        result = 31 * result + (operator != null ? operator.hashCode() : 0);
        result = 31 * result + (remark != null ? remark.hashCode() : 0);
        result = 31 * result + (phone != null ? phone.hashCode() : 0);
        result = 31 * result + (selfManagementAgency != null ? selfManagementAgency.hashCode() : 0);
        result = 31 * result + (selfManagementAgencyDirector != null ? selfManagementAgencyDirector.hashCode() : 0);
        result = 31 * result + (directorPhone != null ? directorPhone.hashCode() : 0);
        return result;
    }

    @OneToMany(mappedBy = "municipality")
    public Set<Edu> getEdus() {
        return edus;
    }

    public void setEdus(Set<Edu> edus) {
        this.edus = edus;
    }

    @OneToMany(mappedBy = "municipality")
    public Set<MunicipalityFormData> getMunicipalityFormDatas() {
        return municipalityFormDatas;
    }

    public void setMunicipalityFormDatas(Set<MunicipalityFormData> municipalityFormDatas) {
        this.municipalityFormDatas = municipalityFormDatas;
    }

    @ManyToMany
    @JoinTable(name = "mm_municipality__blocked_form", catalog = "edu_forms_test", schema = "public", joinColumns = @JoinColumn(name = "municipality_id", referencedColumnName = "id", nullable = false), inverseJoinColumns = @JoinColumn(name = "form_id", referencedColumnName = "id", nullable = false))
    public Set<Form> getBlockedForms() {
        return blockedForms;
    }

    public void setBlockedForms(Set<Form> blockedForms) {
        this.blockedForms = blockedForms;
    }
}
