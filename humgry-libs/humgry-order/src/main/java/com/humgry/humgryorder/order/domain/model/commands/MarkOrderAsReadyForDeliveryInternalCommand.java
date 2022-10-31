package com.humgry.humgryorder.order.domain.model.commands;

import com.humgry.humgrycustomer.common.domain.api.models.valueObjects.AuditEntry;
import com.humgry.humgrycustomer.order.domain.api.model.valueObjects.OrderId;
import org.axonframework.modelling.command.TargetAggregateIdentifier;

public record MarkOrderAsReadyForDeliveryInternalCommand(@TargetAggregateIdentifier OrderId targetAggregateIdentifier,
                                                         AuditEntry auditEntry) {}
