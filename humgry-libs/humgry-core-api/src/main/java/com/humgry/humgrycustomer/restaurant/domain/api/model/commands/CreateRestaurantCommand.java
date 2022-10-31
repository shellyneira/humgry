package com.humgry.humgrycustomer.restaurant.domain.api.model.commands;

import com.humgry.humgrycustomer.common.domain.api.models.valueObjects.AuditEntry;
import com.humgry.humgrycustomer.restaurant.domain.api.model.valueObjects.RestaurantId;
import com.humgry.humgrycustomer.restaurant.domain.api.model.valueObjects.RestaurantMenu;
import org.axonframework.modelling.command.TargetAggregateIdentifier;

import javax.validation.Valid;

public record CreateRestaurantCommand(@TargetAggregateIdentifier RestaurantId targetAggregateIdentifier, String name,
                                      @Valid RestaurantMenu menu, AuditEntry auditEntry) {

    public CreateRestaurantCommand(final String name, final RestaurantMenu menu, final AuditEntry auditEntry) {
        this(new RestaurantId(), name, menu, auditEntry);
    }
}
