package com.bs.rentbike.repository.jpa;

import com.bs.rentbike.repository.schema.BikeEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Optional;

public interface BikeRepository extends JpaRepository<BikeEntity, Long> {


    @Query(value =
            "SELECT * FROM ( SELECT *, (3959 * acos(cos(radians(:fromLatitude)) * cos(radians(latitude)) * cos(radians(longitude) - radians(:fromLongitude)) + sin(radians(:fromLatitude)) * sin(radians(latitude)))) AS distance" +
                    " FROM bike WHERE status='AVAILABLE' AND active_status='ACTIVE') bike_distance WHERE bike_distance.distance < :withinMiles ORDER BY bike_distance.distance",
            nativeQuery = true)
    Optional<List<BikeEntity>> findAllAvailableByLatitudeLongitude(@Param("fromLatitude") Double fromLatitude, @Param("fromLongitude") Double fromLongitude, @Param("withinMiles") Integer withinMiles) throws Exception;
}
