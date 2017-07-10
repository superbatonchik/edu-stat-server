package ru.cmo.edu.data.repository;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import ru.cmo.edu.data.entity.EduKindEntity;
import ru.cmo.edu.data.entity.MunicipalityEntity;

import java.util.List;

/**
 * Created by to on 07.06.2017.
 */
@Repository
public interface EduKindEntityRepository extends CrudRepository<EduKindEntity, Integer> {

    @Query("select distinct ek from EduKindEntity ek join ek.edus e where e.municipality = ?1 order by ek.name ")
    List<EduKindEntity> findByMunicipality(MunicipalityEntity municipality);
}
