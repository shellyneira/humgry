package com.humgry.humgrycustomer.courier.domain.api.model.valueObjects;

import java.util.UUID;

public record CourierOrderId(String identifier) {
    public CourierOrderId() {
        this(UUID.randomUUID().toString());
    }

    @Override
    public String toString() {
        return identifier;
    }
}