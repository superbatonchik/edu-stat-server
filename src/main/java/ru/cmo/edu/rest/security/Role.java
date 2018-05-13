package ru.cmo.edu.rest.security;

import ru.cmo.edu.data.entity.enumerable.FormTypeEnum;

public enum Role {
    region,
    ministry,
    municipality,
    edu;

    public boolean canUpload(int formType) {
        switch (this) {
            case region:
                return FormTypeEnum.isRegionType(formType);
            case municipality:
                return FormTypeEnum.isMunicipalityType(formType);
            case edu:
                return FormTypeEnum.isEduType(formType);
        }
        return false;
    }
}
