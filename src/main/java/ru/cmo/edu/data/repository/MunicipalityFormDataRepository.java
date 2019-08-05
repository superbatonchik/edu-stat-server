package ru.cmo.edu.data.repository;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import ru.cmo.edu.data.entity.MunicipalityFormData;

import java.util.List;

/**
 * Created by to on 06.06.2017.
 */

@Repository
public interface MunicipalityFormDataRepository extends FormDataRepository<MunicipalityFormData, Integer> {

    @Override
    @Query("select mf from MunicipalityFormData mf join mf.form f " +
            "where mf.municipalityId = ?1 and f.formTypeId = ?2 and ((YEAR(mf.sendDate) = YEAR(CURRENT_DATE) and false = ?3) or (YEAR(mf.sendDate) < YEAR(CURRENT_DATE) and true = ?3)) " +
            "order by mf.sendDate desc")
    List<MunicipalityFormData> findAll(int orgId, int formTypeId, boolean isArchived);

    @Override
    @Query("select mf from MunicipalityFormData mf join mf.form f " +
            "where mf.municipalityId = ?1 and f.formTypeId = ?2 and YEAR(mf.sendDate) = ?3 " +
            "order by mf.sendDate desc")
    List<MunicipalityFormData> findExact(int orgId, int formId, int year);
}
