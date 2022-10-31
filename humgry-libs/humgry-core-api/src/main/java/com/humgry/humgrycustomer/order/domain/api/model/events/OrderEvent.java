package com.humgry.humgrycustomer.order.domain.api.model.events;

import com.humgry.humgrycustomer.common.domain.api.models.valueObjects.AuditEntry;
import com.humgry.humgrycustomer.order.domain.api.model.valueObjects.OrderId;

public record OrderEvent(OrderId aggregateIdentifier, AuditEntry auditEntry) {}
