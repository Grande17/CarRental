package com.grande.order.domain;

import com.grande.clients.car.Classification;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class CreateOrderDto {
    private Long id;
    private Long userId;
    private Classification classification;
    private LocalDate rentalStart;
    private LocalDate rentalEnds;

}
