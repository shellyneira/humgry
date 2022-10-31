package com.humgry.humgrycustomer.courier.domain.api.commands;

import com.humgry.humgrycustomer.common.domain.api.models.valueObjects.AuditEntry;
import com.humgry.humgrycustomer.common.domain.api.models.valueObjects.PersonName;
import com.humgry.humgrycustomer.courier.domain.api.model.valueObjects.CourierId;
import org.axonframework.modelling.command.TargetAggregateIdentifier;

import javax.validation.Valid;

public record CreateCourierCommand(@TargetAggregateIdentifier CourierId targetAggregateIdentifier,
                                   @Valid PersonName personName, int maxNumberOfActiveOrders, AuditEntry auditEntry) {

    public CreateCourierCommand(@Valid final PersonName personName, final int maxNumberOfActiveOrders, final AuditEntry auditEntry) {
        this(new CourierId(), personName, maxNumberOfActiveOrders, auditEntry);
    }
}
