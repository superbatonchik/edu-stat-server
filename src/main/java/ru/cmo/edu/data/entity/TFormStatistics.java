package ru.cmo.edu.data.entity;

import javax.persistence.*;

/**
 * Created by to on 11.07.2017.
 */
@Entity
@Table(name = "t_form_statistics", schema = "public", catalog = "edu_forms_test")
public class TFormStatistics {
    private long rowId;
    private int munitId;
    private String munitName;
    private int eduId;
    private String orgShortname;
    private int year;
    private long overallCount;
    private long okCount;
    private long expiredCount;
    private long errorCount;
    private long badCount;
    private long loadedCount;

    @Id
    @Column(name = "row_id")
    public long getRowId() {
        return rowId;
    }

    public void setRowId(long rowId) {
        this.rowId = rowId;
    }

    @Basic
    @Column(name = "munit_id")
    public int getMunitId() {
        return munitId;
    }

    public void setMunitId(int munitId) {
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
    public int getEduId() {
        return eduId;
    }

    public void setEduId(int eduId) {
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
    public int getYear() {
        return year;
    }

    public void setYear(int year) {
        this.year = year;
    }

    @Basic
    @Column(name = "overall_count")
    public long getOverallCount() {
        return overallCount;
    }

    public void setOverallCount(long overallCount) {
        this.overallCount = overallCount;
    }

    @Basic
    @Column(name = "ok_count")
    public long getOkCount() {
        return okCount;
    }

    public void setOkCount(long okCount) {
        this.okCount = okCount;
    }

    @Basic
    @Column(name = "expired_count")
    public long getExpiredCount() {
        return expiredCount;
    }

    public void setExpiredCount(long expiredCount) {
        this.expiredCount = expiredCount;
    }

    @Basic
    @Column(name = "error_count")
    public long getErrorCount() {
        return errorCount;
    }

    public void setErrorCount(long errorCount) {
        this.errorCount = errorCount;
    }

    @Basic
    @Column(name = "bad_count")
    public long getBadCount() {
        return badCount;
    }

    public void setBadCount(long badCount) {
        this.badCount = badCount;
    }

    @Basic
    @Column(name = "loaded_count")
    public long getLoadedCount() {
        return loadedCount;
    }

    public void setLoadedCount(long loadedCount) {
        this.loadedCount = loadedCount;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        TFormStatistics that = (TFormStatistics) o;

        if (rowId != that.rowId) return false;
        if (munitId != that.munitId) return false;
        if (eduId != that.eduId) return false;
        if (year != that.year) return false;
        if (overallCount != that.overallCount) return false;
        if (okCount != that.okCount) return false;
        if (expiredCount != that.expiredCount) return false;
        if (errorCount != that.errorCount) return false;
        if (badCount != that.badCount) return false;
        if (loadedCount != that.loadedCount) return false;
        if (munitName != null ? !munitName.equals(that.munitName) : that.munitName != null) return false;
        if (orgShortname != null ? !orgShortname.equals(that.orgShortname) : that.orgShortname != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = (int) (rowId ^ (rowId >>> 32));
        result = 31 * result + munitId;
        result = 31 * result + (munitName != null ? munitName.hashCode() : 0);
        result = 31 * result + eduId;
        result = 31 * result + (orgShortname != null ? orgShortname.hashCode() : 0);
        result = 31 * result + year;
        result = 31 * result + (int) (overallCount ^ (overallCount >>> 32));
        result = 31 * result + (int) (okCount ^ (okCount >>> 32));
        result = 31 * result + (int) (expiredCount ^ (expiredCount >>> 32));
        result = 31 * result + (int) (errorCount ^ (errorCount >>> 32));
        result = 31 * result + (int) (badCount ^ (badCount >>> 32));
        result = 31 * result + (int) (loadedCount ^ (loadedCount >>> 32));
        return result;
    }
}
