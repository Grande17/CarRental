package com.grande.car.domain;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;


@AllArgsConstructor
@NoArgsConstructor
@Data
@Entity
@Table(name = "CARS")
public class Car {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String brand;
    private String model;
    private Integer yearOfProduction;
    @Enumerated(value = EnumType.STRING)
    private Classification classification;
    private Integer km;
    private String plates;
    @Enumerated(value = EnumType.STRING)
    private Status status;

    public Car(String brand, String model, Integer yearOfProduction, Classification classification, Integer km, String plates, Status status) {
        this.brand = brand;
        this.model = model;
        this.yearOfProduction = yearOfProduction;
        this.classification = classification;
        this.km = km;
        this.plates = plates;
        this.status = status;
    }
}
