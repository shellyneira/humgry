package com.humgry.humgrycustomer.restaurant.domain.api.model.events;

import com.humgry.humgrycustomer.common.domain.api.models.valueObjects.AuditEntry;
import com.humgry.humgrycustomer.restaurant.domain.api.model.valueObjects.RestaurantId;

public record RestaurantEvent(RestaurantId restaurantId, AuditEntry auditEntry) {}

