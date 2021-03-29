package com.bs.rentbike.service;

import com.bs.rentbike.mapper.BikeMapper;
import com.bs.rentbike.model.Bike;
import com.bs.rentbike.model.request.BikeLocationUpdateReq;
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
        BikeUtil.getDummyBikeEntities().forEach(this::createUpdate);
    }

    public Bike createUpdate(Bike bike) {

        if (Objects.isNull(bike) || Objects.isNull(bike.getManufacturer())
                || StringUtils.isEmpty(bike.getModel()) || StringUtils.isEmpty(bike.getLicenseNo())) {
            log.error("Couldn't create/update bike without manufacturer, model and license no");
            return null;
        }

        try {
            bikeRepository.save(bikeMapper.domainToEntity().map(bike));
        } catch (Exception e) {
            log.error("Failed to save or update bike", e);
            bike = null;
        }

        return bike;
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

    public List<Bike> getAllNearestBikesByLatitudeLongitude(Double fromLatitude, Double fromLongitude, Integer withinMiles) {
        List<Bike> bikeList = new ArrayList<>();

        if (Objects.isNull(fromLatitude) || Objects.isNull(fromLongitude)) return bikeList;

        if (Objects.isNull(withinMiles) || withinMiles <= 0) withinMiles = DEFAULT_DISTANCE_IN_MILES;

        try {
            var bikeListOptional = bikeRepository.findAllAvailableByLatitudeLongitude(fromLatitude, fromLongitude, withinMiles);
            if (bikeListOptional.isPresent()) {
                bikeList = bikeListOptional.get()
                        .stream()
                        .map(bikeEntity -> bikeMapper.entityToDomain().map(bikeEntity))
                        .collect(Collectors.toList());
            }
        } catch (Exception e) {
            log.error("Error while finding nearest bikes for " + fromLatitude + ", " + fromLongitude, e);
        }

        return bikeList;
    }


    public boolean updateBikeLocation(BikeLocationUpdateReq locationUpdateReq) {
        boolean isSuccessfullyUpdated = false;
        if (Objects.isNull(locationUpdateReq.getBikeId())
                || Objects.isNull(locationUpdateReq.getLatitude())
                || Objects.isNull(locationUpdateReq.getLongitude())
        ) {
            log.error("Can not update bike location without bike id and location info");
            return isSuccessfullyUpdated;
        }

        var bike = getBikeById(locationUpdateReq.getBikeId());
        if (Objects.nonNull(bike)) {
            bike.setLatitude(locationUpdateReq.getLatitude());
            bike.setLongitude(locationUpdateReq.getLongitude());

            /*Bike status update isn't mandatory*/
            if (Objects.nonNull(locationUpdateReq.getStatus()))
                bike.setStatus(locationUpdateReq.getStatus());

            bike = createUpdate(bike);
            isSuccessfullyUpdated = Objects.nonNull(bike);
        }

        return isSuccessfullyUpdated;
    }
}
