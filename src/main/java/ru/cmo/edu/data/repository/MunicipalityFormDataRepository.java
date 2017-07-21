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
public interface MunicipalityFormDataRepository extends CrudRepository<MunicipalityFormData, Integer> {

    @Query("select mf from MunicipalityFormData mf " +
            "where mf.municipalityId = ?1 and YEAR(mf.sendDate) = YEAR(CURRENT_DATE) " +
            "order by mf.sendDate desc")
    List<MunicipalityFormData> findAll(int municipalityId);

    @Query("select mf from MunicipalityFormData mf " +
            "where mf.municipalityId = ?1 and YEAR(mf.sendDate) < YEAR(CURRENT_DATE) " +
            "order by mf.sendDate desc")
    List<MunicipalityFormData> findAllArchived(int municipalityId);
}
