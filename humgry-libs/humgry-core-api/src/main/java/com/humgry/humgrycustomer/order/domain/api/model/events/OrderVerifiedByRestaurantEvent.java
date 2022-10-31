package com.humgry.humgrycustomer.order.domain.api.model.events;

import com.humgry.humgrycustomer.common.domain.api.models.valueObjects.AuditEntry;
import com.humgry.humgrycustomer.order.domain.api.model.valueObjects.OrderId;
import com.humgry.humgrycustomer.restaurant.domain.api.model.valueObjects.RestaurantId;

public record OrderVerifiedByRestaurantEvent(OrderId aggregateIdentifier, RestaurantId restaurantId,
                                             AuditEntry auditEntry) {}
