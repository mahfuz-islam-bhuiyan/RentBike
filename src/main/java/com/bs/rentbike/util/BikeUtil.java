package com.bs.rentbike.util;

import com.bs.rentbike.model.Bike;
import com.bs.rentbike.model.BikeActiveStatus;
import com.bs.rentbike.model.BikeManufacturer;
import com.bs.rentbike.model.BikeStatus;

import java.util.ArrayList;
import java.util.List;

public class BikeUtil {

    public static List<Bike> getDummyBikeEntities() {
        List<Bike> bikeEntities = new ArrayList<>(5);

        bikeEntities.add(new Bike()
                .setLatitude(24.748963305546052).setLongitude(90.41914560397615)
                .setManufacturer(BikeManufacturer.BIANCHI).setModel("OKLA").setLicenseNo("1320456XCVB")
                .generateBikeCode()
                .setStatus(BikeStatus.AVAILABLE).setActiveStatus(BikeActiveStatus.ACTIVE)
        );

        bikeEntities.add(new Bike()
                .setLatitude(24.746631415785743).setLongitude(90.42045019204504)
                .setManufacturer(BikeManufacturer.TREK).setModel("KHJ").setLicenseNo("240785XCLOK")
                .generateBikeCode()
                .setStatus(BikeStatus.UNAVAILABLE).setActiveStatus(BikeActiveStatus.INACTIVE)
        );

        bikeEntities.add(new Bike()
                .setLatitude(24.76628036314259).setLongitude(90.39360438810579)
                .setManufacturer(BikeManufacturer.SARACEN).setModel("XAIV").setLicenseNo("984576785XAIV")
                .generateBikeCode()
                .setStatus(BikeStatus.AVAILABLE).setActiveStatus(BikeActiveStatus.ACTIVE)
        );

        return bikeEntities;
    }
}
