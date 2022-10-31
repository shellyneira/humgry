package com.humgry.humgrycustomer.restaurant.domain.api.model.events;

import com.google.common.collect.ImmutableList;
import com.humgry.humgrycustomer.common.domain.api.models.valueObjects.AuditEntry;
import com.humgry.humgrycustomer.restaurant.domain.api.model.valueObjects.RestaurantId;
import com.humgry.humgrycustomer.restaurant.domain.api.model.valueObjects.RestaurantOrderId;
import com.humgry.humgrycustomer.restaurant.domain.api.model.valueObjects.RestaurantOrderLineItem;

public record RestaurantOrderCreatedEvent(ImmutableList<RestaurantOrderLineItem> lineItems,
                                          RestaurantOrderId restaurantOrderId, RestaurantId restaurantId,
                                          AuditEntry auditEntry) {}
