package com.humgry.humgrycustomer.restaurant.domain.api.model.commands;

import com.humgry.humgrycustomer.common.domain.api.models.valueObjects.AuditEntry;
import com.humgry.humgrycustomer.restaurant.domain.api.model.valueObjects.RestaurantId;
import com.humgry.humgrycustomer.restaurant.domain.api.model.valueObjects.RestaurantOrderDetails;
import com.humgry.humgrycustomer.restaurant.domain.api.model.valueObjects.RestaurantOrderId;
import org.axonframework.modelling.command.TargetAggregateIdentifier;

import javax.validation.Valid;

public record CreateRestaurantOrderCommand(@TargetAggregateIdentifier RestaurantId targetAggregateIdentifier,
                                           @Valid RestaurantOrderDetails orderDetails,
                                           RestaurantOrderId restaurantOrderId, AuditEntry auditEntry) {
    public CreateRestaurantOrderCommand(final RestaurantOrderDetails orderDetails, final RestaurantOrderId restaurantOrderId,
                                        final AuditEntry auditEntry) {
        this(new RestaurantId(), orderDetails, restaurantOrderId, auditEntry);
    }
}
