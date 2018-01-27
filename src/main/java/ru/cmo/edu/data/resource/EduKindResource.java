package ru.cmo.edu.data.resource;

import com.fasterxml.jackson.annotation.JsonProperty;
import org.springframework.hateoas.ResourceSupport;
import ru.cmo.edu.data.dto.EduKindCoreDto;

/**
 * Created by to on 12.07.2017.
 */
public class EduKindResource extends BaseResource {
    private EduKindCoreDto eduKindDto;

    public EduKindResource(EduKindCoreDto dto) {
        this.eduKindDto = dto;
    }

    @JsonProperty("object")
    public EduKindCoreDto getEduKindDto() {
        return eduKindDto;
    }

    public void setEduKindDto(EduKindCoreDto eduKindDto) {
        this.eduKindDto = eduKindDto;
    }
}
