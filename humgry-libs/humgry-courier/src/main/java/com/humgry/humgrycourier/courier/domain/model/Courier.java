package com.humgry.humgrycourier.courier.domain.model;

import com.humgry.humgrycourier.courier.domain.model.events.CourierValidatedOrderWithErrorInternalEvent;
import com.humgry.humgrycourier.courier.domain.model.events.CourierValidatedOrderWithSuccessInternalEvent;
import com.humgry.humgrycustomer.common.domain.api.models.valueObjects.AuditEntry;
import com.humgry.humgrycustomer.common.domain.api.models.valueObjects.PersonName;
import com.humgry.humgrycustomer.courier.domain.api.commands.CreateCourierCommand;
import com.humgry.humgrycustomer.courier.domain.api.events.CourierCreatedEvent;
import com.humgry.humgrycustomer.courier.domain.api.model.valueObjects.CourierId;
import com.humgry.humgrycustomer.courier.domain.api.model.valueObjects.CourierOrderId;
import org.axonframework.commandhandling.CommandHandler;
import org.axonframework.eventsourcing.EventSourcingHandler;
import org.axonframework.modelling.command.AggregateIdentifier;
import org.axonframework.modelling.command.AggregateLifecycle;
import org.axonframework.spring.stereotype.Aggregate;

import java.util.Objects;

@Aggregate(snapshotTriggerDefinition = "courierSnapshotTriggerDefinition")
class Courier {

    @AggregateIdentifier
    private CourierId id;
    private PersonName personName;
    private int maxNumberOfActiveOrders = 5;
    private int numberOfActiveOrders;

    Courier() {
    }

    @CommandHandler
    Courier(final CreateCourierCommand command) {
        AggregateLifecycle.apply(new CourierCreatedEvent(command.personName(), command.maxNumberOfActiveOrders(), command.targetAggregateIdentifier(), command.auditEntry()));
    }

    @EventSourcingHandler
    void on(final CourierCreatedEvent event) {
        id = event.aggregateIdentifier();
        personName = event.personName();
        maxNumberOfActiveOrders = event.maxNumberOfActiveOrders();
        numberOfActiveOrders += 1;
    }

    void validateOrder(final CourierOrderId orderId, final AuditEntry auditEntry) {
        if (numberOfActiveOrders + 1 > maxNumberOfActiveOrders)
             AggregateLifecycle.apply(new CourierValidatedOrderWithErrorInternalEvent(id, orderId, auditEntry));
        else AggregateLifecycle.apply(new CourierValidatedOrderWithSuccessInternalEvent(id, orderId, auditEntry));
    }

    @Override
    public boolean equals(final Object o) {
        if (this == o)
            return true;
        if (o == null || getClass() != o.getClass())
            return false;
        final Courier courier = (Courier) o;
        return maxNumberOfActiveOrders == courier.maxNumberOfActiveOrders && numberOfActiveOrders == courier.numberOfActiveOrders && id.equals(courier.id) && personName.equals(courier.personName);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, personName, maxNumberOfActiveOrders, numberOfActiveOrders);
    }
}
