package com.humgry.humgryrestaurant.domain;

import com.humgry.humgrycustomer.restaurant.domain.api.model.commands.CreateRestaurantCommand;
import com.humgry.humgrycustomer.restaurant.domain.api.model.commands.CreateRestaurantOrderCommand;
import com.humgry.humgrycustomer.restaurant.domain.api.model.events.RestaurantCreatedEvent;
import com.humgry.humgrycustomer.restaurant.domain.api.model.valueObjects.*;
import org.axonframework.commandhandling.CommandHandler;
import org.axonframework.eventsourcing.EventSourcingHandler;
import org.axonframework.modelling.command.AggregateIdentifier;
import org.axonframework.modelling.command.AggregateLifecycle;
import java.util.HashSet;
import java.util.Objects;
import java.util.stream.Collectors;

class Restaurant {

    @AggregateIdentifier
    private RestaurantId id;
    private String name;
    private RestaurantMenu menu;
    private RestaurantState state;

    public Restaurant() {
    }

    @CommandHandler
    Restaurant(final CreateRestaurantCommand command) {
        AggregateLifecycle.apply(new RestaurantCreatedEvent(command.name(), command.menu(), command.targetAggregateIdentifier(),
                                                            command.auditEntry()));
    }

    @EventSourcingHandler
    void on(final RestaurantCreatedEvent event) {
        id = event.restaurantId();
        name = event.name();
        menu = event.restaurantMenu();
        state = RestaurantState.OPEN;
    }

    @CommandHandler
    void handle(final CreateRestaurantOrderCommand command) {
        if (new HashSet<>(menu.menuItems().stream().map(MenuItem::id).collect(Collectors.toList()))
                .containsAll(command.orderDetails().lineItems().stream().map(RestaurantOrderLineItem::menuItemId).toList()))
        {
            AggregateLifecycle.apply(new RestaurantOrder(command));
        }
        else throw new UnsupportedOperationException("Invalid order - Does not exist in the menu.");
    }

    @Override
    public boolean equals(final Object o) {
        if (this == o)
            return true;
        if (o == null || getClass() != o.getClass())
            return false;
        final Restaurant that = (Restaurant) o;
        return id.equals(that.id) && name.equals(that.name) && menu.equals(that.menu) && state == that.state;
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, name, menu, state);
    }
}
