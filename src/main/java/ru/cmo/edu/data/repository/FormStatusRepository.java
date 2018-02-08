package ru.cmo.edu.data.repository;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import ru.cmo.edu.data.entity.FormStatus;

import java.util.List;

/**
 * Created by to on 06.06.2017.
 */

@Repository
public interface FormStatusRepository extends CrudRepository<FormStatus, Integer> {

    @Query(nativeQuery = true)
    List<FormStatus> getMunicipalityStatusForEduView(/*int regionId*/);

    @Query(nativeQuery = true)
    List<FormStatus> getMunicipalityStatusForEduViewArchived(/*int regionId*/);

    @Query(nativeQuery = true)
    List<FormStatus> getEduKindStatusForEduView(/*int regionId*/int municipalityId);

    @Query(nativeQuery = true)
    List<FormStatus> getEduKindStatusForEduViewArchived(/*int regionId*/int municipalityId);

    @Query(nativeQuery = true)
    List<FormStatus> getEduStatus(/*int regionId*/int municipalityId, int eduKindId);

    @Query(nativeQuery = true)
    List<FormStatus> getEduStatusArchived(/*int regionId*/int municipalityId, int eduKindId);

    @Query(nativeQuery = true)
    List<FormStatus> getMunicipalityStatus(/*int regionId*/);

    @Query(nativeQuery = true)
    List<FormStatus> getMunicipalityStatusArchived(/*int regionId*/);
}
