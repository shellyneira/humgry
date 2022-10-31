package com.humgry.humgrycustomer.order.domain.api.model.valueObjects;

import java.util.UUID;

public record OrderId(String identifier) {
    public OrderId() {
        this(UUID.randomUUID().toString());
    }

    @Override
    public String toString() {
        return identifier;
    }
}

