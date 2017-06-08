package ru.cmo.edu.data.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.cmo.edu.data.entity.EduEntity;
import ru.cmo.edu.data.repository.EduEntityRepository;

import java.util.List;

/**
 * Created by to on 06.06.2017.
 */
@Service
public class EduService {

    @Autowired
    EduEntityRepository eduEntityRepository;

    public List<EduEntity> getAllEdus() {
        return eduEntityRepository.findAll();
    }

}
