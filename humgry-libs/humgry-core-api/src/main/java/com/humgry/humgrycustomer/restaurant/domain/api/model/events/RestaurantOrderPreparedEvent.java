package com.humgry.humgrycustomer.restaurant.domain.api.model.events;

import com.humgry.humgrycustomer.common.domain.api.models.valueObjects.AuditEntry;
import com.humgry.humgrycustomer.restaurant.domain.api.model.valueObjects.RestaurantOrderId;

public record RestaurantOrderPreparedEvent(RestaurantOrderId restaurantOrderId, AuditEntry auditEntry) {}
