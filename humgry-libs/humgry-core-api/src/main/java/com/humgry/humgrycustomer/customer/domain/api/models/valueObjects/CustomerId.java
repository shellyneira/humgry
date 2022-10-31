package com.humgry.humgrycustomer.customer.domain.api.models.valueObjects;

import java.io.Serializable;
import java.util.UUID;

public record CustomerId(String identifier) implements Serializable {

    public CustomerId() {
        this(UUID.randomUUID().toString());
    }

    @Override
    public String toString() {
        return identifier;
    }
}

