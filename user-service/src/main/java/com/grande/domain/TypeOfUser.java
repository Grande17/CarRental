package com.grande.domain;

import java.math.BigDecimal;

public enum TypeOfUser {

    STANDARD(BigDecimal.ONE),
    PREMIUM(BigDecimal.valueOf(0.95));

    private BigDecimal rates;

    TypeOfUser(BigDecimal rates) {
        this.rates = rates;
    }
}
