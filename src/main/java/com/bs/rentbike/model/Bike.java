package com.bs.rentbike.model;

import com.bs.rentbike.repository.schema.BikeEntity;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import lombok.experimental.Accessors;
import org.apache.commons.lang.StringUtils;

import java.time.OffsetDateTime;
import java.util.Objects;

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

    public Bike generateBikeCode() {
        if (Objects.nonNull(manufacturer) && StringUtils.isNotEmpty(model) && StringUtils.isNotEmpty(licenseNo))
            bikeCode = manufacturer.name() + "-" + model + "-" + licenseNo;
        return this;
    }
}
