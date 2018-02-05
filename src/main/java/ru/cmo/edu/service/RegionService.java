package ru.cmo.edu.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.cmo.edu.data.dto.RegionCoreDto;
import ru.cmo.edu.data.entity.Municipality;
import ru.cmo.edu.data.entity.Region;
import ru.cmo.edu.data.repository.RegionRepository;

import java.lang.reflect.InvocationTargetException;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class RegionService {

    private Logger logger = LoggerFactory.getLogger(RegionService.class);

    private final RegionRepository regionRepository;

    @Autowired
    public RegionService(RegionRepository regionRepository) {
        this.regionRepository = regionRepository;
    }

    public <T extends RegionCoreDto> List<T> getAllDto(Class<T> clazz) {
        return regionRepository.findAll().stream().map(t -> {
            try {
                return clazz.getDeclaredConstructor(Region.class).newInstance(t);
            } catch (InstantiationException | IllegalAccessException | InvocationTargetException | NoSuchMethodException e) {
                logger.error(e.getMessage(), e);
                return (T)new RegionCoreDto(t);
            }
        }).collect(Collectors.toList());
    }

    public <T extends RegionCoreDto> T getDto(int id, Class<T> clazz) {
        Region region = regionRepository.findById(id);
        try {
            return clazz.getDeclaredConstructor(Region.class).newInstance(region);
        } catch (IllegalAccessException | InstantiationException | NoSuchMethodException | InvocationTargetException e) {
            logger.error(e.getMessage(), e);
            return (T)new RegionCoreDto(region);
        }
    }
}
