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
        List<Edu> list = eduRepository.findAll();
        return toDto(clazz, list);
    }

    public <T extends EduCoreDto> List<T> getAllDto(Class<T> clazz, int municipalityId) {
        List<Edu> list = eduRepository.findAll(municipalityId);
        return toDto(clazz, list);
    }

    public <T extends EduCoreDto> List<T> getAllDto(Class<T> clazz, int municipalityId, int eduKindId) {
        List<Edu> list = eduRepository.findAll(municipalityId, eduKindId);
        return toDto(clazz, list);
    }

    public <T extends EduCoreDto> List<T> getAllByFormDto(Class<T> clazz, Integer municipalityId, int formId, int year) {
        List<Edu> list = null;
        if (municipalityId == null) {
            list = eduRepository.findAllByForm(formId, year);
        } else {
            list = eduRepository.findAllByForm(municipalityId, formId, year);
        }
        return toDto(clazz, list);
    }

    public <T extends EduCoreDto> T getDto(int id, Class<T> clazz) {
        Edu edu = eduRepository.findById(id);
        return toDto(clazz, edu);
    }


    private <T extends EduCoreDto> List<T> toDto(Class<T> clazz, List<Edu> list) {
        List<T> dtos = list.stream().map(t -> {
            return toDto(clazz, t);
        }).collect(Collectors.toList());
        return dtos;
    }

    private <T extends EduCoreDto> T toDto(Class<T> clazz, Edu edu) {
        try {
            return clazz.getDeclaredConstructor(Edu.class).newInstance(edu);
        } catch (InstantiationException | IllegalAccessException | InvocationTargetException | NoSuchMethodException e) {
            logger.error(e.getMessage(), e);
            return (T) new EduCoreDto(edu);
        }
    }
}
