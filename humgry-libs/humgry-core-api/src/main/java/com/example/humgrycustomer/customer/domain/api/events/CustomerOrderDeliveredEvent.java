package com.example.humgrycustomer.customer.domain.api.events;

import com.example.humgrycustomer.common.domain.api.models.valueObjects.AuditEntry;
import com.example.humgrycustomer.customer.domain.api.models.valueObjects.CustomerOrderId;

public record CustomerOrderDeliveredEvent(CustomerOrderId aggregateIdentifier, AuditEntry auditEntry){}