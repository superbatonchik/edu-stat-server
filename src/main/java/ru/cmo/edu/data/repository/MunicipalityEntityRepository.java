package ru.cmo.edu.data.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import ru.cmo.edu.data.entity.MunicipalityEntity;

/**
 * Created by to on 07.06.2017.
 */

@Repository
public interface MunicipalityEntityRepository extends CrudRepository<MunicipalityEntity, Integer> {
}
