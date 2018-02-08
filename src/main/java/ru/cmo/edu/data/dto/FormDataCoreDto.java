package ru.cmo.edu.data.dto;

import ru.cmo.edu.data.entity.BaseFormData;
import ru.cmo.edu.data.entity.EduFormData;
import ru.cmo.edu.data.entity.MunicipalityFormData;
import ru.cmo.edu.data.entity.RegionFormData;

/**
 * Created by to on 14.07.2017.
 */
public class FormDataCoreDto extends BaseFormDataCoreDto {

    private int organizationId;

    public FormDataCoreDto(EduFormData f) {
        super(f);
        this.organizationId = f.getEduId();
    }

    public FormDataCoreDto(MunicipalityFormData f) {
        super(f);
        this.organizationId = f.getMunicipalityId();
    }

    public FormDataCoreDto(RegionFormData f) {
        super(f);
        this.organizationId = f.getRegionId();
    }

    public int getOrganizationId() {
        return organizationId;
    }

    public void setOrganizationId(int organizationId) {
        this.organizationId = organizationId;
    }
}
