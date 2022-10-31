package com.humgry.humgrycustomer.customer.domain.api.events;

import com.humgry.humgrycustomer.common.domain.api.models.valueObjects.AuditEntry;
import com.humgry.humgrycustomer.common.domain.api.models.valueObjects.Money;
import com.humgry.humgrycustomer.common.domain.api.models.valueObjects.PersonName;
import com.humgry.humgrycustomer.customer.domain.api.models.valueObjects.CustomerId;

public record CustomerCreatedEvent(CustomerId aggregateIdentifier, PersonName personName, Money orderLimit,
                                   AuditEntry auditEntry) {}