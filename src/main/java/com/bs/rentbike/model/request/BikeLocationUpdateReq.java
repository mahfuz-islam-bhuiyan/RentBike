package com.bs.rentbike.model.request;

import com.bs.rentbike.model.BikeStatus;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import lombok.experimental.Accessors;

@Getter
@Setter
@Accessors(chain = true)
@ToString
public class BikeLocationUpdateReq {

    private Long bikeId;
    private Double latitude;
    private Double longitude;
    private BikeStatus status;

}
