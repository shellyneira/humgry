package com.example.humgrycustomer.domain;

import com.example.humgrycustomer.common.domain.api.models.valueObjects.AuditEntry;
import com.example.humgrycustomer.common.domain.api.models.valueObjects.Money;
import com.example.humgrycustomer.customer.domain.CustomerOrder;
import com.example.humgrycustomer.customer.domain.api.commands.MarkCustomerOrderAsDeliveredCommand;
import com.example.humgrycustomer.customer.domain.api.events.CustomerOrderCreatedEvent;
import com.example.humgrycustomer.customer.domain.api.events.CustomerOrderDeliveredEvent;
import com.example.humgrycustomer.customer.domain.api.models.valueObjects.CustomerId;
import com.example.humgrycustomer.customer.domain.api.models.valueObjects.CustomerOrderId;
import org.axonframework.messaging.interceptors.BeanValidationInterceptor;
import org.axonframework.test.aggregate.AggregateTestFixture;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import java.math.BigDecimal;
import java.time.LocalDate;

class CustomerOrderAggregateTest {
    private AggregateTestFixture<? extends CustomerOrder> fixture;
    private final String who = "shelly";
    private final AuditEntry auditEntry = new AuditEntry(who, LocalDate.now());
    private final CustomerOrderId customerOrderId = new CustomerOrderId("orderId");
    private final CustomerId customerId = new CustomerId("customerId");
    private final Money orderTotal = new Money(BigDecimal.valueOf(100));

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

        fixture.given(customerOrderCreatedEvent)
                .when(markCustomerOrderAsDeliveredCommand)
                .expectSuccessfulHandlerExecution()
                .expectEvents(customerOrderDeliveredEvent);
    }
}
