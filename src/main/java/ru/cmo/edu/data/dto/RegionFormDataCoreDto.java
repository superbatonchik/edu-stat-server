package ru.cmo.edu.data.dto;

import ru.cmo.edu.data.entity.RegionFormData;

/**
 * Created by to on 14.07.2017.
 */
public class RegionFormDataCoreDto extends BaseFormDataCoreDto {

    private int regionId;

    public RegionFormDataCoreDto(RegionFormData f) {
        super(f);
        this.regionId = f.getRegionId();
    }

    public int getRegionId() {
        return regionId;
    }

    public void setRegionId(int regionId) {
        this.regionId = regionId;
    }
}
