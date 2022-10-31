package com.humgry.humgrycustomer.courier.domain.api.events;

import com.humgry.humgrycustomer.common.domain.api.models.valueObjects.AuditEntry;
import com.humgry.humgrycustomer.courier.domain.api.model.valueObjects.CourierId;
import com.humgry.humgrycustomer.courier.domain.api.model.valueObjects.CourierOrderId;

public record CourierOrderAssignedEvent(CourierOrderId aggregateIdentifier, CourierId courierId,
                                        AuditEntry auditEntry) {}
