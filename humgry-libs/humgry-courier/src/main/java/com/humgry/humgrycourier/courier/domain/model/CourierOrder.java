package com.humgry.humgrycourier.courier.domain.model;

import com.humgry.humgrycourier.courier.domain.model.commands.MarkCourierOrderAsAssignedInternalCommand;
import com.humgry.humgrycourier.courier.domain.model.commands.MarkCourierOrderAsNotAssignedInternalCommand;
import com.humgry.humgrycourier.courier.domain.model.events.CourierOrderAssigningInitiatedInternalEvent;
import com.humgry.humgrycustomer.courier.domain.api.commands.AssignCourierOrderToCourierCommand;
import com.humgry.humgrycustomer.courier.domain.api.commands.CreateCourierOrderCommand;
import com.humgry.humgrycustomer.courier.domain.api.commands.MarkCourierOrderAsDeliveredCommand;
import com.humgry.humgrycustomer.courier.domain.api.events.CourierOrderAssignedEvent;
import com.humgry.humgrycustomer.courier.domain.api.events.CourierOrderCreatedEvent;
import com.humgry.humgrycustomer.courier.domain.api.events.CourierOrderDeliveredEvent;
import com.humgry.humgrycustomer.courier.domain.api.events.CourierOrderNotAssignedEvent;
import com.humgry.humgrycustomer.courier.domain.api.model.valueObjects.CourierId;
import com.humgry.humgrycustomer.courier.domain.api.model.valueObjects.CourierOrderId;
import com.humgry.humgrycustomer.courier.domain.api.model.valueObjects.CourierOrderState;
import org.axonframework.commandhandling.CommandHandler;
import org.axonframework.eventsourcing.EventSourcingHandler;
import org.axonframework.modelling.command.AggregateIdentifier;
import org.axonframework.modelling.command.AggregateLifecycle;
import org.axonframework.spring.stereotype.Aggregate;

import java.util.Objects;

@Aggregate(snapshotTriggerDefinition = "courierOrderSnapshotTriggerDefinition")
class CourierOrder {
    @AggregateIdentifier
    private CourierOrderId id;
    private CourierId courierId;
    private CourierOrderState state;

    CourierOrder() {
    }

    @CommandHandler
    CourierOrder(final CreateCourierOrderCommand command) {
        AggregateLifecycle.apply(new CourierOrderCreatedEvent(command.targetAggregateIdentifier(), command.auditEntry()));
    }

    @EventSourcingHandler
    void on(final CourierOrderCreatedEvent event) {
        id = event.aggregateIdentifier();
        state = CourierOrderState.CREATED;
    }

    @CommandHandler
    void assignToCourier(final AssignCourierOrderToCourierCommand command) {
        if (CourierOrderState.CREATED == state) {
            AggregateLifecycle.apply(new CourierOrderAssigningInitiatedInternalEvent(command.courierId(), command.targetAggregateIdentifier(), command.auditEntry()));
        }
        else {
            throw new UnsupportedOperationException("The current state is not CREATED");
        }
    }

    @EventSourcingHandler
    void on(final CourierOrderAssigningInitiatedInternalEvent event) {
        state = CourierOrderState.ASSIGN_PENDING;
    }

    @CommandHandler
    void markOrderAsAssigned(final MarkCourierOrderAsAssignedInternalCommand command) {
        if (CourierOrderState.ASSIGN_PENDING == state) {
            AggregateLifecycle.apply(new CourierOrderAssignedEvent(command.targetAggregateIdentifier(), command.courierId(), command.auditEntry()));
        }
        else {
            throw new UnsupportedOperationException("The current state is not ASSIGN_PENDING");
        }
    }

    @EventSourcingHandler
    void on(final CourierOrderAssignedEvent event) {
        courierId = event.courierId();
        state = CourierOrderState.ASSIGNED;
    }

    @CommandHandler
    void markOrderAsNotAssigned(final MarkCourierOrderAsNotAssignedInternalCommand command) {
        if (CourierOrderState.ASSIGN_PENDING == state) {
            AggregateLifecycle.apply(new CourierOrderNotAssignedEvent(command.targetAggregateIdentifier(), command.auditEntry()));
        }
        else {
            throw new UnsupportedOperationException("The current state is not ASSIGN_PENDING");
        }
    }

    @EventSourcingHandler
    void on(final CourierOrderNotAssignedEvent event) {
        state = CourierOrderState.CREATED;
    }

    @CommandHandler
    void markOrderAsDelivered(final MarkCourierOrderAsDeliveredCommand command) {
        if (CourierOrderState.ASSIGNED == state) {
            AggregateLifecycle.apply(new CourierOrderDeliveredEvent(command.targetAggregateIdentifier(), command.auditEntry()));
        }
        else {
            throw new UnsupportedOperationException("The current state is not ASSIGNED");
        }
    }

    @EventSourcingHandler
    void on(final CourierOrderDeliveredEvent event) {
        state = CourierOrderState.DELIVERED;
    }

    @Override
    public boolean equals(final Object o) {
        if (this == o)
            return true;
        if (o == null || getClass() != o.getClass())
            return false;
        final CourierOrder that = (CourierOrder) o;
        return id.equals(that.id) && courierId.equals(that.courierId) && state == that.state;
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, courierId, state);
    }
}