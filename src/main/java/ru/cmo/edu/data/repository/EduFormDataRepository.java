package ru.cmo.edu.data.repository;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import ru.cmo.edu.data.entity.EduFormData;

import java.util.List;

/**
 * Created by to on 06.06.2017.
 */

@Repository
public interface EduFormDataRepository extends CrudRepository<EduFormData, Integer> {

    @Query("select ef from EduFormData ef " +
            "where ef.eduId = ?1 and YEAR(ef.sendDate) = YEAR(CURRENT_DATE) " +
            "order by ef.sendDate desc")
    List<EduFormData> findAll(int eduId);

    @Query("select ef from EduFormData ef " +
            "where ef.eduId = ?1 and YEAR(ef.sendDate) < YEAR(CURRENT_DATE) " +
            "order by ef.sendDate desc")
    List<EduFormData> findAllArchived(int eduId);
}
