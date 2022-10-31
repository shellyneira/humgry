package com.humgry.humgrycustomer.restaurant.domain.api.model.valueObjects;

import java.io.Serializable;
import java.util.UUID;

public record RestaurantId(String identifier) implements Serializable {
    public RestaurantId() {
        this(UUID.randomUUID().toString());
    }

    @Override
    public String toString() {
        return identifier;
    }
}
