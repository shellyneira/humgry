package com.humgry.humgrycustomer.order.domain.api.model.valueObjects;

import com.humgry.humgrycustomer.common.domain.api.models.valueObjects.Money;

import javax.validation.Valid;

public record OrderLineItem(String menuItemId, String name, @Valid Money price, int quantity, Money total) {
}
