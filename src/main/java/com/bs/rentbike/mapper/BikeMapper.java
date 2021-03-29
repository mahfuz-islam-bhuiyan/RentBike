package com.bs.rentbike.mapper;

import com.bs.rentbike.model.Bike;
import com.bs.rentbike.repository.schema.BikeEntity;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Component;


@Component
public class BikeMapper {

    public ObjectMapper<Bike, BikeEntity> domainToEntity() {
        return domain -> {
            BikeEntity entity = new BikeEntity();
            BeanUtils.copyProperties(domain, entity);
            return entity;
        };

    }

    public ObjectMapper<BikeEntity, Bike> entityToDomain() {
        return entity -> {
            Bike domain = new Bike();
            BeanUtils.copyProperties(entity, domain);
            return domain;
        };
    }
}
