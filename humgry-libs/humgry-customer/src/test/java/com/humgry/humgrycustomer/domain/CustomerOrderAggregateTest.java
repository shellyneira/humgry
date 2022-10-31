package com.humgry.humgrycustomer.domain;

import com.humgry.humgrycustomer.common.domain.api.models.valueObjects.AuditEntry;
import com.humgry.humgrycustomer.common.domain.api.models.valueObjects.Money;
import com.humgry.humgrycustomer.customer.domain.CustomerOrder;
import com.humgry.humgrycustomer.customer.domain.api.commands.MarkCustomerOrderAsDeliveredCommand;
import com.humgry.humgrycustomer.customer.domain.api.events.CustomerOrderCreatedEvent;
import com.humgry.humgrycustomer.customer.domain.api.events.CustomerOrderDeliveredEvent;
import com.humgry.humgrycustomer.customer.domain.api.models.valueObjects.CustomerId;
import com.humgry.humgrycustomer.customer.domain.api.models.valueObjects.CustomerOrderId;
import org.axonframework.messaging.interceptors.BeanValidationInterceptor;
import org.axonframework.test.aggregate.AggregateTestFixture;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.math.BigDecimal;
import java.time.LocalDate;

class CustomerOrderAggregateTest {
    private final String who = "shelly";
    private final AuditEntry auditEntry = new AuditEntry(who, LocalDate.now());
    private final CustomerOrderId customerOrderId = new CustomerOrderId("orderId");
    private final CustomerId customerId = new CustomerId("customerId");
    private final Money orderTotal = new Money(BigDecimal.valueOf(100));
    private AggregateTestFixture<? extends CustomerOrder> fixture;

    @BeforeEach
    public void setUp() {
        fixture = new AggregateTestFixture<>(CustomerOrder.class);
        fixture.registerCommandDispatchInterceptor(new BeanValidationInterceptor<>());
    }

    @Test
    void markOrderAsDeliveredTest() {
        var customerOrderCreatedEvent = new CustomerOrderCreatedEvent(customerId, customerOrderId, orderTotal, auditEntry);
        var markCustomerOrderAsDeliveredCommand = new MarkCustomerOrderAsDeliveredCommand(customerOrderId, auditEntry);
        var customerOrderDeliveredEvent = new CustomerOrderDeliveredEvent(customerOrderId, auditEntry);

        fixture.given(customerOrderCreatedEvent).when(markCustomerOrderAsDeliveredCommand).expectSuccessfulHandlerExecution().expectEvents(customerOrderDeliveredEvent);
    }
}
