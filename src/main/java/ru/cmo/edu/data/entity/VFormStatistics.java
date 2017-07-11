package ru.cmo.edu.data.entity;

import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

/**
 * Created by to on 11.07.2017.
 */
@Entity
@Table(name = "form_statistics", schema = "public", catalog = "edu_forms_test")
public class VFormStatistics {
    private Long rowId;
    private Integer munitId;
    private String munitName;
    private Integer eduId;
    private String orgShortname;
    private Integer year;
    private Long overallCount;
    private Long okCount;
    private Long expiredCount;
    private Long errorCount;
    private Long badCount;
    private Long loadedCount;

    @Basic
    @Column(name = "row_id")
    public Long getRowId() {
        return rowId;
    }

    public void setRowId(Long rowId) {
        this.rowId = rowId;
    }

    @Basic
    @Column(name = "munit_id")
    public Integer getMunitId() {
        return munitId;
    }

    public void setMunitId(Integer munitId) {
        this.munitId = munitId;
    }

    @Basic
    @Column(name = "munit_name")
    public String getMunitName() {
        return munitName;
    }

    public void setMunitName(String munitName) {
        this.munitName = munitName;
    }

    @Basic
    @Column(name = "edu_id")
    public Integer getEduId() {
        return eduId;
    }

    public void setEduId(Integer eduId) {
        this.eduId = eduId;
    }

    @Basic
    @Column(name = "org_shortname")
    public String getOrgShortname() {
        return orgShortname;
    }

    public void setOrgShortname(String orgShortname) {
        this.orgShortname = orgShortname;
    }

    @Basic
    @Column(name = "year")
    public Integer getYear() {
        return year;
    }

    public void setYear(Integer year) {
        this.year = year;
    }

    @Basic
    @Column(name = "overall_count")
    public Long getOverallCount() {
        return overallCount;
    }

    public void setOverallCount(Long overallCount) {
        this.overallCount = overallCount;
    }

    @Basic
    @Column(name = "ok_count")
    public Long getOkCount() {
        return okCount;
    }

    public void setOkCount(Long okCount) {
        this.okCount = okCount;
    }

    @Basic
    @Column(name = "expired_count")
    public Long getExpiredCount() {
        return expiredCount;
    }

    public void setExpiredCount(Long expiredCount) {
        this.expiredCount = expiredCount;
    }

    @Basic
    @Column(name = "error_count")
    public Long getErrorCount() {
        return errorCount;
    }

    public void setErrorCount(Long errorCount) {
        this.errorCount = errorCount;
    }

    @Basic
    @Column(name = "bad_count")
    public Long getBadCount() {
        return badCount;
    }

    public void setBadCount(Long badCount) {
        this.badCount = badCount;
    }

    @Basic
    @Column(name = "loaded_count")
    public Long getLoadedCount() {
        return loadedCount;
    }

    public void setLoadedCount(Long loadedCount) {
        this.loadedCount = loadedCount;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        VFormStatistics that = (VFormStatistics) o;

        if (rowId != null ? !rowId.equals(that.rowId) : that.rowId != null) return false;
        if (munitId != null ? !munitId.equals(that.munitId) : that.munitId != null) return false;
        if (munitName != null ? !munitName.equals(that.munitName) : that.munitName != null) return false;
        if (eduId != null ? !eduId.equals(that.eduId) : that.eduId != null) return false;
        if (orgShortname != null ? !orgShortname.equals(that.orgShortname) : that.orgShortname != null) return false;
        if (year != null ? !year.equals(that.year) : that.year != null) return false;
        if (overallCount != null ? !overallCount.equals(that.overallCount) : that.overallCount != null) return false;
        if (okCount != null ? !okCount.equals(that.okCount) : that.okCount != null) return false;
        if (expiredCount != null ? !expiredCount.equals(that.expiredCount) : that.expiredCount != null) return false;
        if (errorCount != null ? !errorCount.equals(that.errorCount) : that.errorCount != null) return false;
        if (badCount != null ? !badCount.equals(that.badCount) : that.badCount != null) return false;
        if (loadedCount != null ? !loadedCount.equals(that.loadedCount) : that.loadedCount != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = rowId != null ? rowId.hashCode() : 0;
        result = 31 * result + (munitId != null ? munitId.hashCode() : 0);
        result = 31 * result + (munitName != null ? munitName.hashCode() : 0);
        result = 31 * result + (eduId != null ? eduId.hashCode() : 0);
        result = 31 * result + (orgShortname != null ? orgShortname.hashCode() : 0);
        result = 31 * result + (year != null ? year.hashCode() : 0);
        result = 31 * result + (overallCount != null ? overallCount.hashCode() : 0);
        result = 31 * result + (okCount != null ? okCount.hashCode() : 0);
        result = 31 * result + (expiredCount != null ? expiredCount.hashCode() : 0);
        result = 31 * result + (errorCount != null ? errorCount.hashCode() : 0);
        result = 31 * result + (badCount != null ? badCount.hashCode() : 0);
        result = 31 * result + (loadedCount != null ? loadedCount.hashCode() : 0);
        return result;
    }
}
