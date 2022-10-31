package com.humgry.humgrycourier.courier.domain.model.commands;

import com.humgry.humgrycustomer.common.domain.api.models.valueObjects.AuditEntry;
import com.humgry.humgrycustomer.courier.domain.api.model.valueObjects.CourierOrderId;
import org.axonframework.modelling.command.TargetAggregateIdentifier;

public record MarkCourierOrderAsNotAssignedInternalCommand(
        @TargetAggregateIdentifier CourierOrderId targetAggregateIdentifier, AuditEntry auditEntry) {}
