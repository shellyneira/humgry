package com.humgry.humgrycustomer.customer.domain;

import com.humgry.humgrycustomer.common.domain.api.models.valueObjects.Money;
import com.humgry.humgrycustomer.common.domain.api.models.valueObjects.PersonName;
import com.humgry.humgrycustomer.customer.domain.api.commands.CreateCustomerCommand;
import com.humgry.humgrycustomer.customer.domain.api.commands.CreateCustomerOrderCommand;
import com.humgry.humgrycustomer.customer.domain.api.events.CustomerCreatedEvent;
import com.humgry.humgrycustomer.customer.domain.api.models.valueObjects.CustomerId;
import lombok.NoArgsConstructor;
import org.axonframework.commandhandling.CommandHandler;
import org.axonframework.eventsourcing.EventSourcingHandler;
import org.axonframework.modelling.command.AggregateIdentifier;
import org.axonframework.modelling.command.AggregateLifecycle;
import org.axonframework.spring.stereotype.Aggregate;
import java.util.Objects;

@NoArgsConstructor
@Aggregate
public final class Customer {
    @AggregateIdentifier
    private CustomerId customerId;
    private PersonName personName;
    private Money orderLimit;

    @CommandHandler
    public Customer(final CreateCustomerCommand command) {
        AggregateLifecycle.apply(new CustomerCreatedEvent(command.targetAggregateIdentifier(), command.personName(), command.orderLimit(), command.auditEntry()));
    }

    @EventSourcingHandler
    public void on(final CustomerCreatedEvent event) {
        customerId = event.aggregateIdentifier();
        personName = event.personName();
        orderLimit = event.orderLimit();
    }

    @CommandHandler
    public void handle(final CreateCustomerOrderCommand command) {
        if (command.orderTotal().isGreaterThanOrEqual(orderLimit))
            throw new UnsupportedOperationException("Customer limit is reached.");

        AggregateLifecycle.apply(new CustomerOrder());
    }

    @Override
    public boolean equals(final Object o) {
        if (this == o)
            return true;
        if (o == null || getClass() != o.getClass())
            return false;
        final Customer customer = (Customer) o;
        return customerId.equals(customer.customerId) && personName.equals(customer.personName) && orderLimit.equals(customer.orderLimit);
    }

    @Override
    public int hashCode() {
        return Objects.hash(customerId, personName, orderLimit);
    }
}
