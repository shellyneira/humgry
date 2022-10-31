package com.humgry.humgrycustomer.order.domain.api.model.events;

import com.humgry.humgrycustomer.common.domain.api.models.valueObjects.AuditEntry;
import com.humgry.humgrycustomer.customer.domain.api.models.valueObjects.CustomerId;
import com.humgry.humgrycustomer.order.domain.api.model.valueObjects.OrderId;

public record OrderVerifiedByCustomerEvent(OrderId aggregateIdentifier, CustomerId customerId,
                                           AuditEntry auditEntry) {}
