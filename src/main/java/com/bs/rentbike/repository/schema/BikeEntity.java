package com.bs.rentbike.repository.schema;

import com.bs.rentbike.model.BikeActiveStatus;
import com.bs.rentbike.model.BikeManufacturer;
import com.bs.rentbike.model.BikeStatus;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import lombok.experimental.Accessors;

import javax.persistence.*;
import java.time.OffsetDateTime;

@Getter
@Setter
@ToString
@Accessors(chain = true)
@Entity
@Table(name = SchemaConstant.BIKE_TABLE_NAME)
public class BikeEntity extends BaseEntity {

    //TODO: set column more meaningfully

    private Double latitude;
    private Double longitude;

    @Column(name = "manufacturer")
    @Enumerated(EnumType.STRING)
    private BikeManufacturer manufacturer;

    private String model;
    private String licenseNo;

    private String bikeCode;

    private OffsetDateTime operationalHourStartAt;
    private OffsetDateTime operationalHourEndAt;

    @Column(name = "status")
    @Enumerated(EnumType.STRING)
    private BikeStatus status;

    @Column(name = "activeStatus")
    @Enumerated(EnumType.STRING)
    private BikeActiveStatus activeStatus;
}
