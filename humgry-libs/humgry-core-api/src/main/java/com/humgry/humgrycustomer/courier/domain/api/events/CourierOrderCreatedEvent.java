package com.humgry.humgrycustomer.courier.domain.api.events;

import com.humgry.humgrycustomer.common.domain.api.models.valueObjects.AuditEntry;
import com.humgry.humgrycustomer.courier.domain.api.model.valueObjects.CourierOrderId;

public record CourierOrderCreatedEvent(CourierOrderId aggregateIdentifier, AuditEntry auditEntry) {}
