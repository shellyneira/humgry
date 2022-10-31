package com.humgry.humgrycustomer.customer.domain.api.commands;

import com.humgry.humgrycustomer.common.domain.api.models.valueObjects.AuditEntry;
import com.humgry.humgrycustomer.common.domain.api.models.valueObjects.Money;
import com.humgry.humgrycustomer.customer.domain.api.models.valueObjects.CustomerId;
import com.humgry.humgrycustomer.customer.domain.api.models.valueObjects.CustomerOrderId;
import org.axonframework.modelling.command.TargetAggregateIdentifier;

import javax.validation.Valid;

public record CreateCustomerOrderCommand(@TargetAggregateIdentifier CustomerId targetAggregateIdentifier,
                                         CustomerOrderId customerOrderId, @Valid Money orderTotal,
                                         AuditEntry auditEntry) {}