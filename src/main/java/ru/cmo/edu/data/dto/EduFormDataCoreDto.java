package ru.cmo.edu.data.dto;

import ru.cmo.edu.data.entity.EduFormData;

/**
 * Created by to on 14.07.2017.
 */
public class EduFormDataCoreDto extends BaseFormDataCoreDto {

    private int eduId;

    public EduFormDataCoreDto(EduFormData f) {
        super(f);
        this.eduId = f.getEduId();
    }

    public int getEduId() {
        return eduId;
    }

    public void setEduId(int eduId) {
        this.eduId = eduId;
    }
}
