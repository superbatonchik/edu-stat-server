package ru.cmo.edu.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.cmo.edu.data.dto.FormCoreDto;
import ru.cmo.edu.data.entity.Form;
import ru.cmo.edu.data.repository.FormRepository;

import java.lang.reflect.InvocationTargetException;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

/**
 * Created by to on 11.07.2017.
 */
@Service
public class FormService {

    private Logger logger = LoggerFactory.getLogger(FormService.class);

    private final FormRepository formRepository;

    @Autowired
    public FormService(FormRepository formRepository) {
        this.formRepository = formRepository;
    }

    public <T extends FormCoreDto> List<T> getAllDto(Class<T> clazz, Integer... formTypeIds) {
        List<Form> list = formRepository.findAll(Arrays.stream(formTypeIds).collect(Collectors.toSet()));
        return toDto(clazz, list);
    }

    public <T extends FormCoreDto> List<T> getAllDtoByEdu(Class<T> clazz, int eduId) {
        List<Form> list = formRepository.findAllByEdu(eduId);
        return toDto(clazz, list);
    }

    public <T extends FormCoreDto> List<T> toDto(Class<T> clazz, List<Form> list) {
        List<T> dtos = list.stream().map(t -> {
            try {
                return clazz.getDeclaredConstructor(Form.class).newInstance(t);
            } catch (InstantiationException | IllegalAccessException | InvocationTargetException | NoSuchMethodException e) {
                logger.error(e.getMessage(), e);
                return (T) new FormCoreDto(t);
            }
        }).collect(Collectors.toList());
        return dtos;
    }
}
