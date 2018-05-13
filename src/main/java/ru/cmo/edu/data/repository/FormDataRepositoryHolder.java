package ru.cmo.edu.data.repository;

import org.springframework.stereotype.Component;
import ru.cmo.edu.data.entity.enumerable.FormTypeEnum;

@Component
public class FormDataRepositoryHolder {

    private final FormDataRepository eduFormDataRepository;
    private final FormDataRepository municipalityFormDataRepository;
    private final FormDataRepository regionFormDataRepository;

    public FormDataRepositoryHolder(EduFormDataRepository eduFormDataRepository,
                                    MunicipalityFormDataRepository municipalityFormDataRepository,
                                    RegionFormDataRepository regionFormDataRepository) {
        this.eduFormDataRepository = eduFormDataRepository;
        this.municipalityFormDataRepository = municipalityFormDataRepository;
        this.regionFormDataRepository = regionFormDataRepository;
    }

    public FormDataRepository get(FormTypeEnum formType) {
        if (FormTypeEnum.isEduType(formType)) {
            return eduFormDataRepository;
        }
        if (FormTypeEnum.isMunicipalityType(formType)) {
            return municipalityFormDataRepository;
        }
        if (FormTypeEnum.isRegionType(formType)) {
            return regionFormDataRepository;
        }
        throw new IllegalArgumentException(String.format("Form type %s not found", formType.toString()));
    }
}
