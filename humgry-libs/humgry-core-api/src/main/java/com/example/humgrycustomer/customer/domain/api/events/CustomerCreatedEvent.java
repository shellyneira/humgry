package com.example.humgrycustomer.customer.domain.api.events;

import com.example.humgrycustomer.common.domain.api.models.valueObjects.AuditEntry;
import com.example.humgrycustomer.common.domain.api.models.valueObjects.Money;
import com.example.humgrycustomer.common.domain.api.models.valueObjects.PersonName;
import com.example.humgrycustomer.customer.domain.api.models.valueObjects.CustomerId;

public record CustomerCreatedEvent(CustomerId aggregateIdentifier, PersonName personName,
                                   Money orderLimit, AuditEntry auditEntry){}