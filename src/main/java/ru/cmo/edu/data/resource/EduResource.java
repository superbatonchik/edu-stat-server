package ru.cmo.edu.data.resource;

import com.fasterxml.jackson.annotation.JsonProperty;
import org.springframework.hateoas.ResourceSupport;
import ru.cmo.edu.data.dto.EduCoreDto;

/**
 * Created by to on 12.07.2017.
 */
public class EduResource extends ResourceSupport {
    private EduCoreDto eduDto;

    public EduResource(EduCoreDto dto) {
        this.eduDto = dto;
    }

    @JsonProperty("object")
    public EduCoreDto getKindDto() {
        return eduDto;
    }

    public void setEduDto(EduCoreDto eduDto) {
        this.eduDto = eduDto;
    }
}
