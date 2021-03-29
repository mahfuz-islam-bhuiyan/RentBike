package com.bs.rentbike.mapper;

@FunctionalInterface
public interface ObjectMapper<T, R> {
    R map(T var1);
}