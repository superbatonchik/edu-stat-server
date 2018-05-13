package ru.cmo.edu.data.repository;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import ru.cmo.edu.data.entity.RegionFormData;

import java.util.List;

/**
 * Created by to on 06.06.2017.
 */

@Repository
public interface RegionFormDataRepository extends FormDataRepository<RegionFormData, Integer> {

    @Override
    @Query("select rf from RegionFormData rf join rf.form f " +
            "where rf.regionId = ?1 and f.formTypeId = ?2 and ((YEAR(rf.sendDate) = YEAR(CURRENT_DATE) and true = ?3) or (YEAR(rf.sendDate) < YEAR(CURRENT_DATE) and true = ?3)) " +
            "order by rf.sendDate desc")
    List<RegionFormData> findAll(int orgId, int formTypeId, boolean isArchived);

    @Query("select rf from RegionFormData rf join rf.form f " +
            "where rf.regionId = ?1 and f.formTypeId = ?2 and YEAR(rf.sendDate) = ?3 " +
            "order by rf.sendDate desc")
    List<RegionFormData> findExact(int orgId, int formId, int year);
}
