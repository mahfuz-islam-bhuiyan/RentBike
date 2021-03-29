package com.bs.rentbike.service;

import com.bs.rentbike.mapper.BikeMapper;
import com.bs.rentbike.model.Bike;
import com.bs.rentbike.model.BikeActiveStatus;
import com.bs.rentbike.model.BikeManufacturer;
import com.bs.rentbike.model.BikeStatus;
import com.bs.rentbike.repository.jpa.BikeRepository;
import com.bs.rentbike.repository.schema.BikeEntity;
import com.bs.rentbike.util.BikeUtil;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.concurrent.atomic.AtomicReference;
import java.util.stream.Collectors;

@Slf4j
@Service
public class BikeService {

    @Value("${bike.search.default-distance-in-miles}")
    public Integer DEFAULT_DISTANCE_IN_MILES;

    private final BikeRepository bikeRepository;
    private final BikeMapper bikeMapper;

    public BikeService(BikeRepository bikeRepository, BikeMapper bikeMapper) {
        this.bikeRepository = bikeRepository;
        this.bikeMapper = bikeMapper;
    }

    public void createDummyBike() {
        BikeUtil.getDummyBikeEntities().forEach(this::create);
    }

    public boolean create(Bike bike) {
        var hasCreated = false;
        if (Objects.isNull(bike) || Objects.isNull(bike.getManufacturer())
                || StringUtils.isEmpty(bike.getModel()) || StringUtils.isEmpty(bike.getLicenseNo())) {
            log.error("Couldn't create/update bike without manufacturer, model and license no");
            return hasCreated;
        }

        try {
            bikeRepository.save(bikeMapper.domainToEntity().map(bike));
            hasCreated = true;
        } catch (Exception e) {
            log.error("Failed to save bike", e);
        }

        return hasCreated;
    }

    public List<Bike> getAllBikes() {
        return bikeRepository.findAll()
                .stream()
                .map(bikeEntity -> bikeMapper.entityToDomain().map(bikeEntity))
                .collect(Collectors.toList());

    }

    public Bike getBikeById(Long bikeId) {
        AtomicReference<BikeEntity> bikeEntity = new AtomicReference<>(new BikeEntity());

        if (Objects.isNull(bikeId)) return null;

        try {
            bikeRepository.findById(bikeId).ifPresentOrElse(bikeEntity::set,
                    () -> {
                        log.error("No bike found with this id" + bikeId);
                    });
        } catch (Exception e) {
            log.error("No bike found with this id" + bikeId, e);
        }

        return bikeMapper.entityToDomain().map(bikeEntity.get());
    }


}
