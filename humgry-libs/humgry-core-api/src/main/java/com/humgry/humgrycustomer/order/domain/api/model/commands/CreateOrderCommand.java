package com.humgry.humgrycustomer.order.domain.api.model.commands;

import com.humgry.humgrycustomer.common.domain.api.models.valueObjects.AuditEntry;
import com.humgry.humgrycustomer.order.domain.api.model.valueObjects.OrderId;
import com.humgry.humgrycustomer.order.domain.api.model.valueObjects.OrderInfo;

public record CreateOrderCommand(OrderId aggregateIdentifier, OrderInfo orderInfo, AuditEntry auditEntry) {
    public CreateOrderCommand(final OrderInfo orderInfo, final AuditEntry auditEntry) {
        this(new OrderId(), orderInfo, auditEntry);
    }
}
