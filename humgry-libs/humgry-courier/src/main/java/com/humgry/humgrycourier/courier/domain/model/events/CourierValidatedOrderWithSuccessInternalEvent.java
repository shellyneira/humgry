package com.humgry.humgrycourier.courier.domain.model.events;

import com.humgry.humgrycustomer.common.domain.api.models.valueObjects.AuditEntry;
import com.humgry.humgrycustomer.courier.domain.api.model.valueObjects.CourierId;
import com.humgry.humgrycustomer.courier.domain.api.model.valueObjects.CourierOrderId;

public record CourierValidatedOrderWithSuccessInternalEvent(CourierId aggregateIdentifier,
                                                            CourierOrderId courierOrderId, AuditEntry auditEntry) {}