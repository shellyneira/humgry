package com.humgry.humgrycustomer.courier.domain.api.events;

import com.humgry.humgrycustomer.common.domain.api.models.valueObjects.AuditEntry;
import com.humgry.humgrycustomer.common.domain.api.models.valueObjects.PersonName;
import com.humgry.humgrycustomer.courier.domain.api.model.valueObjects.CourierId;

public record CourierCreatedEvent(PersonName personName, int maxNumberOfActiveOrders, CourierId aggregateIdentifier,
                                  AuditEntry auditEntry) {}
