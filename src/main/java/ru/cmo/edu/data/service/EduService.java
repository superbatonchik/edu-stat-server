package ru.cmo.edu.data.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.cmo.edu.data.entity.Edu;
import ru.cmo.edu.data.repository.EduRepository;

import java.util.List;

/**
 * Created by to on 06.06.2017.
 */
@Service
public class EduService {

    @Autowired
    EduRepository eduRepository;

    public List<Edu> getAllEdus() {
        return eduRepository.findAll();
    }

}
