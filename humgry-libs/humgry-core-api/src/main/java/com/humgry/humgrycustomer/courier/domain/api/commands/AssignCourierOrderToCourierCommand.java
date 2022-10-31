package com.humgry.humgrycustomer.courier.domain.api.commands;

import com.humgry.humgrycustomer.common.domain.api.models.valueObjects.AuditEntry;
import com.humgry.humgrycustomer.courier.domain.api.model.valueObjects.CourierId;
import com.humgry.humgrycustomer.courier.domain.api.model.valueObjects.CourierOrderId;
import org.axonframework.modelling.command.TargetAggregateIdentifier;

public record AssignCourierOrderToCourierCommand(@TargetAggregateIdentifier CourierOrderId targetAggregateIdentifier,
                                                 CourierId courierId, AuditEntry auditEntry) {}
