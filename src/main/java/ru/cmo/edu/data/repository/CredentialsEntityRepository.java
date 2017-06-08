package ru.cmo.edu.data.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import ru.cmo.edu.data.entity.CredentialsEntity;
import ru.cmo.edu.data.entity.EduFormDataEntity;

/**
 * Created by to on 06.06.2017.
 */

@Repository
public interface CredentialsEntityRepository extends CrudRepository<CredentialsEntity, Integer> {

    CredentialsEntity findByLogin(String login);
}
