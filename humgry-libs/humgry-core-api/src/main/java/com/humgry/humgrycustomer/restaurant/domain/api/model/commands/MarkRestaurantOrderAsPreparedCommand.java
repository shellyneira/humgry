package com.humgry.humgrycustomer.restaurant.domain.api.model.commands;

import com.humgry.humgrycustomer.common.domain.api.models.valueObjects.AuditEntry;
import com.humgry.humgrycustomer.restaurant.domain.api.model.valueObjects.RestaurantOrderId;
import org.axonframework.modelling.command.TargetAggregateIdentifier;

public record MarkRestaurantOrderAsPreparedCommand(@TargetAggregateIdentifier RestaurantOrderId targetAggregateIdentifier,
                                                   AuditEntry auditEntry) {}
