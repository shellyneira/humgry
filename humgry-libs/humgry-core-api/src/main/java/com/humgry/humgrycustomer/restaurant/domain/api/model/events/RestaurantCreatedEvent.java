package com.humgry.humgrycustomer.restaurant.domain.api.model.events;

import com.humgry.humgrycustomer.common.domain.api.models.valueObjects.AuditEntry;
import com.humgry.humgrycustomer.restaurant.domain.api.model.valueObjects.RestaurantId;
import com.humgry.humgrycustomer.restaurant.domain.api.model.valueObjects.RestaurantMenu;

public record RestaurantCreatedEvent(String name, RestaurantMenu restaurantMenu, RestaurantId restaurantId,
                                     AuditEntry auditEntry) {}
