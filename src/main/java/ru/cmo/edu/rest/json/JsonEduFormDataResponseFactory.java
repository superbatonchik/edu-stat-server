package ru.cmo.edu.rest.json;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.cmo.edu.data.entity.EduEntity;
import ru.cmo.edu.data.entity.EduFormDataEntity;
import ru.cmo.edu.data.entity.EduKindEntity;
import ru.cmo.edu.data.entity.MunicipalityEntity;
import ru.cmo.edu.data.repository.EduEntityRepository;
import ru.cmo.edu.data.repository.EduKindEntityRepository;
import ru.cmo.edu.data.repository.MunicipalityEntityRepository;
import ru.cmo.edu.data.repository.RegionEntityRepository;

import java.time.LocalDate;
import java.util.*;
import java.util.stream.Collectors;

/**
 * Created by to on 07.06.2017.
 */

@Service
public class JsonEduFormDataResponseFactory {

    @Autowired
    EduEntityRepository eduEntityRepository;
    @Autowired
    MunicipalityEntityRepository municipalityEntityRepository;
    @Autowired
    RegionEntityRepository regionEntityRepository;
    @Autowired
    EduKindEntityRepository eduKindEntityRepository;

    public Map<String, Object> createMunicipalityListForRegion(int regionId) {
        Map<String, Object> obj = new HashMap<>();
        ArrayList<Map<String, Object>> municipalityJArray = new ArrayList<>();
        for (MunicipalityEntity m : municipalityEntityRepository.findAll()) {
            Map<String, Object> mObj = new HashMap<String, Object>() {
                {
                    put("id", m.getMunicipalityId());
                    put("name", m.getName());
                }
            };
            municipalityJArray.add(mObj);
        }
        obj.put("municipalities", municipalityJArray);
        return obj;
    }

    public Map<String, Object> createEduKindListByMunicipalityForRegion(int municipalityId) {
        MunicipalityEntity municipality = municipalityEntityRepository.findOne(municipalityId);
        Map<String, Object> obj = new HashMap<String, Object>() {
            {
                put("municipality",new HashMap<String, Object>() {
                    {
                        put("id", municipality.getMunicipalityId());
                        put("name", municipality.getName());
                        put("eduKinds", new ArrayList<Map<String, Object>>());
                    }
                });
                put("eduKinds", new ArrayList<Map<String, Object>>());
            }
        };
        for (EduKindEntity ek : eduKindEntityRepository.findByMunicipality(municipality)) {
            Map<String, Object> ekObj = new HashMap<String, Object>() {
                {
                    put("id", ek.getEduKindId());
                    put("name", ek.getName());
                }
            };
            ((ArrayList<Map<String, Object>>) obj.get("eduKinds")).add(ekObj);
        }
        return obj;
    }

    public Map<String, Object> createEduListByEduKindForRegion(int municipalityId, int eduKindId) {
        MunicipalityEntity municipality = municipalityEntityRepository.findOne(municipalityId);
        EduKindEntity eduKind = eduKindEntityRepository.findOne(eduKindId);

        Map<String, Object> obj = new HashMap<String, Object>() {
            {
                put("municipality", new HashMap<String, Object>() {
                    {
                        put("id", municipality.getMunicipalityId());
                        put("name", municipality.getName());
                    }
                });
                put("eduKind", new HashMap<String, Object>() {
                    {
                        put("id", eduKind.getEduKindId());
                        put("name", eduKind.getName());
                    }
                });
                put("edus", new ArrayList<Map<String, Object>>());
            }
        };
        for (EduEntity e : eduEntityRepository.findByMunicipalityAndEduKind(municipality, eduKind)) {
            Map<String, Object> eObj = new HashMap<String, Object>() {
                {
                    put("id", e.getEduId());
                    put("name", e.getName());
                }
            };
            ((ArrayList<Map<String, Object>>) obj.get("edus")).add(eObj);
        }
        return obj;
    }

    public Map<String, Object> createEduListForRegionResponse(int regionId) {
        //TODO: sdelat region i municipality relationship
        Map<String, Object> obj = new HashMap<>();
        ArrayList<Map<String, Object>> municipalityJArray = new ArrayList<>();
        for (MunicipalityEntity m : municipalityEntityRepository.findAll()) {
            Map<String, Object> mObj = new HashMap<String, Object>() {
                {
                    put("id", m.getMunicipalityId());
                    put("name", m.getName());
                    put("eduKinds", new ArrayList<Map<String, Object>>());
                }
            };

            for (EduKindEntity ek : eduKindEntityRepository.findByMunicipality(m)) {
                Map<String, Object> ekObj = new HashMap<String, Object>() {
                    {
                        put("id", ek.getEduKindId());
                        put("name", ek.getName());
                        put("edus", new ArrayList<Map<String, Object>>());
                    }
                };
                ((ArrayList<Map<String, Object>>)mObj.get("eduKinds")).add(ekObj);
                for (EduEntity e : ek.getEdus()) {
                    Map<String, Object> eObj = new HashMap<String, Object>() {
                        {
                            put("id", e.getEduId());
                            put("name", String.format("%s № %d", e.getName(), e.getEduNumber()));
                            put("formData", new ArrayList<Map<String, Object>>());
                        }
                    };
                    ((ArrayList<Map<String, Object>>)ekObj.get("edus")).add(eObj);
                    List<EduFormDataEntity> eduFormDataEntityList = e.getEduFormDatas()
                            .stream()
                            .filter(t -> t.getSendDate().toLocalDate().getYear() == LocalDate.now().getYear())
                            .collect(Collectors.toList());
                    for (EduFormDataEntity efd : eduFormDataEntityList) {
                        Map<String, Object> efdObj = new HashMap<String, Object>() {
                            {
                                put("id", efd.getId());
                                put("form",  new HashMap<String, Object>(){
                                    {
                                        put("id", efd.getForm().getFormId());
                                        put("name", efd.getForm().getName());
                                        put("submissionDate", efd.getForm().getSubmissionDate());
                                    }
                                });
                                put("sent", efd.getSendDate());
                                put("status", efd.getStatus());
                            }
                        };
                        ((ArrayList<Map<String, Object>>)eObj.get("formData")).add(efdObj);
                    }
                }
            }
            municipalityJArray.add(mObj);
        }
        obj.put("municipalities", municipalityJArray);
        return obj;
    }
}
