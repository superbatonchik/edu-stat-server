package ru.cmo.edu.data.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import ru.cmo.edu.data.entity.Credentials;

/**
 * Created by to on 06.06.2017.
 */

@Repository
public interface CredentialsRepository extends CrudRepository<Credentials, Integer> {

    Credentials findByLogin(String login);
}
