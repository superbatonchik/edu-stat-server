package ru.cmo.edu.data.resource;

import com.fasterxml.jackson.annotation.JsonProperty;
import ru.cmo.edu.data.dto.BaseFormDataCoreDto;
import ru.cmo.edu.data.dto.EduKindCoreDto;
import ru.cmo.edu.data.dto.FormDataCoreDto;

/**
 * Created by to on 12.07.2017.
 */
public class FormDataResource extends BaseResource {
    private FormDataCoreDto formDataDto;

    public FormDataResource(FormDataCoreDto dto) {
        this.formDataDto = dto;
    }

    @JsonProperty("object")
    public FormDataCoreDto getFormDataDto() {
        return formDataDto;
    }

    public void setFormDataDto(FormDataCoreDto formDataDto) {
        this.formDataDto = formDataDto;
    }
}
