package ru.cmo.edu.data.dto;

import ru.cmo.edu.data.entity.Edu;
import ru.cmo.edu.data.entity.Form;
import ru.cmo.edu.data.entity.Municipality;
import ru.cmo.edu.data.entity.MunicipalityFormData;

import javax.persistence.*;
import java.util.Set;

/**
 * Created by to on 12.07.2017.
 */
public class MunicipalityCoreDto {
    private int id;
    private String name;

    public MunicipalityCoreDto(Municipality m) {
        id = m.getId();
        name = m.getName();
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
