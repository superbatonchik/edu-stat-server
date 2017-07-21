package ru.cmo.edu.data.repository;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import ru.cmo.edu.data.entity.EduKind;
import ru.cmo.edu.data.entity.Municipality;

import java.util.List;

/**
 * Created by to on 07.06.2017.
 */
@Repository
public interface EduKindRepository extends CrudRepository<EduKind, Integer> {

    @Query("select distinct ek from EduKind ek order by ek.name ")
    List<EduKind> findAll();

    @Query("select distinct ek from EduKind ek join ek.edus e where e.municipalityId = ?1 order by ek.name ")
    List<EduKind> findAll(int municipalityId);
}
