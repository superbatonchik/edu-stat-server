package ru.cmo.edu.data.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import ru.cmo.edu.data.entity.RegionEntity;

/**
 * Created by to on 07.06.2017.
 */

@Repository
public interface RegionEntityRepository extends CrudRepository<RegionEntity, Integer> {
}
