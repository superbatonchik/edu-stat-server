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

    @Query(value = "select e from Edu e order by e.sysName, e.eduNumber asc")
    List<Edu> findAll();

    @Query(value = "select e from Edu e where e.municipalityId = ?1 order by e.name, e.eduNumber asc")
    List<Edu> findAll(int municipalityId);

    @Query(value = "select e from Edu e where e.municipalityId = ?1 and e.eduKindId = ?2 order by e.sysName, e.eduNumber asc")
    List<Edu> findAll(int municipalityId, int eduKindId);
}

