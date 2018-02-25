package ru.cmo.edu.data.repository;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import ru.cmo.edu.data.entity.Edu;

import java.util.List;

/**
 * Created by to on 06.06.2017.
 */

@Repository
public interface EduRepository extends BaseRepository<Edu, Integer> {

    @Query("select e from Edu e " +
            "join fetch e.municipality " +
            "order by e.sysName, e.eduNumber asc")
    List<Edu> findAll();

    @Query("select e from Edu e " +
            "join fetch e.municipality " +
            "where e.municipalityId = ?1 " +
            "order by e.name, e.eduNumber asc")
    List<Edu> findAll(int municipalityId);

    @Query("select e from Edu e " +
            "join fetch e.municipality " +
            "where e.municipalityId = ?1 and e.eduKindId = ?2 order by e.sysName, e.eduNumber asc")
    List<Edu> findAll(int municipalityId, int eduKindId);

    @Query("select e from Edu e " +
            "join fetch e.municipality " +
            "left join fetch e.eduFormDatas ef on ef.formId = ?2 and YEAR(ef.sendDate) = ?3 " +
            "where e.municipalityId = ?1")
    List<Edu> findAllByForm(int municipalityId, int formId, int year);

    @Query("select e from Edu e " +
            "join fetch e.municipality " +
            "left join fetch e.eduFormDatas ef on ef.formId = ?1 and YEAR(ef.sendDate) = ?2")
    List<Edu> findAllByForm(int formId, int year);
}

