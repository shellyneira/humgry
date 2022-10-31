package com.humgry.humgrycustomer.customer.domain.api.events;

import com.humgry.humgrycustomer.common.domain.api.models.valueObjects.AuditEntry;
import com.humgry.humgrycustomer.customer.domain.api.models.valueObjects.CustomerOrderId;

public record CustomerOrderRejectedEvent(CustomerOrderId customerOrderId, AuditEntry auditEntry) {}