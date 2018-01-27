package ru.cmo.edu.data.repository;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import ru.cmo.edu.data.entity.Region;

import java.util.List;

/**
 * Created by to on 07.06.2017.
 */

@Repository
public interface RegionRepository extends BaseRepository<Region, Integer> {
    @Query("select r from Region r order by r.name")
    List<Region> findAll();
}
