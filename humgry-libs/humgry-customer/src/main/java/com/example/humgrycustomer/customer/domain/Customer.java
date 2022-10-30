package com.example.humgrycustomer.customer.domain;

import com.example.humgrycustomer.common.domain.api.models.valueObjects.Money;
import com.example.humgrycustomer.common.domain.api.models.valueObjects.PersonName;
import com.example.humgrycustomer.customer.domain.api.commands.CreateCustomerCommand;
import com.example.humgrycustomer.customer.domain.api.commands.CreateCustomerOrderCommand;
import com.example.humgrycustomer.customer.domain.api.events.CustomerCreatedEvent;
import com.example.humgrycustomer.customer.domain.api.models.valueObjects.CustomerId;
import lombok.NoArgsConstructor;
import org.axonframework.commandhandling.CommandHandler;
import org.axonframework.eventsourcing.EventSourcingHandler;
import org.axonframework.modelling.command.AggregateIdentifier;
import org.axonframework.modelling.command.AggregateLifecycle;
import org.axonframework.spring.stereotype.Aggregate;

@NoArgsConstructor
@Aggregate
public final class Customer {
    @AggregateIdentifier
    private CustomerId customerId;
    private PersonName personName;
    private Money orderLimit;

    @CommandHandler
    public Customer(CreateCustomerCommand command) {
        AggregateLifecycle.apply(new CustomerCreatedEvent(
                command.aggregateIdentifier(),
                command.personName(),
                command.orderLimit(),
                command.auditEntry()
        ));
    }

    @EventSourcingHandler
    public void on(CustomerCreatedEvent event){
        customerId = event.aggregateIdentifier();
        personName = event.personName();
        orderLimit = event.orderLimit();
    }

    @CommandHandler
    public void handle(CreateCustomerOrderCommand command){
        if(command.orderTotal().isGreaterThanOrEqual(orderLimit))
            throw new UnsupportedOperationException("Customer limit is reached.");

        AggregateLifecycle.apply(new CustomerOrder());
    }
}
