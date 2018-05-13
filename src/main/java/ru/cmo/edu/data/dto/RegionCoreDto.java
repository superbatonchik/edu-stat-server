package ru.cmo.edu.data.dto;

import ru.cmo.edu.data.entity.Region;

/**
 * Created by to on 12.07.2017.
 */
public class RegionCoreDto {
    private int id;
    private String name;

    public RegionCoreDto() {
    }

    public RegionCoreDto(Region r) {
        id = r.getId();
        name = r.getName();
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
