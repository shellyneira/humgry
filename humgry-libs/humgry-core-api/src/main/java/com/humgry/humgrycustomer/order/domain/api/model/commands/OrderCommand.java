package com.humgry.humgrycustomer.order.domain.api.model.commands;

import com.humgry.humgrycustomer.common.domain.api.models.valueObjects.AuditEntry;
import com.humgry.humgrycustomer.order.domain.api.model.valueObjects.OrderId;

public record OrderCommand(OrderId aggregateIdentifier, AuditEntry auditEntry) {}

