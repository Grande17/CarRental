package com.grande.car.domain;

public enum Classification {
    A(79),
    B(99),
    C(119),
    D(139),
    E(199),
    F(299),
    S(349),
    J(349);

    public Integer rates;
    Classification(Integer rates) {
    }
}
