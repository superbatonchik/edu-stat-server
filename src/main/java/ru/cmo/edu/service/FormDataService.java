package ru.cmo.edu.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.cmo.edu.config.Messages;
import ru.cmo.edu.data.FormDataFactory;
import ru.cmo.edu.data.dto.FormDataCoreDto;
import ru.cmo.edu.data.entity.*;
import ru.cmo.edu.data.entity.enumerable.DocumentFormatEnum;
import ru.cmo.edu.data.entity.enumerable.FormStatusEnum;
import ru.cmo.edu.data.entity.enumerable.FormTypeEnum;
import ru.cmo.edu.data.repository.*;

import javax.persistence.PersistenceException;
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
    private final FormDataRepositoryHolder formDataRepositoryHolder;
    private final FormDataFactory formDataFactory;
    private final Messages strings;

    @Autowired
    public FormDataService(EduFormDataRepository eduFormDataRepository,
                           MunicipalityFormDataRepository municipalityFormDataRepository,
                           RegionFormDataRepository regionFormDataRepository,
                           FormStatusRepository formStatusRepository,
                           FormDataRepositoryHolder formDataRepositoryHolder,
                           FormDataFactory formDataFactory,
                           Messages strings) {
        this.eduFormDataRepository = eduFormDataRepository;
        this.municipalityFormDataRepository = municipalityFormDataRepository;
        this.regionFormDataRepository = regionFormDataRepository;
        this.formStatusRepository = formStatusRepository;
        this.formDataRepositoryHolder = formDataRepositoryHolder;
        this.formDataFactory = formDataFactory;
        this.strings = strings;
    }

    public void editFormData(FormDataCoreDto formData) throws PersistenceException {
        logger.info("Started creating or updating form data...");

        int formTypeId = formData.getForm().getFormTypeId();
        FormTypeEnum formType = FormTypeEnum.valueOf(formTypeId);
        BaseFormData data = formDataFactory.create(formType, formData.getOrganizationId());
        data.setDocumentFormatId(DocumentFormatEnum.value(formData.getDocumentFormat()).getIntValue());
        data.setFileId(formData.getFileId());
        data.setFormId(formData.getForm().getId());
        data.setSendDate(formData.getSendDate());
        data.setStatus(formData.getStatus());
        if (formData.getId() > 0) {
            logger.info("...editing form data {}", formData.getId());
            data.setId(formData.getId());
        } else {
            logger.info("...creating new form data");
        }
        logger.debug(data.toString());
        FormDataRepository formDataRepository = formDataRepositoryHolder.get(formType);
        if (FormTypeEnum.isEduType(formType)) {
            EduFormData eData = (EduFormData) data;
            formDataRepository.save(eData);
        } else if (FormTypeEnum.isMunicipalityType(formType)) {
            MunicipalityFormData eData = (MunicipalityFormData) data;
            formDataRepository.save(eData);
        } else if (FormTypeEnum.isRegionType(formType)) {
            RegionFormData eData = (RegionFormData) data;
            formDataRepository.save(eData);
        }
        formData.setId(data.getId());
        logger.error("...completed successfully");
    }

    @SuppressWarnings("unchecked")
    public List<FormDataCoreDto> getFormDataDto(int organizationId, FormTypeEnum formType, boolean isArchived) {
        FormDataRepository repository = formDataRepositoryHolder.get(formType);
        List forms = repository.findAll(organizationId, formType.getValue(), isArchived);
        List<FormDataCoreDto> dtos = (List<FormDataCoreDto>) forms.stream().map(t -> new FormDataCoreDto((BaseFormData)t, organizationId)).collect(Collectors.toList());
        return dtos;
    }

    @SuppressWarnings("unchecked")
    public List<FormDataCoreDto> getFormDataDto(int organizationId, int formId, FormTypeEnum formType, int year) {
        logger.info("Searching form id: {}, year: {} for {} id: {}", formId, year, formType, organizationId);
        FormDataRepository repository = formDataRepositoryHolder.get(formType);
        List forms = repository.findExact(organizationId, formId, year);
        List<FormDataCoreDto> dtos = (List<FormDataCoreDto>) forms.stream().map(t -> new FormDataCoreDto((BaseFormData)t, organizationId)).collect(Collectors.toList());
        return dtos;
    }

    public Map<Integer, Integer> getMunicipalityStatusForEdu(int regionId, boolean isArchived) {
        List<FormStatus> statuses = formStatusRepository.getMunicipalityStatusForEduView(/*regionId*/ isArchived);
        return makeDict(statuses);
    }

    public Map<Integer, Integer> getEduKindStatusForEdu(int regionId, int municipalityId, boolean isArchived) {
        List<FormStatus> statuses = formStatusRepository.getEduKindStatusForEduView(/*regionId*/municipalityId, isArchived);
        return makeDict(statuses);
    }

    public Map<Integer, Integer> getEduStatus(int regionId, int municipalityId, int eduKindId, boolean isArchived) {
        List<FormStatus> statuses = formStatusRepository.getEduStatus(/*regionId*/municipalityId, eduKindId, isArchived);
        return makeDict(statuses);
    }

    public Map<Integer, Integer> getMunicipalityStatus(int regionId, boolean isArchived) {
        List<FormStatus> statuses = formStatusRepository.getMunicipalityStatus(/*regionId*/ isArchived);
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
