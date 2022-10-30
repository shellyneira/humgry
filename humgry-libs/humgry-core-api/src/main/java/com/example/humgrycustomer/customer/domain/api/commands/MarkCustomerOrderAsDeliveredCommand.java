package com.example.humgrycustomer.customer.domain.api.commands;

import com.example.humgrycustomer.common.domain.api.models.valueObjects.AuditEntry;
import com.example.humgrycustomer.customer.domain.api.models.valueObjects.CustomerOrderId;
import org.axonframework.modelling.command.TargetAggregateIdentifier;

public record MarkCustomerOrderAsDeliveredCommand(@TargetAggregateIdentifier CustomerOrderId aggregateIdentifier,
                                                  AuditEntry auditEntry) {}