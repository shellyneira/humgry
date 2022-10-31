package com.humgry.humgrycustomer.courier.domain.api.model.valueObjects;

import java.util.UUID;

public record CourierId(String identifier) {
    public CourierId() {
        this(UUID.randomUUID().toString());
    }

    @Override
    public String toString() {
        return identifier;
    }
}
