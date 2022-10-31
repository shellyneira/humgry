package com.humgry.humgrycourier.courier.domain.model;

import com.humgry.humgrycourier.courier.domain.model.commands.ValidateOrderByCourierInternalCommand;
import com.humgry.humgrycourier.courier.domain.model.events.CourierNotFoundForOrderInternalEvent;
import org.axonframework.commandhandling.CommandHandler;
import org.axonframework.eventhandling.EventBus;
import org.axonframework.modelling.command.AggregateNotFoundException;
import org.axonframework.modelling.command.Repository;

import static org.axonframework.eventhandling.GenericEventMessage.asEventMessage;

class CourierCommandHandler {
    private Repository<Courier> repository;
    private EventBus eventBus;

    @CommandHandler
    void handle(final ValidateOrderByCourierInternalCommand command) {
        try {
            repository.load(command.courierId().identifier()).execute(x -> x.validateOrder(command.targetAggregateIdentifier(), command.auditEntry()));
        }
        catch (final AggregateNotFoundException exception) {
            eventBus.publish(asEventMessage(new CourierNotFoundForOrderInternalEvent(command.courierId(), command.targetAggregateIdentifier(), command.auditEntry())));
        }
    }

}