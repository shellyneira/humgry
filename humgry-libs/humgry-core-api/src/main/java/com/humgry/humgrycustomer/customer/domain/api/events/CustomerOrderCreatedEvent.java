package com.humgry.humgrycustomer.customer.domain.api.events;

import com.humgry.humgrycustomer.common.domain.api.models.valueObjects.AuditEntry;
import com.humgry.humgrycustomer.common.domain.api.models.valueObjects.Money;
import com.humgry.humgrycustomer.customer.domain.api.models.valueObjects.CustomerId;
import com.humgry.humgrycustomer.customer.domain.api.models.valueObjects.CustomerOrderId;

public record CustomerOrderCreatedEvent(CustomerId customerId, CustomerOrderId aggregateIdentifier, Money orderTotal,
                                        AuditEntry auditEntry) {}