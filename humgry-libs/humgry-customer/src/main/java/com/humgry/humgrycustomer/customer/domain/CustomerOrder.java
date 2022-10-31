package com.humgry.humgrycustomer.customer.domain;

import com.humgry.humgrycustomer.common.domain.api.models.valueObjects.Money;
import com.humgry.humgrycustomer.customer.domain.api.commands.CreateCustomerOrderCommand;
import com.humgry.humgrycustomer.customer.domain.api.commands.MarkCustomerOrderAsDeliveredCommand;
import com.humgry.humgrycustomer.customer.domain.api.events.CustomerOrderCreatedEvent;
import com.humgry.humgrycustomer.customer.domain.api.events.CustomerOrderDeliveredEvent;
import com.humgry.humgrycustomer.customer.domain.api.models.valueObjects.CustomerId;
import com.humgry.humgrycustomer.customer.domain.api.models.valueObjects.CustomerOrderId;
import com.humgry.humgrycustomer.customer.domain.api.models.valueObjects.CustomerOrderState;
import lombok.NoArgsConstructor;
import org.axonframework.commandhandling.CommandHandler;
import org.axonframework.eventsourcing.EventSourcingHandler;
import org.axonframework.modelling.command.AggregateIdentifier;
import org.axonframework.modelling.command.AggregateLifecycle;
import org.axonframework.spring.stereotype.Aggregate;
import java.util.Objects;

@NoArgsConstructor
@Aggregate
public final class CustomerOrder {
    @AggregateIdentifier
    private CustomerOrderId customerOrderId;
    private CustomerId customerId;
    private CustomerOrderState customerOrderState;
    private Money orderTotal;

    @CommandHandler
    public CustomerOrder(final CreateCustomerOrderCommand command) {
        AggregateLifecycle.apply(new CustomerOrderCreatedEvent(command.targetAggregateIdentifier(), command.customerOrderId(), command.orderTotal(), command.auditEntry()));
    }

    @EventSourcingHandler
    public void on(final CustomerOrderCreatedEvent event) {
        customerId = event.customerId();
        customerOrderId = event.aggregateIdentifier();
        orderTotal = event.orderTotal();
        customerOrderState = CustomerOrderState.CREATED;
    }

    @CommandHandler
    public void handle(final MarkCustomerOrderAsDeliveredCommand command) {
        if (customerOrderState != CustomerOrderState.CREATED)
            throw new UnsupportedOperationException("The current state is not CREATED.");

        AggregateLifecycle.apply(new CustomerOrderDeliveredEvent(command.targetAggregateIdentifier(), command.auditEntry()));
    }

    @EventSourcingHandler
    public void on(final CustomerOrderDeliveredEvent event) {
        customerOrderState = CustomerOrderState.DELIVERED;
    }

    @Override
    public boolean equals(final Object o) {
        if (this == o)
            return true;
        if (o == null || getClass() != o.getClass())
            return false;
        final CustomerOrder that = (CustomerOrder) o;
        return customerOrderId.equals(that.customerOrderId) && customerId.equals(that.customerId) && customerOrderState == that.customerOrderState && orderTotal.equals(that.orderTotal);
    }

    @Override
    public int hashCode() {
        return Objects.hash(customerOrderId, customerId, customerOrderState, orderTotal);
    }
}
