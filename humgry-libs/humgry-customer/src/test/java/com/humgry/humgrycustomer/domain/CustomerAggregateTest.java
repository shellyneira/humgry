package com.humgry.humgrycustomer.domain;

import com.humgry.humgrycustomer.common.domain.api.models.valueObjects.AuditEntry;
import com.humgry.humgrycustomer.common.domain.api.models.valueObjects.Money;
import com.humgry.humgrycustomer.common.domain.api.models.valueObjects.PersonName;
import com.humgry.humgrycustomer.customer.domain.Customer;
import com.humgry.humgrycustomer.customer.domain.api.commands.CreateCustomerCommand;
import com.humgry.humgrycustomer.customer.domain.api.commands.CreateCustomerOrderCommand;
import com.humgry.humgrycustomer.customer.domain.api.events.CustomerCreatedEvent;
import com.humgry.humgrycustomer.customer.domain.api.events.CustomerOrderCreatedEvent;
import com.humgry.humgrycustomer.customer.domain.api.models.valueObjects.CustomerId;
import com.humgry.humgrycustomer.customer.domain.api.models.valueObjects.CustomerOrderId;
import org.axonframework.messaging.interceptors.BeanValidationInterceptor;
import org.axonframework.test.aggregate.AggregateTestFixture;
import org.axonframework.test.aggregate.FixtureConfiguration;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import java.math.BigDecimal;
import java.time.LocalDate;

class CustomerAggregateTest {

    private final String who = "Shelly";
    private final PersonName personName = new PersonName("Ayla", "Yim");
    private final AuditEntry auditEntry = new AuditEntry(who, LocalDate.now());
    private final Money orderLimit = new Money(BigDecimal.valueOf(1000000));
    private final CustomerId customerId = new CustomerId("customerId");
    private final CustomerOrderId customerOrderId = new CustomerOrderId("customerOrderId");
    private FixtureConfiguration<Customer> fixture;

    @BeforeEach
    public void setUp() {
        fixture = new AggregateTestFixture<>(Customer.class);
        fixture.registerCommandDispatchInterceptor(new BeanValidationInterceptor<>());
    }

    @Test
    void createCustomerTest() {
        var createCustomerCommand = new CreateCustomerCommand(customerId, personName, orderLimit, auditEntry);
        var customerCreatedEvent = new CustomerCreatedEvent(createCustomerCommand.targetAggregateIdentifier(), personName, orderLimit, auditEntry);

        fixture.given().when(createCustomerCommand).expectEvents(customerCreatedEvent);
    }

    @Test
    void createCustomerOrderTest() {
        var createCustomerOrderCommand = new CreateCustomerOrderCommand(customerId, customerOrderId, orderLimit, auditEntry);
        var customerCreatedEvent = new CustomerCreatedEvent(createCustomerOrderCommand.targetAggregateIdentifier(), personName, orderLimit.add(new Money(BigDecimal.ONE)), auditEntry);
        var customerOrderCreatedEvent = new CustomerOrderCreatedEvent(customerId, createCustomerOrderCommand.customerOrderId(), orderLimit, auditEntry);


        fixture.given(customerCreatedEvent).when(createCustomerOrderCommand).expectEvents(customerOrderCreatedEvent);
    }

    @Test
    void createCustomerOrderFailOrderLimitTest() {
        var createCustomerOrderCommand = new CreateCustomerOrderCommand(customerId, customerOrderId, orderLimit, auditEntry);
        var customerCreatedEvent = new CustomerCreatedEvent(createCustomerOrderCommand.targetAggregateIdentifier(), personName, orderLimit, auditEntry);

        fixture.given(customerCreatedEvent).when(createCustomerOrderCommand).expectException(UnsupportedOperationException.class);
    }
}
