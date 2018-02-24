package ru.cmo.edu.data.dto;

import ru.cmo.edu.data.entity.Edu;
import ru.cmo.edu.data.entity.EduFormData;

import java.util.List;
import java.util.stream.Collectors;

public class EduWithFormDataDto extends EduCoreDto {

    private List<FormDataCoreDto> formDatas;

    public EduWithFormDataDto(Edu e) {
        super(e);
        formDatas = e.getEduFormDatas().stream().map(FormDataCoreDto::new).collect(Collectors.toList());
    }

    public List<FormDataCoreDto> getFormDatas() {
        return formDatas;
    }

    public void setFormDatas(List<FormDataCoreDto> formDatas) {
        this.formDatas = formDatas;
    }
}
