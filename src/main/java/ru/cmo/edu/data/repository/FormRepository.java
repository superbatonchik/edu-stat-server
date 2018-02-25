package ru.cmo.edu.data.repository;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import ru.cmo.edu.data.entity.Form;
import ru.cmo.edu.data.entity.MmRegularSummaryForm;

import java.util.List;
import java.util.Set;

/**
 * Created by to on 06.06.2017.
 */

@Repository
public interface FormRepository extends CrudRepository<Form, Integer> {

    @Query("select f from Form f " +
            "left join fetch f.formType ft " +
            "where f.formTypeId in ?1 order by f.name")
    List<Form> findAll(Set<Integer> formTypeIds);

    @Query(value = "SELECT f.* FROM " +
            "  form f, " +
            "  mm_edu_kind__form ekf, " +
            "  edu_kind ek, " +
            "  edu e " +
            "WHERE e.id = ?1 " +
            "AND f.id = ekf.form_id " +
            "AND ekf.edu_kind_id = ek.id " +
            "AND e.edu_kind_id = ek.id " +
            "AND f.id NOT IN (SELECT form_id FROM mm_edu__hidden_form WHERE edu_id = e.id)", nativeQuery = true)
    List<Form> findAllByEdu(int eduId);

    @Query(value = "SELECT bf.form_id FROM " +
            "  mm_edu__blocked_form bf " +
            "WHERE bf.edu_id = ?1", nativeQuery = true)
    List<Object[]> findBlockedIdsByEdu(int id);

    @Query(value = "SELECT bf.form_id FROM " +
            "  mm_municipality__blocked_form bf " +
            "WHERE bf.municipality_id = ?1", nativeQuery = true)
    List<Object[]> findBlockedIdsByMunicipality(int id);

    @Query("select s from MmRegularSummaryForm s " +
            "left join fetch s.regularForm rf " +
            "join fetch s.summaryForm sf " +
            "where s.summaryFormId = ?1")
    MmRegularSummaryForm findSummaryByFormId(int summaryFormId);
}
