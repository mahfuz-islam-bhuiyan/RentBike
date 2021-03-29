package com.bs.rentbike.controller;

import com.bs.rentbike.model.Bike;
import com.bs.rentbike.model.request.BikeLocationUpdateReq;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@Api(value = "bike", tags = "bike")
@RequestMapping(path = "/bike")
public interface BikeControllerApi {

    @ApiOperation(
            value = "Get all bikes",
            nickname = "getAllBikes",
            notes = "Get all bikes",
            tags = {
                    "bike",
            })
    @GetMapping
    ResponseEntity<List<Bike>> getAllBikes();

    @ApiOperation(
            value = "Get bike by bike id",
            nickname = "getBikeById",
            notes = "Get bike by bike id",
            tags = {
                    "bike",
            })
    @GetMapping(value = "/{id}")
    ResponseEntity<Bike> getBikeById(@PathVariable Long id);

    @ApiOperation(
            value = "Create a new bike",
            nickname = "createBike",
            notes = "Create a new bike",
            tags = {"bike",})
    @PostMapping
    ResponseEntity<Bike> createBike(@RequestBody Bike bike);

    @ApiOperation(
            value = "Update a bike's latitude and longitude",
            nickname = "updateBikeLocation",
            notes = "Update a bike's latitude and longitude",
            tags = {"bike",})
    @PutMapping(value = "/updateBikeLocation")
    ResponseEntity<Map<String, String>> updateBikeLocation(@RequestBody BikeLocationUpdateReq locationUpdateReq);

    @ApiOperation(
            value = "Create dummy bikes",
            nickname = "createDummyBikes",
            notes = "Create dummy bikes",
            tags = {
                    "bike",
            })
    @GetMapping(value = "/createDummyBikes")
    ResponseEntity<Void> createDummyBikes();

    @ApiOperation(
            value = "Get bikes by latitude, longitude and distance within miles",
            nickname = "getAllBikesByLatitudeLongitude",
            notes = "Get bikes by latitude, longitude and distance within miles",
            tags = {
                    "bike",
            })
    @GetMapping(value = "/nearestBikesByLatLong")
    ResponseEntity<List<Bike>> getAllNearestBikesByLatitudeLongitude(
            @RequestParam(value = "fromLatitude", required = true) Double fromLatitude,
            @RequestParam(value = "fromLongitude", required = true) Double fromLongitude,
            @RequestParam(value = "withinMiles", required = false) Integer withinMiles);
}
