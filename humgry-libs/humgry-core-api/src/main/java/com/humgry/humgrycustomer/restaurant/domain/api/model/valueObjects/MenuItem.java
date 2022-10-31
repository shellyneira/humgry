package com.humgry.humgrycustomer.restaurant.domain.api.model.valueObjects;

import com.humgry.humgrycustomer.common.domain.api.models.valueObjects.Money;

public record MenuItem(String id, String name, Money price) {}

