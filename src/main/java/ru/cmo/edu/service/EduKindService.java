package ru.cmo.edu.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.cmo.edu.data.dto.EduKindCoreDto;
import ru.cmo.edu.data.entity.EduKind;
import ru.cmo.edu.data.repository.EduKindRepository;


import java.lang.reflect.InvocationTargetException;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class EduKindService {

    private Logger logger = LoggerFactory.getLogger(EduKindService.class);

    private final EduKindRepository eduKindRepository;

    @Autowired
    public EduKindService(EduKindRepository eduKindRepository) {
        this.eduKindRepository = eduKindRepository;
    }

    public <T extends EduKindCoreDto> List<T> getAllDto(Class<T> clazz) {
        List<EduKind> list =  eduKindRepository.findAll();
        return toDto(clazz, list);
    }

    public <T extends EduKindCoreDto> List<T> getAllDto(Class<T> clazz, int municipalityId) {
        List<EduKind> list = eduKindRepository.findAll(municipalityId);
        return toDto(clazz, list);
    }

    private <T extends EduKindCoreDto> List<T> toDto(Class<T> clazz, List<EduKind> list) {
        List<T> dtos = list.stream().map(t -> {
            try {
                return clazz.getDeclaredConstructor(EduKind.class).newInstance(t);
            } catch (InstantiationException | IllegalAccessException | InvocationTargetException | NoSuchMethodException e) {
                logger.error(e.getMessage(), e);
                return (T) new EduKindCoreDto(t);
            }
        }).collect(Collectors.toList());
        return dtos;
    }
}
