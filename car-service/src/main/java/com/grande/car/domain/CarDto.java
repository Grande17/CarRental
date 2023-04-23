package com.grande.car.domain;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class CarDto {
    private Long id;
    private String brand;
    private String model;
    private Integer yearOfProduction;
    private Classification classification;
    private Integer km;
    private String plates;
}
