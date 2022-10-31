package com.humgry.humgrycustomer.order.domain.api.model.valueObjects;

enum OrderState {
    CREATE_PENDING, VERIFIED_BY_CUSTOMER, VERIFIED_BY_RESTAURANT,
    PREPARED, READY_FOR_DELIVERY, DELIVERED, REJECTED, CANCEL_PENDING, CANCELLED
}
