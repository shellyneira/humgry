package com.humgry.humgrycustomer.restaurant.domain.api.model.valueObjects;

import java.util.UUID;

public record RestaurantOrderId(String identifier) {
    public RestaurantOrderId() {
        this(UUID.randomUUID().toString());
    }

    @Override
    public String toString() {
        return identifier;
    }
}
