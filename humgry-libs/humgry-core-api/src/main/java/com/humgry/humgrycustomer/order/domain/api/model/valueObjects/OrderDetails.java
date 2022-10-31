package com.humgry.humgrycustomer.order.domain.api.model.valueObjects;

import com.humgry.humgrycustomer.common.domain.api.models.valueObjects.Money;

public record OrderDetails(OrderInfo orderInfo, Money orderTotal) {}
