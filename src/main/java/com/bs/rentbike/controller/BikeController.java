package com.bs.rentbike.controller;

import com.bs.rentbike.model.Bike;
import com.bs.rentbike.service.BikeService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Slf4j
@RestController
@RequestMapping(value = "bike")
public class BikeController implements BikeControllerApi {

    private final BikeService bikeService;

//    TODO: 1. add interface 2. add ResponseEntity

    public BikeController(BikeService bikeService) {
        this.bikeService = bikeService;
    }

    @GetMapping
    public ResponseEntity<Void> createDummyBikes() {
        bikeService.createDummyBike();

        return ResponseEntity.ok().build();
    }

}
