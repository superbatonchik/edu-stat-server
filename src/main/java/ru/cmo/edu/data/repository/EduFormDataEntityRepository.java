package ru.cmo.edu.data.repository;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import ru.cmo.edu.data.entity.EduEntity;
import ru.cmo.edu.data.entity.EduFormDataEntity;
import ru.cmo.edu.data.entity.MunicipalityEntity;

import java.util.List;

/**
 * Created by to on 06.06.2017.
 */

@Repository
public interface EduFormDataEntityRepository extends CrudRepository<EduFormDataEntity, Integer> {

}
