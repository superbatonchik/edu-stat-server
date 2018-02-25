package ru.cmo.edu.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import ru.cmo.edu.data.dto.MunicipalityCoreDto;
import ru.cmo.edu.data.entity.Municipality;
import ru.cmo.edu.data.repository.MunicipalityRepository;

import java.lang.reflect.InvocationTargetException;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class MunicipalityService {

    private Logger logger = LoggerFactory.getLogger(MunicipalityService.class);

    private final MunicipalityRepository municipalityRepository;

    @Autowired
    public MunicipalityService(MunicipalityRepository municipalityRepository) {
        this.municipalityRepository = municipalityRepository;
    }

    public <T extends MunicipalityCoreDto> List<T> getAllDto(Class<T> clazz) {
        List<Municipality> list = municipalityRepository.findAll();
        return toDto(clazz, list);
    }

    public <T extends MunicipalityCoreDto> List<T> getAllDto(Class<T> clazz, int regionId) {
        List<Municipality> list = municipalityRepository.findAll(regionId);
        return toDto(clazz, list);
    }

    public <T extends MunicipalityCoreDto> T getDto(int id, Class<T> clazz) {
        Municipality municipality = municipalityRepository.findById(id);
        return toDto(clazz, municipality);
    }

    public <T extends MunicipalityCoreDto> List<T> getAllByFormDto(Class<T> clazz, int formId, int year) {
        List<Municipality> list = municipalityRepository.findAllByForm(formId, year);
        return toDto(clazz, list);
    }

    private <T extends MunicipalityCoreDto> List<T> toDto(Class<T> clazz, List<Municipality> list) {
        List<T> dtos = list.stream().map(t -> {
            return toDto(clazz, t);
        }).collect(Collectors.toList());
        return dtos;
    }

    private <T extends MunicipalityCoreDto> T toDto(Class<T> clazz, Municipality municipality) {
        try {
            return clazz.getDeclaredConstructor(Municipality.class).newInstance(municipality);
        } catch (InstantiationException | IllegalAccessException | InvocationTargetException | NoSuchMethodException e) {
            logger.error(e.getMessage(), e);
            return (T) new MunicipalityCoreDto(municipality);
        }
    }
}
