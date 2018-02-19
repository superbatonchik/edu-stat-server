package ru.cmo.edu.data.entity;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.Collection;
import java.util.Set;

/**
 * Created by to on 11.07.2017.
 */
@Entity
@Table(name = "edu", schema = "public", catalog = "edu_forms_test")
public class Edu extends BaseUser {
    private String fullname;
    private String operator;
    private Integer municipalityId;
    private Integer eduKindId;
    private Integer statusId;
    private String code;
    private String okpoCode;
    private LocalDateTime licenseDate;
    private String licenseFrom;
    private LocalDateTime accreditationDate;
    private String accreditationFrom;
    private Integer ownershipTypeId;
    private Integer eduTypeId;
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
    private LocalDateTime accreditationEndDate;
    private LocalDateTime licenseEndDate;
    private String kpp;
    private String ogrn;
    private String inn;
    private Integer eduNumber;
    private String sysName;
    private Integer settlementTypeId;
    private Municipality municipality;
    private EduKind eduKind;
    private EduStatus eduStatus;
    private OwnershipType ownershipType;
    private EduType eduType;
    private SettlementType settlementType;
    private Set<EduFormData> eduFormDatas;
    private Collection<Message> messages;
    private Set<ActivityType> activityTypes;
    private Set<Form> blockedForms;
    private Set<Form> hiddenForms;
    private Set<ManagementAgency> managementAgencies;
    private Set<ManagementAgencyActivity> managementAgencyActivities;

    @Basic
    @Column(name = "fullname")
    public String getFullname() {
        return fullname;
    }

    public void setFullname(String fullname) {
        this.fullname = fullname;
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
    @Column(name = "municipality_id", insertable = false, updatable = false)
    public Integer getMunicipalityId() {
        return municipalityId;
    }

    public void setMunicipalityId(Integer municipalityId) {
        this.municipalityId = municipalityId;
    }

    @Basic
    @Column(name = "edu_kind_id", insertable = false, updatable = false)
    public Integer getEduKindId() {
        return eduKindId;
    }

    public void setEduKindId(Integer eduKindId) {
        this.eduKindId = eduKindId;
    }

    @Basic
    @Column(name = "status_id", insertable = false, updatable = false)
    public Integer getStatusId() {
        return statusId;
    }

    public void setStatusId(Integer statusId) {
        this.statusId = statusId;
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
    public LocalDateTime getLicenseDate() {
        return licenseDate;
    }

    public void setLicenseDate(LocalDateTime licenseDate) {
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
    public LocalDateTime getAccreditationDate() {
        return accreditationDate;
    }

    public void setAccreditationDate(LocalDateTime accreditationDate) {
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
    @Column(name = "ownership_type_id", insertable = false, updatable = false)
    public Integer getOwnershipTypeId() {
        return ownershipTypeId;
    }

    public void setOwnershipTypeId(Integer ownershipTypeId) {
        this.ownershipTypeId = ownershipTypeId;
    }

    @Basic
    @Column(name = "edu_type_id", insertable = false, updatable = false)
    public Integer getEduTypeId() {
        return eduTypeId;
    }

    public void setEduTypeId(Integer eduTypeId) {
        this.eduTypeId = eduTypeId;
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
    public LocalDateTime getAccreditationEndDate() {
        return accreditationEndDate;
    }

    public void setAccreditationEndDate(LocalDateTime accreditationEndDate) {
        this.accreditationEndDate = accreditationEndDate;
    }

    @Basic
    @Column(name = "license_end_date")
    public LocalDateTime getLicenseEndDate() {
        return licenseEndDate;
    }

    public void setLicenseEndDate(LocalDateTime licenseEndDate) {
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

    @Basic
    @Column(name = "settlement_type_id", insertable = false, updatable = false)
    public Integer getSettlementTypeId() {
        return settlementTypeId;
    }

    public void setSettlementTypeId(Integer settlementTypeId) {
        this.settlementTypeId = settlementTypeId;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Edu edu = (Edu) o;

        if (super.getId() != edu.getId()) return false;
        if (isUngraded != edu.isUngraded) return false;
        if (branchNum != edu.branchNum) return false;
        if (managementAgencyHasAuthority != edu.managementAgencyHasAuthority) return false;
        if (fullname != null ? !fullname.equals(edu.fullname) : edu.fullname != null) return false;
        if (super.getName() != null ? !super.getName().equals(edu.getName()) : edu.getName() != null) return false;
        if (operator != null ? !operator.equals(edu.operator) : edu.operator != null) return false;
        if (municipalityId != null ? !municipalityId.equals(edu.municipalityId) : edu.municipalityId != null)
            return false;
        if (eduKindId != null ? !eduKindId.equals(edu.eduKindId) : edu.eduKindId != null) return false;
        if (statusId != null ? !statusId.equals(edu.statusId) : edu.statusId != null) return false;
        if (code != null ? !code.equals(edu.code) : edu.code != null) return false;
        if (okpoCode != null ? !okpoCode.equals(edu.okpoCode) : edu.okpoCode != null) return false;
        if (licenseDate != null ? !licenseDate.equals(edu.licenseDate) : edu.licenseDate != null) return false;
        if (licenseFrom != null ? !licenseFrom.equals(edu.licenseFrom) : edu.licenseFrom != null) return false;
        if (accreditationDate != null ? !accreditationDate.equals(edu.accreditationDate) : edu.accreditationDate != null)
            return false;
        if (accreditationFrom != null ? !accreditationFrom.equals(edu.accreditationFrom) : edu.accreditationFrom != null)
            return false;
        if (ownershipTypeId != null ? !ownershipTypeId.equals(edu.ownershipTypeId) : edu.ownershipTypeId != null)
            return false;
        if (eduTypeId != null ? !eduTypeId.equals(edu.eduTypeId) : edu.eduTypeId != null) return false;
        if (director != null ? !director.equals(edu.director) : edu.director != null) return false;
        if (post != null ? !post.equals(edu.post) : edu.post != null) return false;
        if (phone != null ? !phone.equals(edu.phone) : edu.phone != null) return false;
        if (email != null ? !email.equals(edu.email) : edu.email != null) return false;
        if (link != null ? !link.equals(edu.link) : edu.link != null) return false;
        if (accreditationNum != null ? !accreditationNum.equals(edu.accreditationNum) : edu.accreditationNum != null)
            return false;
        if (licenseNum != null ? !licenseNum.equals(edu.licenseNum) : edu.licenseNum != null) return false;
        if (accreditationEndDate != null ? !accreditationEndDate.equals(edu.accreditationEndDate) : edu.accreditationEndDate != null)
            return false;
        if (licenseEndDate != null ? !licenseEndDate.equals(edu.licenseEndDate) : edu.licenseEndDate != null)
            return false;
        if (kpp != null ? !kpp.equals(edu.kpp) : edu.kpp != null) return false;
        if (ogrn != null ? !ogrn.equals(edu.ogrn) : edu.ogrn != null) return false;
        if (inn != null ? !inn.equals(edu.inn) : edu.inn != null) return false;
        if (eduNumber != null ? !eduNumber.equals(edu.eduNumber) : edu.eduNumber != null) return false;
        if (sysName != null ? !sysName.equals(edu.sysName) : edu.sysName != null) return false;
        if (settlementTypeId != null ? !settlementTypeId.equals(edu.settlementTypeId) : edu.settlementTypeId != null)
            return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = super.hashCode();
        result = 31 * result + (fullname != null ? fullname.hashCode() : 0);
        result = 31 * result + (operator != null ? operator.hashCode() : 0);
        result = 31 * result + (municipalityId != null ? municipalityId.hashCode() : 0);
        result = 31 * result + (eduKindId != null ? eduKindId.hashCode() : 0);
        result = 31 * result + (statusId != null ? statusId.hashCode() : 0);
        result = 31 * result + (code != null ? code.hashCode() : 0);
        result = 31 * result + (okpoCode != null ? okpoCode.hashCode() : 0);
        result = 31 * result + (licenseDate != null ? licenseDate.hashCode() : 0);
        result = 31 * result + (licenseFrom != null ? licenseFrom.hashCode() : 0);
        result = 31 * result + (accreditationDate != null ? accreditationDate.hashCode() : 0);
        result = 31 * result + (accreditationFrom != null ? accreditationFrom.hashCode() : 0);
        result = 31 * result + (ownershipTypeId != null ? ownershipTypeId.hashCode() : 0);
        result = 31 * result + (eduTypeId != null ? eduTypeId.hashCode() : 0);
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
        result = 31 * result + (settlementTypeId != null ? settlementTypeId.hashCode() : 0);
        return result;
    }

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "municipality_id", referencedColumnName = "id")
    public Municipality getMunicipality() {
        return municipality;
    }

    public void setMunicipality(Municipality municipality) {
        this.municipality = municipality;
    }

    @ManyToOne
    @JoinColumn(name = "edu_kind_id", referencedColumnName = "id")
    public EduKind getEduKind() {
        return eduKind;
    }

    public void setEduKind(EduKind eduKind) {
        this.eduKind = eduKind;
    }

    @ManyToOne
    @JoinColumn(name = "status_id", referencedColumnName = "id")
    public EduStatus getEduStatus() {
        return eduStatus;
    }

    public void setEduStatus(EduStatus eduStatus) {
        this.eduStatus = eduStatus;
    }

    @ManyToOne
    @JoinColumn(name = "ownership_type_id", referencedColumnName = "id")
    public OwnershipType getOwnershipType() {
        return ownershipType;
    }

    public void setOwnershipType(OwnershipType ownershipType) {
        this.ownershipType = ownershipType;
    }

    @ManyToOne
    @JoinColumn(name = "edu_type_id", referencedColumnName = "id")
    public EduType getEduType() {
        return eduType;
    }

    public void setEduType(EduType eduType) {
        this.eduType = eduType;
    }

    @ManyToOne
    @JoinColumn(name = "settlement_type_id", referencedColumnName = "id")
    public SettlementType getSettlementType() {
        return settlementType;
    }

    public void setSettlementType(SettlementType settlementType) {
        this.settlementType = settlementType;
    }

    @OneToMany(mappedBy = "edu")
    public Set<EduFormData> getEduFormDatas() {
        return eduFormDatas;
    }

    public void setEduFormDatas(Set<EduFormData> eduFormDatas) {
        this.eduFormDatas = eduFormDatas;
    }

    @OneToMany(mappedBy = "edu")
    public Collection<Message> getMessages() {
        return messages;
    }

    public void setMessages(Collection<Message> messages) {
        this.messages = messages;
    }

    @ManyToMany
    @JoinTable(name = "mm_edu__activity_type", catalog = "edu_forms_test", schema = "public", joinColumns = @JoinColumn(name = "edu_id", referencedColumnName = "id", nullable = false), inverseJoinColumns = @JoinColumn(name = "activity_type_id", referencedColumnName = "id", nullable = false))
    public Set<ActivityType> getActivityTypes() {
        return activityTypes;
    }

    public void setActivityTypes(Set<ActivityType> activityTypes) {
        this.activityTypes = activityTypes;
    }

    @ManyToMany
    @JoinTable(name = "mm_edu__blocked_form", catalog = "edu_forms_test", schema = "public", joinColumns = @JoinColumn(name = "edu_id", referencedColumnName = "id", nullable = false), inverseJoinColumns = @JoinColumn(name = "form_id", referencedColumnName = "id", nullable = false))
    public Set<Form> getBlockedForms() {
        return blockedForms;
    }

    public void setBlockedForms(Set<Form> blockedForms) {
        this.blockedForms = blockedForms;
    }

    @ManyToMany
    @JoinTable(name = "mm_edu__hidden_form", catalog = "edu_forms_test", schema = "public", joinColumns = @JoinColumn(name = "edu_id", referencedColumnName = "id", nullable = false), inverseJoinColumns = @JoinColumn(name = "form_id", referencedColumnName = "id", nullable = false))
    public Set<Form> getHiddenForms() {
        return hiddenForms;
    }

    public void setHiddenForms(Set<Form> hiddenForms) {
        this.hiddenForms = hiddenForms;
    }

    @ManyToMany
    @JoinTable(name = "mm_edu__management_agency", catalog = "edu_forms_test", schema = "public", joinColumns = @JoinColumn(name = "edu_id", referencedColumnName = "id", nullable = false), inverseJoinColumns = @JoinColumn(name = "management_agency_id", referencedColumnName = "id", nullable = false))
    public Set<ManagementAgency> getManagementAgencies() {
        return managementAgencies;
    }

    public void setManagementAgencies(Set<ManagementAgency> managementAgencies) {
        this.managementAgencies = managementAgencies;
    }

    @ManyToMany
    @JoinTable(name = "mm_edu__management_agency_activity", catalog = "edu_forms_test", schema = "public", joinColumns = @JoinColumn(name = "edu_id", referencedColumnName = "id", nullable = false), inverseJoinColumns = @JoinColumn(name = "management_agency_activity_id", referencedColumnName = "id", nullable = false))
    public Set<ManagementAgencyActivity> getManagementAgencyActivities() {
        return managementAgencyActivities;
    }

    public void setManagementAgencyActivities(Set<ManagementAgencyActivity> managementAgencyActivities) {
        this.managementAgencyActivities = managementAgencyActivities;
    }
}
