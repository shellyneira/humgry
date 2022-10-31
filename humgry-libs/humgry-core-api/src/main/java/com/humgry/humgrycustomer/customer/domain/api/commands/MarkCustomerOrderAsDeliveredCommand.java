package com.humgry.humgrycustomer.customer.domain.api.commands;

import com.humgry.humgrycustomer.common.domain.api.models.valueObjects.AuditEntry;
import com.humgry.humgrycustomer.customer.domain.api.models.valueObjects.CustomerOrderId;
import org.axonframework.modelling.command.TargetAggregateIdentifier;

public record MarkCustomerOrderAsDeliveredCommand(@TargetAggregateIdentifier CustomerOrderId targetAggregateIdentifier,
                                                  AuditEntry auditEntry) {}