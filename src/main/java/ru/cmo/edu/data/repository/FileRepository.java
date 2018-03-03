package ru.cmo.edu.data.repository;

import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import ru.cmo.edu.data.entity.File;

import java.util.List;

@Repository
public interface FileRepository extends BaseRepository<File, Integer> {

    @Query("select f from Edu f")
    List<File> findAll();
}

