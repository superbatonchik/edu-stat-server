package ru.cmo.edu.data.dto;

import ru.cmo.edu.data.entity.Municipality;

import java.util.List;
import java.util.stream.Collectors;

public class MunicipalityWithFormDataDto extends MunicipalityCoreDto {

    private List<FormDataCoreDto> formDatas;

    public MunicipalityWithFormDataDto(Municipality m) {
        super(m);
        formDatas = m.getMunicipalityFormDatas().stream().map(FormDataCoreDto::new).collect(Collectors.toList());
    }

    public List<FormDataCoreDto> getFormDatas() {
        return formDatas;
    }

    public void setFormDatas(List<FormDataCoreDto> formDatas) {
        this.formDatas = formDatas;
    }
}
