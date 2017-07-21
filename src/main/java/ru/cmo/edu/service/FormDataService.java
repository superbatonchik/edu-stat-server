package ru.cmo.edu.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.cmo.edu.data.dto.EduCoreDto;
import ru.cmo.edu.data.dto.EduFormDataCoreDto;
import ru.cmo.edu.data.entity.*;
import ru.cmo.edu.data.repository.*;

import java.util.Calendar;
import java.util.List;
import java.util.stream.Collectors;

/**
 * Created by to on 11.07.2017.
 */
@Service
public class FormDataService {

    @Autowired
    private EduFormDataRepository eduFormDataRepository;

    @Autowired
    private MunicipalityFormDataRepository municipalityFormDataRepository;

    public List<EduFormDataCoreDto> getEduFormDataDto(int eduId, boolean isArchived) {
        List<EduFormData> eduFormDatas;
        if (isArchived) {
             eduFormDatas = eduFormDataRepository.findAllArchived(eduId);
        } else {
            eduFormDatas = eduFormDataRepository.findAll(eduId);
        }
        List<EduFormDataCoreDto> dtos = eduFormDatas.stream().map(EduFormDataCoreDto::new).collect(Collectors.toList());
        return dtos;
    }
}
