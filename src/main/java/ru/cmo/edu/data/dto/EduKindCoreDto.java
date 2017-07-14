package ru.cmo.edu.data.dto;

import ru.cmo.edu.data.entity.EduKind;

/**
 * Created by to on 12.07.2017.
 */
public class EduKindCoreDto {
    private int id;
    private String name;

    public EduKindCoreDto(EduKind e) {
        id = e.getId();
        name = e.getName();
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
