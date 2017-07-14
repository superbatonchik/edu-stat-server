package ru.cmo.edu.data.dto;

import ru.cmo.edu.data.entity.Edu;

/**
 * Created by to on 12.07.2017.
 */
public class EduCoreDto {
    private int id;
    private String fullname;
    private String name;
    private Integer municipalityId;
    private Integer eduKindId;
    private Integer eduNumber;
    private String sysName;

    public EduCoreDto(Edu e) {
        this.id = e.getId();
        this.fullname = e.getFullname();
        this.name = e.getName();
        this.municipalityId = e.getMunicipalityId();
        this.eduKindId = e.getEduKindId();
        this.eduNumber = e.getEduNumber();
        this.sysName = e.getSysName();
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getFullname() {
        return fullname;
    }

    public void setFullname(String fullname) {
        this.fullname = fullname;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getMunicipalityId() {
        return municipalityId;
    }

    public void setMunicipalityId(Integer municipalityId) {
        this.municipalityId = municipalityId;
    }

    public Integer getEduKindId() {
        return eduKindId;
    }

    public void setEduKindId(Integer eduKindId) {
        this.eduKindId = eduKindId;
    }

    public Integer getEduNumber() {
        return eduNumber;
    }

    public void setEduNumber(Integer eduNumber) {
        this.eduNumber = eduNumber;
    }

    public String getSysName() {
        return sysName;
    }

    public void setSysName(String sysName) {
        this.sysName = sysName;
    }
}
