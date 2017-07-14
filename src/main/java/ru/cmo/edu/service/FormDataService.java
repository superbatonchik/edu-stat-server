package ru.cmo.edu.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.cmo.edu.data.entity.Edu;
import ru.cmo.edu.data.entity.EduFormData;
import ru.cmo.edu.data.entity.EduKind;
import ru.cmo.edu.data.entity.Municipality;
import ru.cmo.edu.data.repository.*;

import java.util.Calendar;
import java.util.List;

/**
 * Created by to on 11.07.2017.
 */
@Service
public class FormDataService {
    @Autowired
    private EduRepository eduRepository;
    @Autowired
    private MunicipalityRepository municipalityRepository;
    @Autowired
    private EduFormDataRepository eduFormDataRepository;
    @Autowired
    private EduKindRepository eduKindRepository;

    public List<Municipality> getMunicipalities(int regionId) {
        List<Municipality> municipalityList = municipalityRepository.findAll();
        return municipalityList;
    }

    public List<EduKind> getEduKinds(int municipalityId) {
        List<EduKind> eduKinds = eduKindRepository.findAll(municipalityId);
        return eduKinds;
    }

    public List<Edu> getEdus(int municipalityId, int eduKindId) {
        List<Edu> edus = eduRepository.findAll(municipalityId, eduKindId);
        return edus;
    }

    public List<EduFormData> getEduFormDatas(int eduId, boolean isArchived) {
        List<EduFormData> eduFormDatas;
        if (isArchived) {
             eduFormDatas = eduFormDataRepository.findAllArchived(eduId);
        } else {
            eduFormDatas = eduFormDataRepository.findAll(eduId);
        }
        return eduFormDatas;
    }
}
