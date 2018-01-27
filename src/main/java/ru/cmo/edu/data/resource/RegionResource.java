package ru.cmo.edu.data.resource;

import com.fasterxml.jackson.annotation.JsonProperty;
import org.springframework.hateoas.ResourceSupport;
import ru.cmo.edu.data.dto.RegionCoreDto;

/**
 * Created by to on 12.07.2017.
 */
public class RegionResource extends BaseResource {
    private RegionCoreDto regionCoreDto;

    public RegionResource(RegionCoreDto dto) {
        this.regionCoreDto = dto;
    }

    @JsonProperty("object")
    public RegionCoreDto getRegionCoreDto() {
        return regionCoreDto;
    }

    public void setRegionCoreDto(RegionCoreDto regionCoreDto) {
        this.regionCoreDto = regionCoreDto;
    }
}
