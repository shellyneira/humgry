package com.humgry.humgrycustomer.restaurant.domain.api.model.valueObjects;

import com.google.common.collect.ImmutableList;

public record RestaurantOrderDetails(ImmutableList<RestaurantOrderLineItem> lineItems) {}
