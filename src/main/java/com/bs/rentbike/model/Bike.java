package com.bs.rentbike.model;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import lombok.experimental.Accessors;

import java.time.OffsetDateTime;

@Getter
@Setter
@Accessors(chain = true)
@ToString
public class Bike extends BaseDomain{

    private Double latitude;
    private Double longitude;

    private BikeManufacturer manufacturer;
    private String model;
    private String licenseNo;

    private String bikeCode;

    private OffsetDateTime operationalHourStartAt;
    private OffsetDateTime operationalHourEndAt;

    private BikeStatus status;

    private BikeActiveStatus activeStatus;
}
