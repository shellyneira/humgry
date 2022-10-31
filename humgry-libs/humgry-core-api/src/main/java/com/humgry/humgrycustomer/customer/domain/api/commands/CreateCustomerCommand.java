package com.humgry.humgrycustomer.customer.domain.api.commands;

import com.humgry.humgrycustomer.common.domain.api.models.valueObjects.AuditEntry;
import com.humgry.humgrycustomer.common.domain.api.models.valueObjects.Money;
import com.humgry.humgrycustomer.common.domain.api.models.valueObjects.PersonName;
import com.humgry.humgrycustomer.customer.domain.api.models.valueObjects.CustomerId;
import org.axonframework.modelling.command.TargetAggregateIdentifier;

public record CreateCustomerCommand(@TargetAggregateIdentifier CustomerId targetAggregateIdentifier,
                                    PersonName personName, Money orderLimit, AuditEntry auditEntry) {}
