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
        return municipalityRepository.findAll().stream().map(t -> {
            try {
                return clazz.getDeclaredConstructor(Municipality.class).newInstance(t);
            } catch (InstantiationException | IllegalAccessException | InvocationTargetException | NoSuchMethodException e) {
                logger.error(e.getMessage(), e);
                return (T)(new MunicipalityCoreDto(t));
            }
        }).collect(Collectors.toList());
    }

    public <T extends MunicipalityCoreDto> List<T> getAllDto(Class<T> clazz, int regionId) {
        return municipalityRepository.findAll(regionId).stream().map(t -> {
            try {
                return clazz.getDeclaredConstructor(Municipality.class).newInstance(t);
            } catch (InstantiationException | IllegalAccessException | InvocationTargetException | NoSuchMethodException e) {
                logger.error(e.getMessage(), e);
                return (T)(new MunicipalityCoreDto(t));
            }
        }).collect(Collectors.toList());
    }

    public <T extends MunicipalityCoreDto> T getDto(int id, Class<T> clazz) {
        Municipality municipality = municipalityRepository.findById(id);
        try {
            return clazz.getDeclaredConstructor(Municipality.class).newInstance(municipality);
        } catch (IllegalAccessException | InstantiationException | NoSuchMethodException | InvocationTargetException e) {
            logger.error(e.getMessage(), e);
            return (T)new MunicipalityCoreDto(municipality);
        }
    }
}
