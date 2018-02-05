package ru.cmo.edu.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.cmo.edu.data.dto.EduCoreDto;
import ru.cmo.edu.data.entity.Edu;
import ru.cmo.edu.data.repository.EduRepository;


import java.lang.reflect.InvocationTargetException;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class EduService {

    private Logger logger = LoggerFactory.getLogger(EduKindService.class);

    private final EduRepository eduRepository;

    @Autowired
    public EduService(EduRepository eduRepository) {
        this.eduRepository = eduRepository;
    }

    public <T extends EduCoreDto> List<T> getAllDto(Class<T> clazz) {
        return eduRepository.findAll().stream().map(t -> {
            try {
                return clazz.getDeclaredConstructor(Edu.class).newInstance(t);
            } catch (InstantiationException | IllegalAccessException | InvocationTargetException | NoSuchMethodException e) {
                logger.error(e.getMessage(), e);
                return (T) new EduCoreDto(t);
            }
        }).collect(Collectors.toList());
    }

    public <T extends EduCoreDto> List<T> getAllDto(Class<T> clazz, int municipalityId) {
        return eduRepository.findAll(municipalityId).stream().map(t -> {
            try {
                return clazz.getDeclaredConstructor(Edu.class).newInstance(t);
            } catch (InstantiationException | IllegalAccessException | InvocationTargetException | NoSuchMethodException e) {
                logger.error(e.getMessage(), e);
                return (T) new EduCoreDto(t);
            }
        }).collect(Collectors.toList());
    }

    public <T extends EduCoreDto> List<T> getAllDto(Class<T> clazz, int municipalityId, int eduKindId) {
        return eduRepository.findAll(municipalityId, eduKindId).stream().map(t -> {
            try {
                return clazz.getDeclaredConstructor(Edu.class).newInstance(t);
            } catch (InstantiationException | IllegalAccessException | InvocationTargetException | NoSuchMethodException e) {
                logger.error(e.getMessage(), e);
                return (T) new EduCoreDto(t);
            }
        }).collect(Collectors.toList());
    }

    public <T extends EduCoreDto> T getDto(int id, Class<T> clazz) {
        Edu edu = eduRepository.findById(id);
        try {
            return clazz.getDeclaredConstructor(Edu.class).newInstance(edu);
        } catch (IllegalAccessException | InstantiationException | NoSuchMethodException | InvocationTargetException e) {
            logger.error(e.getMessage(), e);
            return (T)new EduCoreDto(edu);
        }
    }
}
