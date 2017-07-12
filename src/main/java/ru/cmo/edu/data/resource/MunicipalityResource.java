package ru.cmo.edu.data.resource;

import com.fasterxml.jackson.annotation.JsonProperty;
import org.springframework.hateoas.ResourceSupport;
import ru.cmo.edu.data.dto.MunicipalityCoreDto;

/**
 * Created by to on 12.07.2017.
 */
public class MunicipalityResource extends ResourceSupport {
    private MunicipalityCoreDto municipalityDto;

    public MunicipalityResource(MunicipalityCoreDto dto) {
        this.municipalityDto = dto;
    }

    @JsonProperty("municipality")
    public MunicipalityCoreDto getMunicipalityDto() {
        return municipalityDto;
    }

    public void setMunicipalityDto(MunicipalityCoreDto municipalityDto) {
        this.municipalityDto = municipalityDto;
    }
}
