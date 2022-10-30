package com.example.humgrycustomer.customer.domain.api.events;

import com.example.humgrycustomer.common.domain.api.models.valueObjects.AuditEntry;
import com.example.humgrycustomer.common.domain.api.models.valueObjects.Money;
import com.example.humgrycustomer.customer.domain.api.models.valueObjects.CustomerId;
import com.example.humgrycustomer.customer.domain.api.models.valueObjects.CustomerOrderId;

public record CustomerOrderCreatedEvent(CustomerId customerId,
                                 CustomerOrderId aggregateIdentifier, Money orderTotal, AuditEntry auditEntry){}