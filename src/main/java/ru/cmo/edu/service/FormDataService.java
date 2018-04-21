package ru.cmo.edu.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.cmo.edu.config.Messages;
import ru.cmo.edu.data.dto.FormDataCoreDto;
import ru.cmo.edu.data.entity.*;
import ru.cmo.edu.data.entity.enumerable.DocumentFormatEnum;
import ru.cmo.edu.data.entity.enumerable.FormStatusEnum;
import ru.cmo.edu.data.entity.enumerable.FormTypeEnum;
import ru.cmo.edu.data.repository.*;

import javax.persistence.PersistenceException;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

/**
 * Created by to on 11.07.2017.
 */
@Service
public class FormDataService {

    private Logger logger = LoggerFactory.getLogger(FormDataService.class);

    private final EduFormDataRepository eduFormDataRepository;

    private final MunicipalityFormDataRepository municipalityFormDataRepository;

    private final RegionFormDataRepository regionFormDataRepository;

    private final FormStatusRepository formStatusRepository;
    private final Messages strings;


    @Autowired
    public FormDataService(EduFormDataRepository eduFormDataRepository, MunicipalityFormDataRepository municipalityFormDataRepository,
                           RegionFormDataRepository regionFormDataRepository, FormStatusRepository formStatusRepository,
                           Messages strings) {
        this.eduFormDataRepository = eduFormDataRepository;
        this.municipalityFormDataRepository = municipalityFormDataRepository;
        this.regionFormDataRepository = regionFormDataRepository;
        this.formStatusRepository = formStatusRepository;
        this.strings = strings;
    }

    public List<FormDataCoreDto> getEduFormDataDto(int eduId, int formTypeId, boolean isArchived) {
        List<EduFormData> eduFormDatas;
        if (isArchived) {
             eduFormDatas = eduFormDataRepository.findAllArchived(eduId, formTypeId);
        } else {
            eduFormDatas = eduFormDataRepository.findAll(eduId, formTypeId);
        }
        List<FormDataCoreDto> dtos = eduFormDatas.stream().map(FormDataCoreDto::new).collect(Collectors.toList());
        return dtos;
    }

    public void editFormData(FormDataCoreDto formData) throws PersistenceException {
        logger.info("Started creating or updating form data...");

        BaseFormData data = new BaseFormData();
        data.setDocumentFormatId(DocumentFormatEnum.XLSX);
        data.setFileId(formData.getFileId());
        data.setFormId(formData.getForm().getId());
        data.setSendDate(LocalDateTime.now());
        data.setStatus(formData.getStatus());
        if (formData.getId() > 0) {
            logger.info("...found form data {}", formData.getId());
            data.setId(formData.getId());
        } else {
            logger.info("...created new form data {}", formData.getId());
        }
        int formTypeId = formData.getForm().getFormTypeId();
        try {
            if (formTypeId == FormTypeEnum.EDU || formTypeId == FormTypeEnum.ADD_EDU) {
                EduFormData eduFormData = (EduFormData) data;
                eduFormData.setEduId(formData.getOrganizationId());
                eduFormDataRepository.save(eduFormData);
            } else if (formTypeId == FormTypeEnum.MUNICIPALITY || formTypeId == FormTypeEnum.ADD_MUNICIPALITY) {
                MunicipalityFormData municipalityFormData = (MunicipalityFormData) data;
                municipalityFormData.setMunicipalityId(formData.getOrganizationId());
                municipalityFormDataRepository.save(municipalityFormData);
            } else if (formTypeId == FormTypeEnum.REGION || formTypeId == FormTypeEnum.ADD_REGION) {
                RegionFormData regionFormData = (RegionFormData) data;
                regionFormData.setRegionId(formData.getOrganizationId());
                regionFormDataRepository.save(regionFormData);
            } else {
                logger.error("Could not find form type {}, {}", formTypeId, formData.getForm().getFormType());
                logger.error("...completed with errors");
                throw new PersistenceException(strings.get("message.error-formtype-unavailable"));
            }
        } catch (Exception e) {
            logger.error("...completed with errors", e);
            throw new PersistenceException(strings.get("message.error-save"), e);
        }
        logger.error("...completed successfully");
    }

    public List<FormDataCoreDto> getMunicipalityFormDataDto(int municipalityId, int formTypeId, boolean isArchived) {
        List<MunicipalityFormData> municipalityFormDatas;
        if (isArchived) {
            municipalityFormDatas = municipalityFormDataRepository.findAllArchived(municipalityId, formTypeId);
        } else {
            municipalityFormDatas = municipalityFormDataRepository.findAll(municipalityId, formTypeId);
        }
        List<FormDataCoreDto> dtos = municipalityFormDatas.stream().map(FormDataCoreDto::new).collect(Collectors.toList());
        return dtos;
    }

    public List<FormDataCoreDto> getRegionFormDataDto(int regionId, int formTypeId, boolean isArchived) {
        List<RegionFormData> regionFormDatas;
        if (isArchived) {
            regionFormDatas = regionFormDataRepository.findAllArchived(regionId, formTypeId);
        } else {
            regionFormDatas = regionFormDataRepository.findAll(regionId, formTypeId);
        }
        List<FormDataCoreDto> dtos = regionFormDatas.stream().map(FormDataCoreDto::new).collect(Collectors.toList());
        return dtos;
    }

    public Map<Integer, Integer> getMunicipalityStatusForEdu(int regionId, boolean isArchived) {
        List<FormStatus> statuses;
        if (isArchived) {
            statuses = formStatusRepository.getMunicipalityStatusForEduViewArchived(/*regionId*/);
        } else {
            statuses = formStatusRepository.getMunicipalityStatusForEduView(/*regionId*/);
        }
        return makeDict(statuses);
    }

    public Map<Integer, Integer> getEduKindStatusForEdu(int regionId, int municipalityId, boolean isArchived) {
        List<FormStatus> statuses;
        if (isArchived) {
            statuses = formStatusRepository.getEduKindStatusForEduViewArchived(/*regionId*/municipalityId);
        } else {
            statuses = formStatusRepository.getEduKindStatusForEduView(/*regionId*/municipalityId);
        }
        return makeDict(statuses);
    }

    public Map<Integer, Integer> getEduStatus(int regionId, int municipalityId, int eduKindId, boolean isArchived) {
        List<FormStatus> statuses;
        if (isArchived) {
            statuses = formStatusRepository.getEduStatusArchived(/*regionId*/municipalityId, eduKindId);
        } else {
            statuses = formStatusRepository.getEduStatus(/*regionId*/municipalityId, eduKindId);
        }
        return makeDict(statuses);
    }

    public Map<Integer, Integer> getMunicipalityStatus(int regionId, boolean isArchived) {
        List<FormStatus> statuses;
        if (isArchived) {
            statuses = formStatusRepository.getMunicipalityStatusArchived(/*regionId*/);
        } else {
            statuses = formStatusRepository.getMunicipalityStatus(/*regionId*/);
        }
        return makeDict(statuses);
    }

    private Map<Integer, Integer> makeDict(List<FormStatus> statuses) {
        Map<Integer, Integer> statusDict = statuses.stream().collect(Collectors.toMap(FormStatus::getId, t -> {
            int status = FormStatusEnum.DEFAULT;

            if (t.getOk() > 0 && t.getExpired() == 0 && t.getErrors() == 0 && t.getExpiredErrors() == 0)
                status = FormStatusEnum.OK;
            else if (t.getErrors() > t.getExpired() && t.getErrors() > t.getExpiredErrors())
                status = FormStatusEnum.WITH_ERRORS;
            else if (t.getExpired() > t.getErrors() && t.getExpired() > t.getExpiredErrors())
                status = FormStatusEnum.EXPIRED;
            else if (t.getExpiredErrors() > t.getExpired() && t.getExpiredErrors() > t.getErrors())
                status = FormStatusEnum.WITH_ERRORS | FormStatusEnum.EXPIRED;
            else if (t.getOk() != 0 && (t.getExpired() !=0 || t.getErrors() != 0 || t.getExpiredErrors() != 0))
                status = FormStatusEnum.UNKNOWN;
            return status;
        }));
        return statusDict;
    }
}
