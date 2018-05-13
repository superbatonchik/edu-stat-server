package ru.cmo.edu.data;

import org.springframework.stereotype.Component;
import ru.cmo.edu.data.entity.*;
import ru.cmo.edu.data.entity.enumerable.FormTypeEnum;

@Component
public class FormDataFactory {

    public BaseFormData create(FormTypeEnum formType) {
        if (FormTypeEnum.isEduType(formType)) {
            return new EduFormData();
        }
        if (FormTypeEnum.isMunicipalityType(formType)) {
            return new MunicipalityFormData();
        }
        if (FormTypeEnum.isRegionType(formType)) {
            return new RegionFormData();
        }
        throw new IllegalArgumentException("Form type not found");
    }

    public BaseFormData create(FormTypeEnum formType, int organizationId) {
        BaseFormData formData = create(formType);
        if (FormTypeEnum.isEduType(formType)) {
            EduFormData eduFormData = (EduFormData) formData;
            eduFormData.setEduId(organizationId);
            return eduFormData;
        }
        if (FormTypeEnum.isMunicipalityType(formType)) {
            MunicipalityFormData municipalityFormData = (MunicipalityFormData) formData;
            municipalityFormData.setMunicipalityId(organizationId);
            return municipalityFormData;
        }
        if (FormTypeEnum.isRegionType(formType)) {
            RegionFormData regionFormData = (RegionFormData) formData;
            regionFormData.setRegionId(organizationId);
            return regionFormData;
        }
        throw new IllegalArgumentException("Form type not found");
    }
}
