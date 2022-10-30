package com.example.humgrycustomer.customer.domain.api.commands;

import com.example.humgrycustomer.common.domain.api.models.valueObjects.AuditEntry;
import com.example.humgrycustomer.common.domain.api.models.valueObjects.Money;
import com.example.humgrycustomer.customer.domain.api.models.valueObjects.CustomerId;
import com.example.humgrycustomer.customer.domain.api.models.valueObjects.CustomerOrderId;
import javax.validation.Valid;
import org.axonframework.modelling.command.TargetAggregateIdentifier;

public record CreateCustomerOrderCommand(@TargetAggregateIdentifier CustomerId aggregateIdentifier,
                                         CustomerOrderId customerOrderId, @Valid Money orderTotal, AuditEntry auditEntry){
}