package ru.cmo.edu.data.repository;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import ru.cmo.edu.data.entity.RegionFormData;

import java.util.List;

/**
 * Created by to on 06.06.2017.
 */

@Repository
public interface RegionFormDataRepository extends CrudRepository<RegionFormData, Integer> {

    @Query("select rf from RegionFormData rf " +
            "where rf.regionId = ?1 and YEAR(rf.sendDate) = YEAR(CURRENT_DATE) " +
            "order by rf.sendDate desc")
    List<RegionFormData> findAll(int regionId);

    @Query("select rf from RegionFormData rf " +
            "where rf.regionId = ?1 and YEAR(rf.sendDate) < YEAR(CURRENT_DATE) " +
            "order by rf.sendDate desc")
    List<RegionFormData> findAllArchived(int regionId);
}
