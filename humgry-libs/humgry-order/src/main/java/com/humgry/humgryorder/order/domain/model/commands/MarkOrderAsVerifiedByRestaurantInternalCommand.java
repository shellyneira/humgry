package com.humgry.humgryorder.order.domain.model.commands;

import com.humgry.humgrycustomer.common.domain.api.models.valueObjects.AuditEntry;
import com.humgry.humgrycustomer.order.domain.api.model.valueObjects.OrderId;
import com.humgry.humgrycustomer.restaurant.domain.api.model.valueObjects.RestaurantId;
import org.axonframework.modelling.command.TargetAggregateIdentifier;

public record MarkOrderAsVerifiedByRestaurantInternalCommand(@TargetAggregateIdentifier OrderId targetAggregateIdentifier,
                                                             RestaurantId restaurantId, AuditEntry auditEntry) {}
