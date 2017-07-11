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
}
