package ru.cmo.edu.data.resource;

import com.fasterxml.jackson.annotation.JsonProperty;
import ru.cmo.edu.data.dto.BaseFormDataCoreDto;
import ru.cmo.edu.data.dto.EduKindCoreDto;

/**
 * Created by to on 12.07.2017.
 */
public class FormDataResource extends BaseResource {
    private BaseFormDataCoreDto formDataDto;

    public FormDataResource(BaseFormDataCoreDto dto) {
        this.formDataDto = dto;
    }

    @JsonProperty("object")
    public BaseFormDataCoreDto getFormDataDto() {
        return formDataDto;
    }

    public void setFormDataDto(BaseFormDataCoreDto formDataDto) {
        this.formDataDto = formDataDto;
    }
}
