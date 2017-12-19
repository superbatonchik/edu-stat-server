package ru.cmo.edu.data.repository;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import ru.cmo.edu.data.entity.Municipality;

import java.util.List;

/**
 * Created by to on 07.06.2017.
 */

@Repository
public interface MunicipalityRepository extends CrudRepository<Municipality, Integer> {

    @Query("select m from Municipality m order by m.name")
    List<Municipality> findAll();

    //poka ne nuzhno
    //если аргумент метода не используется в запросе, то jpa фейлит
    @Query("select m from Municipality m where ?1 is not null order by m.name")
    List<Municipality> findAll(int regionId);
}
