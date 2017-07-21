package ru.cmo.edu.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import ru.cmo.edu.data.dto.MunicipalityCoreDto;
import ru.cmo.edu.data.entity.Municipality;
import ru.cmo.edu.data.repository.MunicipalityRepository;
import ru.cmo.edu.rest.controller.EduFormDataController;

import java.lang.reflect.InvocationTargetException;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class MunicipalityService {

    private Logger logger = LoggerFactory.getLogger(MunicipalityService.class);

    @Autowired
    private MunicipalityRepository municipalityRepository;

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
}
