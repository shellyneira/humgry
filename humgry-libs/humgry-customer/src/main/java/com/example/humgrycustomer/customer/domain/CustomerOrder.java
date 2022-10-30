package com.example.humgrycustomer.customer.domain;

import com.example.humgrycustomer.common.domain.api.models.valueObjects.Money;
import com.example.humgrycustomer.customer.domain.api.commands.CreateCustomerOrderCommand;
import com.example.humgrycustomer.customer.domain.api.commands.MarkCustomerOrderAsDeliveredCommand;
import com.example.humgrycustomer.customer.domain.api.events.CustomerOrderCreatedEvent;
import com.example.humgrycustomer.customer.domain.api.events.CustomerOrderDeliveredEvent;
import com.example.humgrycustomer.customer.domain.api.models.valueObjects.CustomerId;
import com.example.humgrycustomer.customer.domain.api.models.valueObjects.CustomerOrderId;
import com.example.humgrycustomer.customer.domain.api.models.valueObjects.CustomerOrderState;
import lombok.NoArgsConstructor;
import org.axonframework.commandhandling.CommandHandler;
import org.axonframework.eventsourcing.EventSourcingHandler;
import org.axonframework.modelling.command.AggregateIdentifier;
import org.axonframework.modelling.command.AggregateLifecycle;
import org.axonframework.spring.stereotype.Aggregate;

@NoArgsConstructor
@Aggregate
public final class CustomerOrder {
    @AggregateIdentifier
    private CustomerOrderId customerOrderId;
    private CustomerId customerId;
    private CustomerOrderState customerOrderState;
    private Money orderTotal;

    @CommandHandler
    public CustomerOrder(CreateCustomerOrderCommand command){
        AggregateLifecycle.apply(new CustomerOrderCreatedEvent(
                command.aggregateIdentifier(),
                command.customerOrderId(),
                command.orderTotal(),
                command.auditEntry()
        ));
    }
    @EventSourcingHandler
    public void on(CustomerOrderCreatedEvent event){
        customerId = event.customerId();
        customerOrderId = event.aggregateIdentifier();
        orderTotal = event.orderTotal();
        customerOrderState = CustomerOrderState.CREATED;
    }

    @CommandHandler
    public void handle(MarkCustomerOrderAsDeliveredCommand command){
        if(customerOrderState != CustomerOrderState.CREATED)
            throw new UnsupportedOperationException("The current state is not CREATED.");

        AggregateLifecycle.apply(new CustomerOrderDeliveredEvent(
                command.aggregateIdentifier(),
                command.auditEntry()));
    }

    @EventSourcingHandler
    public void on(CustomerOrderDeliveredEvent event){
        customerOrderState = CustomerOrderState.DELIVERED;
    }
}
