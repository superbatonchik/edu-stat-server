package ru.cmo.edu.data.entity;

import com.fasterxml.jackson.annotation.JsonBackReference;

import javax.persistence.*;
import java.sql.Date;
import java.util.Collection;

/**
 * Created by to on 06.06.2017.
 */
@Entity
@Table(name = "edu", schema = "public", catalog = "edu_forms_test")
public class EduEntity {
    private int eduId;
    private String fullname;
    private String name;
    private String operator;
    private String code;
    private String okpoCode;
    private Date licenseDate;
    private String licenseFrom;
    private Date accreditationDate;
    private String accreditationFrom;
    private boolean isUngraded;
    private String director;
    private String post;
    private String phone;
    private String email;
    private String link;
    private int branchNum;
    private boolean managementAgencyHasAuthority;
    private String accreditationNum;
    private String licenseNum;
    private Date accreditationEndDate;
    private Date licenseEndDate;
    private String kpp;
    private String ogrn;
    private String inn;
    private Integer eduNumber;
    private String sysName;
    private MunicipalityEntity municipality;
    private EduKindEntity eduKind;
    private EduStatusEntity eduStatus;
    private OwnershipTypeEntity ownershipType;
    private EduTypeEntity eduType;
    private SettlementTypeEntity settlementType;
    private Collection<EduFormDataEntity> eduFormDatas;
    private Collection<MessageEntity> messages;

    @Id
    @GeneratedValue
    @Column(name = "edu_id")
    public int getEduId() {
        return eduId;
    }

    public void setEduId(int eduId) {
        this.eduId = eduId;
    }

    @Basic
    @Column(name = "fullname")
    public String getFullname() {
        return fullname;
    }

    public void setFullname(String fullname) {
        this.fullname = fullname;
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
    @Column(name = "code")
    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    @Basic
    @Column(name = "okpo_code")
    public String getOkpoCode() {
        return okpoCode;
    }

    public void setOkpoCode(String okpoCode) {
        this.okpoCode = okpoCode;
    }

    @Basic
    @Column(name = "license_date")
    public Date getLicenseDate() {
        return licenseDate;
    }

    public void setLicenseDate(Date licenseDate) {
        this.licenseDate = licenseDate;
    }

    @Basic
    @Column(name = "license_from")
    public String getLicenseFrom() {
        return licenseFrom;
    }

    public void setLicenseFrom(String licenseFrom) {
        this.licenseFrom = licenseFrom;
    }

    @Basic
    @Column(name = "accreditation_date")
    public Date getAccreditationDate() {
        return accreditationDate;
    }

    public void setAccreditationDate(Date accreditationDate) {
        this.accreditationDate = accreditationDate;
    }

    @Basic
    @Column(name = "accreditation_from")
    public String getAccreditationFrom() {
        return accreditationFrom;
    }

    public void setAccreditationFrom(String accreditationFrom) {
        this.accreditationFrom = accreditationFrom;
    }

    @Basic
    @Column(name = "is_ungraded")
    public boolean isUngraded() {
        return isUngraded;
    }

    public void setUngraded(boolean ungraded) {
        isUngraded = ungraded;
    }

    @Basic
    @Column(name = "director")
    public String getDirector() {
        return director;
    }

    public void setDirector(String director) {
        this.director = director;
    }

    @Basic
    @Column(name = "post")
    public String getPost() {
        return post;
    }

    public void setPost(String post) {
        this.post = post;
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
    @Column(name = "email")
    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    @Basic
    @Column(name = "link")
    public String getLink() {
        return link;
    }

    public void setLink(String link) {
        this.link = link;
    }

    @Basic
    @Column(name = "branch_num")
    public int getBranchNum() {
        return branchNum;
    }

    public void setBranchNum(int branchNum) {
        this.branchNum = branchNum;
    }

    @Basic
    @Column(name = "management_agency_has_authority")
    public boolean isManagementAgencyHasAuthority() {
        return managementAgencyHasAuthority;
    }

    public void setManagementAgencyHasAuthority(boolean managementAgencyHasAuthority) {
        this.managementAgencyHasAuthority = managementAgencyHasAuthority;
    }

    @Basic
    @Column(name = "accreditation_num")
    public String getAccreditationNum() {
        return accreditationNum;
    }

    public void setAccreditationNum(String accreditationNum) {
        this.accreditationNum = accreditationNum;
    }

    @Basic
    @Column(name = "license_num")
    public String getLicenseNum() {
        return licenseNum;
    }

    public void setLicenseNum(String licenseNum) {
        this.licenseNum = licenseNum;
    }

    @Basic
    @Column(name = "accreditation_end_date")
    public Date getAccreditationEndDate() {
        return accreditationEndDate;
    }

    public void setAccreditationEndDate(Date accreditationEndDate) {
        this.accreditationEndDate = accreditationEndDate;
    }

    @Basic
    @Column(name = "license_end_date")
    public Date getLicenseEndDate() {
        return licenseEndDate;
    }

    public void setLicenseEndDate(Date licenseEndDate) {
        this.licenseEndDate = licenseEndDate;
    }

    @Basic
    @Column(name = "kpp")
    public String getKpp() {
        return kpp;
    }

    public void setKpp(String kpp) {
        this.kpp = kpp;
    }

    @Basic
    @Column(name = "ogrn")
    public String getOgrn() {
        return ogrn;
    }

    public void setOgrn(String ogrn) {
        this.ogrn = ogrn;
    }

    @Basic
    @Column(name = "inn")
    public String getInn() {
        return inn;
    }

    public void setInn(String inn) {
        this.inn = inn;
    }

    @Basic
    @Column(name = "edu_number")
    public Integer getEduNumber() {
        return eduNumber;
    }

    public void setEduNumber(Integer eduNumber) {
        this.eduNumber = eduNumber;
    }

    @Basic
    @Column(name = "sys_name")
    public String getSysName() {
        return sysName;
    }

    public void setSysName(String sysName) {
        this.sysName = sysName;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        EduEntity eduEntity = (EduEntity) o;

        if (eduId != eduEntity.eduId) return false;
        if (isUngraded != eduEntity.isUngraded) return false;
        if (branchNum != eduEntity.branchNum) return false;
        if (managementAgencyHasAuthority != eduEntity.managementAgencyHasAuthority) return false;
        if (fullname != null ? !fullname.equals(eduEntity.fullname) : eduEntity.fullname != null) return false;
        if (name != null ? !name.equals(eduEntity.name) : eduEntity.name != null) return false;
        if (operator != null ? !operator.equals(eduEntity.operator) : eduEntity.operator != null) return false;

        if (code != null ? !code.equals(eduEntity.code) : eduEntity.code != null) return false;
        if (okpoCode != null ? !okpoCode.equals(eduEntity.okpoCode) : eduEntity.okpoCode != null) return false;
        if (licenseDate != null ? !licenseDate.equals(eduEntity.licenseDate) : eduEntity.licenseDate != null)
            return false;
        if (licenseFrom != null ? !licenseFrom.equals(eduEntity.licenseFrom) : eduEntity.licenseFrom != null)
            return false;
        if (accreditationDate != null ? !accreditationDate.equals(eduEntity.accreditationDate) : eduEntity.accreditationDate != null)
            return false;
        if (accreditationFrom != null ? !accreditationFrom.equals(eduEntity.accreditationFrom) : eduEntity.accreditationFrom != null)
            return false;

        if (director != null ? !director.equals(eduEntity.director) : eduEntity.director != null) return false;
        if (post != null ? !post.equals(eduEntity.post) : eduEntity.post != null) return false;
        if (phone != null ? !phone.equals(eduEntity.phone) : eduEntity.phone != null) return false;
        if (email != null ? !email.equals(eduEntity.email) : eduEntity.email != null) return false;
        if (link != null ? !link.equals(eduEntity.link) : eduEntity.link != null) return false;
        if (accreditationNum != null ? !accreditationNum.equals(eduEntity.accreditationNum) : eduEntity.accreditationNum != null)
            return false;
        if (licenseNum != null ? !licenseNum.equals(eduEntity.licenseNum) : eduEntity.licenseNum != null) return false;
        if (accreditationEndDate != null ? !accreditationEndDate.equals(eduEntity.accreditationEndDate) : eduEntity.accreditationEndDate != null)
            return false;
        if (licenseEndDate != null ? !licenseEndDate.equals(eduEntity.licenseEndDate) : eduEntity.licenseEndDate != null)
            return false;
        if (kpp != null ? !kpp.equals(eduEntity.kpp) : eduEntity.kpp != null) return false;
        if (ogrn != null ? !ogrn.equals(eduEntity.ogrn) : eduEntity.ogrn != null) return false;
        if (inn != null ? !inn.equals(eduEntity.inn) : eduEntity.inn != null) return false;
        if (eduNumber != null ? !eduNumber.equals(eduEntity.eduNumber) : eduEntity.eduNumber != null) return false;
        if (sysName != null ? !sysName.equals(eduEntity.sysName) : eduEntity.sysName != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = eduId;
        result = 31 * result + (fullname != null ? fullname.hashCode() : 0);
        result = 31 * result + (name != null ? name.hashCode() : 0);
        result = 31 * result + (operator != null ? operator.hashCode() : 0);
        result = 31 * result + (code != null ? code.hashCode() : 0);
        result = 31 * result + (okpoCode != null ? okpoCode.hashCode() : 0);
        result = 31 * result + (licenseDate != null ? licenseDate.hashCode() : 0);
        result = 31 * result + (licenseFrom != null ? licenseFrom.hashCode() : 0);
        result = 31 * result + (accreditationDate != null ? accreditationDate.hashCode() : 0);
        result = 31 * result + (accreditationFrom != null ? accreditationFrom.hashCode() : 0);
        result = 31 * result + (isUngraded ? 1 : 0);
        result = 31 * result + (director != null ? director.hashCode() : 0);
        result = 31 * result + (post != null ? post.hashCode() : 0);
        result = 31 * result + (phone != null ? phone.hashCode() : 0);
        result = 31 * result + (email != null ? email.hashCode() : 0);
        result = 31 * result + (link != null ? link.hashCode() : 0);
        result = 31 * result + branchNum;
        result = 31 * result + (managementAgencyHasAuthority ? 1 : 0);
        result = 31 * result + (accreditationNum != null ? accreditationNum.hashCode() : 0);
        result = 31 * result + (licenseNum != null ? licenseNum.hashCode() : 0);
        result = 31 * result + (accreditationEndDate != null ? accreditationEndDate.hashCode() : 0);
        result = 31 * result + (licenseEndDate != null ? licenseEndDate.hashCode() : 0);
        result = 31 * result + (kpp != null ? kpp.hashCode() : 0);
        result = 31 * result + (ogrn != null ? ogrn.hashCode() : 0);
        result = 31 * result + (inn != null ? inn.hashCode() : 0);
        result = 31 * result + (eduNumber != null ? eduNumber.hashCode() : 0);
        result = 31 * result + (sysName != null ? sysName.hashCode() : 0);
        return result;
    }

    @JsonBackReference
    @ManyToOne
    @JoinColumn(name = "municipality_id", referencedColumnName = "municipality_id")
    public MunicipalityEntity getMunicipality() {
        return municipality;
    }

    public void setMunicipality(MunicipalityEntity municipalityByMunicipalityId) {
        this.municipality = municipalityByMunicipalityId;
    }

    @ManyToOne
    @JoinColumn(name = "edu_kind_id", referencedColumnName = "edu_kind_id")
    public EduKindEntity getEduKind() {
        return eduKind;
    }

    public void setEduKind(EduKindEntity eduKindByEduKindId) {
        this.eduKind = eduKindByEduKindId;
    }

    @ManyToOne
    @JoinColumn(name = "status_id", referencedColumnName = "edu_status_id")
    public EduStatusEntity getEduStatus() {
        return eduStatus;
    }

    public void setEduStatus(EduStatusEntity eduStatusByStatusId) {
        this.eduStatus = eduStatusByStatusId;
    }

    @ManyToOne
    @JoinColumn(name = "ownership_type_id", referencedColumnName = "ownership_type_id")
    public OwnershipTypeEntity getOwnershipType() {
        return ownershipType;
    }

    public void setOwnershipType(OwnershipTypeEntity ownershipTypeByOwnershipTypeId) {
        this.ownershipType = ownershipTypeByOwnershipTypeId;
    }

    @ManyToOne
    @JoinColumn(name = "edu_type_id", referencedColumnName = "edu_type_id")
    public EduTypeEntity getEduType() {
        return eduType;
    }

    public void setEduType(EduTypeEntity eduTypeByEduTypeId) {
        this.eduType = eduTypeByEduTypeId;
    }

    @ManyToOne
    @JoinColumn(name = "settlement_type_id", referencedColumnName = "settlement_type_id")
    public SettlementTypeEntity getSettlementType() {
        return settlementType;
    }

    public void setSettlementType(SettlementTypeEntity settlementTypeBySettlementTypeId) {
        this.settlementType = settlementTypeBySettlementTypeId;
    }

    @OneToMany(mappedBy = "edu", fetch = FetchType.LAZY)
    public Collection<EduFormDataEntity> getEduFormDatas() {
        return eduFormDatas;
    }

    public void setEduFormDatas(Collection<EduFormDataEntity> eduFormDatasByEduId) {
        this.eduFormDatas = eduFormDatasByEduId;
    }

    @OneToMany(mappedBy = "edu", fetch = FetchType.LAZY)
    public Collection<MessageEntity> getMessages() {
        return messages;
    }

    public void setMessages(Collection<MessageEntity> messagesByEduId) {
        this.messages = messagesByEduId;
    }
}
