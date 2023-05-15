package com.grande.order.domain;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.math.BigDecimal;
import java.time.LocalDate;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class OrderDto {
    private Long id;
    private Long userId;
    private Long carId;
    private LocalDate rentalStart;
    private LocalDate rentalEnds;
    private BigDecimal totalCost;
    private RentalStatus status;
}
