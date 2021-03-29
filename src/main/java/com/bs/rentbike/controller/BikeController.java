package com.bs.rentbike.controller;

import com.bs.rentbike.model.Bike;
import com.bs.rentbike.model.request.BikeLocationUpdateReq;
import com.bs.rentbike.service.BikeService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@Slf4j
@RestController
@RequestMapping(value = "bike")
public class BikeController implements BikeControllerApi {

    private final BikeService bikeService;

    public BikeController(BikeService bikeService) {
        this.bikeService = bikeService;
    }

    @Override
    public ResponseEntity<List<Bike>> getAllBikes() {
        return ResponseEntity.ok(bikeService.getAllBikes());
    }

    @Override
    public ResponseEntity<Bike> getBikeById(Long id) {
        return ResponseEntity.ok(bikeService.getBikeById(id));
    }

    @Override
    public ResponseEntity<Bike> createBike(Bike bike) {
        return ResponseEntity.ok(bikeService.createUpdate(bike));
    }

    @Override
    public ResponseEntity<Map<String, String>> updateBikeLocation(BikeLocationUpdateReq locationUpdateReq) {
        var updated = bikeService.updateBikeLocation(locationUpdateReq);
        var result = Map.of("status", updated ? "location and status successfully updated" : "location and status update failed");
        return ResponseEntity.ok(result);
    }

    public ResponseEntity<Void> createDummyBikes() {
        bikeService.createDummyBike();

        return ResponseEntity.ok().build();
    }

    @Override
    public ResponseEntity<List<Bike>> getAllNearestBikesByLatitudeLongitude(Double fromLatitude,
                                                                            Double fromLongitude,
                                                                            Integer withinMiles) {

        return ResponseEntity.ok(bikeService
                .getAllNearestBikesByLatitudeLongitude(fromLatitude, fromLongitude, withinMiles));
    }

}
