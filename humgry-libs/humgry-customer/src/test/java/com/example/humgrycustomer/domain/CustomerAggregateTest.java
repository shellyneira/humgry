package com.example.humgrycustomer.domain;

import com.example.humgrycustomer.common.domain.api.models.valueObjects.AuditEntry;
import com.example.humgrycustomer.common.domain.api.models.valueObjects.Money;
import com.example.humgrycustomer.common.domain.api.models.valueObjects.PersonName;
import com.example.humgrycustomer.customer.domain.Customer;
import com.example.humgrycustomer.customer.domain.api.commands.CreateCustomerCommand;
import com.example.humgrycustomer.customer.domain.api.commands.CreateCustomerOrderCommand;
import com.example.humgrycustomer.customer.domain.api.events.CustomerCreatedEvent;
import com.example.humgrycustomer.customer.domain.api.events.CustomerOrderCreatedEvent;
import com.example.humgrycustomer.customer.domain.api.models.valueObjects.CustomerId;
import com.example.humgrycustomer.customer.domain.api.models.valueObjects.CustomerOrderId;
import org.axonframework.messaging.interceptors.BeanValidationInterceptor;
import org.axonframework.test.aggregate.AggregateTestFixture;
import org.axonframework.test.aggregate.FixtureConfiguration;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import java.math.BigDecimal;
import java.time.LocalDate;

class CustomerAggregateTest {

    private FixtureConfiguration<Customer> fixture;
    private final String who = "Shelly";
    private final PersonName personName = new PersonName("Ayla", "Yim");
    private final AuditEntry auditEntry = new AuditEntry(who, LocalDate.now());
    private final Money orderLimit = new Money(BigDecimal.valueOf(1000000));
    private final CustomerId customerId = new CustomerId("customerId");
    private final CustomerOrderId customerOrderId = new CustomerOrderId("customerOrderId");

    @BeforeEach
    public void setUp(){
        fixture = new AggregateTestFixture<>(Customer.class);
        fixture.registerCommandDispatchInterceptor(new BeanValidationInterceptor<>());
    }

    @Test
    void createCustomerTest(){
        var createCustomerCommand = new CreateCustomerCommand(customerId, personName, orderLimit, auditEntry);
        var customerCreatedEvent = new CustomerCreatedEvent(createCustomerCommand.aggregateIdentifier(), personName, orderLimit, auditEntry);

        fixture.given()
                .when(createCustomerCommand)
                .expectEvents(customerCreatedEvent);
    }

    @Test
    void createCustomerOrderTest(){
        var createCustomerOrderCommand = new CreateCustomerOrderCommand(customerId, customerOrderId, orderLimit, auditEntry);
        var customerCreatedEvent = new CustomerCreatedEvent(createCustomerOrderCommand.aggregateIdentifier(), personName, orderLimit.add(new Money(BigDecimal.ONE)), auditEntry);
        var customerOrderCreatedEvent = new CustomerOrderCreatedEvent(customerId, createCustomerOrderCommand.customerOrderId(), orderLimit, auditEntry);


        fixture.given(customerCreatedEvent)
                .when(createCustomerOrderCommand)
                .expectEvents(customerOrderCreatedEvent);
    }

    @Test
    void createCustomerOrderFailOrderLimitTest(){
        var createCustomerOrderCommand = new CreateCustomerOrderCommand(customerId, customerOrderId, orderLimit, auditEntry);
        var customerCreatedEvent = new CustomerCreatedEvent(createCustomerOrderCommand.aggregateIdentifier(), personName, orderLimit, auditEntry);

        fixture.given(customerCreatedEvent)
                .when(createCustomerOrderCommand)
                .expectException(UnsupportedOperationException.class);
    }
}
