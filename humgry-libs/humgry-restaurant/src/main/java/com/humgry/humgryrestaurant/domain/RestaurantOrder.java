package com.humgry.humgryrestaurant.domain;

import com.humgry.humgrycustomer.restaurant.domain.api.model.commands.CreateRestaurantOrderCommand;
import com.humgry.humgrycustomer.restaurant.domain.api.model.commands.MarkRestaurantOrderAsPreparedCommand;
import com.humgry.humgrycustomer.restaurant.domain.api.model.events.RestaurantOrderCreatedEvent;
import com.humgry.humgrycustomer.restaurant.domain.api.model.events.RestaurantOrderPreparedEvent;
import com.humgry.humgrycustomer.restaurant.domain.api.model.valueObjects.RestaurantId;
import com.humgry.humgrycustomer.restaurant.domain.api.model.valueObjects.RestaurantOrderId;
import com.humgry.humgrycustomer.restaurant.domain.api.model.valueObjects.RestaurantOrderLineItem;
import com.humgry.humgrycustomer.restaurant.domain.api.model.valueObjects.RestaurantOrderState;
import org.axonframework.commandhandling.CommandHandler;
import org.axonframework.eventsourcing.EventSourcingHandler;
import org.axonframework.modelling.command.AggregateIdentifier;
import org.axonframework.modelling.command.AggregateLifecycle;

import java.util.List;
import java.util.Objects;

public class RestaurantOrder {

    @AggregateIdentifier
    private RestaurantOrderId id;
    private RestaurantId restaurantId;
    private RestaurantOrderState state;
    private List<RestaurantOrderLineItem> lineItems;

    public RestaurantOrder() {
    }

    RestaurantOrder(final CreateRestaurantOrderCommand command) {
        AggregateLifecycle.apply(
                new RestaurantOrderCreatedEvent(
                        command.orderDetails().lineItems(),
                        command.restaurantOrderId(),
                        command.targetAggregateIdentifier(),
                        command.auditEntry()));
    }

    @EventSourcingHandler
    void on(final RestaurantOrderCreatedEvent event) {
        id = event.restaurantOrderId();
        restaurantId = event.restaurantId();
        lineItems = event.lineItems();
        state = RestaurantOrderState.CREATED;
    }

    @CommandHandler
    void handle(final MarkRestaurantOrderAsPreparedCommand command) {
        if (RestaurantOrderState.CREATED == state) {
            AggregateLifecycle.apply(new RestaurantOrderPreparedEvent(command.targetAggregateIdentifier(), command.auditEntry()));
        } else {
            throw new UnsupportedOperationException("CREATED is not the state.");
        }
    }

    @EventSourcingHandler
    void on(final RestaurantOrderPreparedEvent event) {
        state = RestaurantOrderState.PREPARED;
    }

    @Override
    public boolean equals(final Object o) {
        if (this == o)
            return true;
        if (o == null || getClass() != o.getClass())
            return false;
        final RestaurantOrder that = (RestaurantOrder) o;
        return id.equals(that.id) && restaurantId.equals(that.restaurantId) && state == that.state && lineItems.equals(that.lineItems);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, restaurantId, state, lineItems);
    }
}
