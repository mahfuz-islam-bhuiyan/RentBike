package com.bs.rentbike.controller;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Api(value = "bike", tags = "bike")
@RequestMapping(path = "/bike")
public interface BikeControllerApi {

    @ApiOperation(
            value = "Create dummy bikes",
            nickname = "createDummyBikes",
            notes = "Create dummy bikes",
            tags = {
                    "bike",
            })
    @GetMapping(value = "/create-dummy-bikes")
    ResponseEntity<Void> createDummyBikes();
}
