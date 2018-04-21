package ru.cmo.edu.rest.security;

import ru.cmo.edu.data.entity.enumerable.FormTypeEnum;

/**
 * Created by to on 12.07.2017.
 */
public enum Role {
    region,
    ministry,
    municipality,
    edu;

    public boolean canUpload(int formType) {
        switch (this) {
            case region:
                return formType == FormTypeEnum.REGION || formType == FormTypeEnum.ADD_REGION;
            case municipality:
                return formType == FormTypeEnum.MUNICIPALITY || formType == FormTypeEnum.ADD_MUNICIPALITY;
            case edu:
                return formType == FormTypeEnum.EDU || formType == FormTypeEnum.ADD_EDU;
        }
        return false;
    }
}
