package com.example.humgrycustomer.customer.domain.api.commands;

import com.example.humgrycustomer.common.domain.api.models.valueObjects.AuditEntry;
import com.example.humgrycustomer.common.domain.api.models.valueObjects.Money;
import com.example.humgrycustomer.common.domain.api.models.valueObjects.PersonName;
import com.example.humgrycustomer.customer.domain.api.models.valueObjects.CustomerId;
import org.axonframework.modelling.command.TargetAggregateIdentifier;

public record CreateCustomerCommand(@TargetAggregateIdentifier CustomerId aggregateIdentifier,
                                    PersonName personName, Money orderLimit, AuditEntry auditEntry){ }
