package com.humgry.humgrycustomer.restaurant.domain.api.model.valueObjects;

import java.util.List;

public record RestaurantMenu(List<MenuItem> menuItems, String menuVersion) {}
