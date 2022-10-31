package com.humgry.humgrycustomer.common.domain.api.models.valueObjects;

import java.math.BigDecimal;

public record Money(BigDecimal amount) {
    public Money add(final Money amountToAdd) {
        return new Money(amount.add(amountToAdd.amount));
    }

    public Money multiply(final int timesToMultiply) {
        return new Money(amount.multiply(BigDecimal.valueOf(timesToMultiply)));
    }

    public boolean isGreaterThanOrEqual(final Money otherAmount) {
        final var result = amount.compareTo(otherAmount.amount);
        return result >= 0;
    }
}
