package ru.cmo.edu.data.dto;

import ru.cmo.edu.data.entity.MunicipalityFormData;

/**
 * Created by to on 14.07.2017.
 */
public class MunicipalityFormDataCoreDto extends BaseFormDataCoreDto {

    private int municipalityId;

    public MunicipalityFormDataCoreDto(MunicipalityFormData f) {
        super(f);
        this.municipalityId = f.getMunicipalityId();
    }

    public int getMunicipalityId() {
        return municipalityId;
    }

    public void setMunicipalityId(int municipalityId) {
        this.municipalityId = municipalityId;
    }
}
