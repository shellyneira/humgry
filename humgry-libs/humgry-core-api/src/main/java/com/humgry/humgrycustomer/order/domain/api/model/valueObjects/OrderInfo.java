package com.humgry.humgrycustomer.order.domain.api.model.valueObjects;

import com.google.common.collect.ImmutableList;

import javax.validation.Valid;

public record OrderInfo(String customerId, String restaurantId, @Valid ImmutableList<OrderLineItem> lineItems) {}
