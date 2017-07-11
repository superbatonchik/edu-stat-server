package ru.cmo.edu.data.entity;

import javax.persistence.*;

/**
 * Created by to on 11.07.2017.
 */
@Entity
@Table(name = "t_summary_form_statistics", schema = "public", catalog = "edu_forms_test")
public class TSummaryFormStatistics {
    private long rowId;
    private int municipalityId;
    private String municipalityTitle;
    private long overallCount;
    private long loadedCount;
    private long okCount;
    private long expiredCount;
    private long errorCount;
    private long badCount;

    @Id
    @Column(name = "row_id")
    public long getRowId() {
        return rowId;
    }

    public void setRowId(long rowId) {
        this.rowId = rowId;
    }

    @Basic
    @Column(name = "municipality_id")
    public int getMunicipalityId() {
        return municipalityId;
    }

    public void setMunicipalityId(int municipalityId) {
        this.municipalityId = municipalityId;
    }

    @Basic
    @Column(name = "municipality_title")
    public String getMunicipalityTitle() {
        return municipalityTitle;
    }

    public void setMunicipalityTitle(String municipalityTitle) {
        this.municipalityTitle = municipalityTitle;
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
    @Column(name = "loaded_count")
    public long getLoadedCount() {
        return loadedCount;
    }

    public void setLoadedCount(long loadedCount) {
        this.loadedCount = loadedCount;
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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        TSummaryFormStatistics that = (TSummaryFormStatistics) o;

        if (rowId != that.rowId) return false;
        if (municipalityId != that.municipalityId) return false;
        if (overallCount != that.overallCount) return false;
        if (loadedCount != that.loadedCount) return false;
        if (okCount != that.okCount) return false;
        if (expiredCount != that.expiredCount) return false;
        if (errorCount != that.errorCount) return false;
        if (badCount != that.badCount) return false;
        if (municipalityTitle != null ? !municipalityTitle.equals(that.municipalityTitle) : that.municipalityTitle != null)
            return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = (int) (rowId ^ (rowId >>> 32));
        result = 31 * result + municipalityId;
        result = 31 * result + (municipalityTitle != null ? municipalityTitle.hashCode() : 0);
        result = 31 * result + (int) (overallCount ^ (overallCount >>> 32));
        result = 31 * result + (int) (loadedCount ^ (loadedCount >>> 32));
        result = 31 * result + (int) (okCount ^ (okCount >>> 32));
        result = 31 * result + (int) (expiredCount ^ (expiredCount >>> 32));
        result = 31 * result + (int) (errorCount ^ (errorCount >>> 32));
        result = 31 * result + (int) (badCount ^ (badCount >>> 32));
        return result;
    }
}
