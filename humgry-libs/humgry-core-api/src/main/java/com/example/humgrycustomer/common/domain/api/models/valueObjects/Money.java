package com.example.humgrycustomer.common.domain.api.models.valueObjects;

import java.math.BigDecimal;

public record Money(BigDecimal amount) {
    public Money add(Money amountToAdd) {
        return new Money(amount.add(amountToAdd.amount));
    }

    public Money multiply(int timesToMultiply) {
        return new Money(amount.multiply(BigDecimal.valueOf(timesToMultiply)));
    }

    public boolean isGreaterThanOrEqual(Money otherAmount){
        var result = amount.compareTo(otherAmount.amount);
        return result >= 0;
    }
}
