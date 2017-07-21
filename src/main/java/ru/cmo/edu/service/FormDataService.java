package ru.cmo.edu.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.cmo.edu.data.dto.EduCoreDto;
import ru.cmo.edu.data.dto.EduFormDataCoreDto;
import ru.cmo.edu.data.dto.MunicipalityFormDataCoreDto;
import ru.cmo.edu.data.dto.RegionFormDataCoreDto;
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

    @Autowired
    private RegionFormDataRepository regionFormDataRepository;

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

    public List<MunicipalityFormDataCoreDto> getMunicipalityFormDataDto(int municipalityId, boolean isArchived) {
        List<MunicipalityFormData> municipalityFormDatas;
        if (isArchived) {
            municipalityFormDatas = municipalityFormDataRepository.findAllArchived(municipalityId);
        } else {
            municipalityFormDatas = municipalityFormDataRepository.findAll(municipalityId);
        }
        List<MunicipalityFormDataCoreDto> dtos = municipalityFormDatas.stream().map(MunicipalityFormDataCoreDto::new).collect(Collectors.toList());
        return dtos;
    }

    public List<RegionFormDataCoreDto> getRegionFormDataDto(int regionId, boolean isArchived) {
        List<RegionFormData> regionFormDatas;
        if (isArchived) {
            regionFormDatas = regionFormDataRepository.findAllArchived(regionId);
        } else {
            regionFormDatas = regionFormDataRepository.findAll(regionId);
        }
        List<RegionFormDataCoreDto> dtos = regionFormDatas.stream().map(RegionFormDataCoreDto::new).collect(Collectors.toList());
        return dtos;
    }
}
