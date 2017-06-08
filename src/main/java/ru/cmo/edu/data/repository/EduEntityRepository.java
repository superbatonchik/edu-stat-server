package ru.cmo.edu.data.repository;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import ru.cmo.edu.data.entity.EduEntity;
import ru.cmo.edu.data.entity.EduKindEntity;
import ru.cmo.edu.data.entity.MunicipalityEntity;

import java.util.List;

/**
 * Created by to on 06.06.2017.
 */

@Repository
public interface EduEntityRepository extends CrudRepository<EduEntity, Integer> {

    @Query(value = "select e from EduEntity e order by e.name, e.eduNumber asc")
    List<EduEntity> findAll();

    @Query(value = "select e from EduEntity e where e.municipality = ?1 order by e.name, e.eduNumber asc")
    List<EduEntity> findByMunicipality(MunicipalityEntity municipality);

    List<EduEntity> findByMunicipalityAndEduKind(MunicipalityEntity municipality, EduKindEntity eduKind);
}
