package ru.cmo.edu.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.cmo.edu.data.dto.EduFormDataCoreDto;
import ru.cmo.edu.data.dto.MunicipalityFormDataCoreDto;
import ru.cmo.edu.data.dto.RegionFormDataCoreDto;
import ru.cmo.edu.data.entity.*;
import ru.cmo.edu.data.repository.*;

import java.util.List;
import java.util.stream.Collectors;

/**
 * Created by to on 11.07.2017.
 */
@Service
public class FormDataService {

    private final EduFormDataRepository eduFormDataRepository;

    private final MunicipalityFormDataRepository municipalityFormDataRepository;

    private final RegionFormDataRepository regionFormDataRepository;

    @Autowired
    public FormDataService(EduFormDataRepository eduFormDataRepository, MunicipalityFormDataRepository municipalityFormDataRepository, RegionFormDataRepository regionFormDataRepository) {
        this.eduFormDataRepository = eduFormDataRepository;
        this.municipalityFormDataRepository = municipalityFormDataRepository;
        this.regionFormDataRepository = regionFormDataRepository;
    }

    public List<EduFormDataCoreDto> getEduFormDataDto(int eduId, int formTypeId, boolean isArchived) {
        List<EduFormData> eduFormDatas;
        if (isArchived) {
             eduFormDatas = eduFormDataRepository.findAllArchived(eduId, formTypeId);
        } else {
            eduFormDatas = eduFormDataRepository.findAll(eduId, formTypeId);
        }
        List<EduFormDataCoreDto> dtos = eduFormDatas.stream().map(EduFormDataCoreDto::new).collect(Collectors.toList());
        return dtos;
    }

    public List<MunicipalityFormDataCoreDto> getMunicipalityFormDataDto(int municipalityId, int formTypeId, boolean isArchived) {
        List<MunicipalityFormData> municipalityFormDatas;
        if (isArchived) {
            municipalityFormDatas = municipalityFormDataRepository.findAllArchived(municipalityId, formTypeId);
        } else {
            municipalityFormDatas = municipalityFormDataRepository.findAll(municipalityId, formTypeId);
        }
        List<MunicipalityFormDataCoreDto> dtos = municipalityFormDatas.stream().map(MunicipalityFormDataCoreDto::new).collect(Collectors.toList());
        return dtos;
    }

    public List<RegionFormDataCoreDto> getRegionFormDataDto(int regionId, int formTypeId, boolean isArchived) {
        List<RegionFormData> regionFormDatas;
        if (isArchived) {
            regionFormDatas = regionFormDataRepository.findAllArchived(regionId, formTypeId);
        } else {
            regionFormDatas = regionFormDataRepository.findAll(regionId, formTypeId);
        }
        List<RegionFormDataCoreDto> dtos = regionFormDatas.stream().map(RegionFormDataCoreDto::new).collect(Collectors.toList());
        return dtos;
    }
}
