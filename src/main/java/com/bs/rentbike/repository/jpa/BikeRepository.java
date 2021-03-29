package com.bs.rentbike.repository.jpa;

import com.bs.rentbike.repository.schema.BikeEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface BikeRepository extends JpaRepository<BikeEntity, Long> {

}
