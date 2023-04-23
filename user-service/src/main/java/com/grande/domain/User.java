package com.grande.domain;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.time.LocalDate;

@NoArgsConstructor
@Data
@Entity
@Table(name = "USERS")
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.TABLE)
    private Long id;
    private String name;
    private String surname;
    private String email;
    private LocalDate dateOfBirth;
    @Enumerated(value = EnumType.STRING)
    private TypeOfUser typeOfUser;
    private BigDecimal balance;

    public User(Long id, String name, String surname, String email, LocalDate dateOfBirth, TypeOfUser typeOfUser, BigDecimal balance) {
        this.id = id;
        this.name = name;
        this.surname = surname;
        this.email = email;
        this.dateOfBirth = dateOfBirth;
        this.typeOfUser = typeOfUser;
        this.balance = balance;
    }
}
