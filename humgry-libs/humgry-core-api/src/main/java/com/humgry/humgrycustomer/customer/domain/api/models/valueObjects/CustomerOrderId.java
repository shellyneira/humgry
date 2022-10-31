package com.humgry.humgrycustomer.customer.domain.api.models.valueObjects;

import java.io.Serializable;
import java.util.UUID;

public record CustomerOrderId(String identifier) implements Serializable {

    public CustomerOrderId() {
        this(UUID.randomUUID().toString());
    }

    @Override
    public String toString() {
        return identifier;
    }
}
