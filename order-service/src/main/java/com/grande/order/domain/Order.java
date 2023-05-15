package com.grande.order.domain;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.time.LocalDate;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Entity
@Table(name = "ORDERS")
public class Order {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private Long userId;
    private Long carId;
    private LocalDate rentalStart;
    private LocalDate rentalEnds;
    private BigDecimal totalCost;
    @Enumerated(value = EnumType.STRING)
    private RentalStatus status;

    public Order(Long userId, Long carId, LocalDate rentalStart, LocalDate rentalEnds, BigDecimal totalCost, RentalStatus status) {
        this.userId = userId;
        this.carId = carId;
        this.rentalStart = rentalStart;
        this.rentalEnds = rentalEnds;
        this.totalCost = totalCost;
        this.status = status;
    }
}
