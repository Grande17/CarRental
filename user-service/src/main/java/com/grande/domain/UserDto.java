package com.grande.domain;

import com.grande.domain.TypeOfUser;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.math.BigDecimal;
import java.time.LocalDate;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class UserDto {

    private Long id;
    private String name;
    private String surname;
    private String email;
    private LocalDate dateOfBirth;
    private TypeOfUser typeOfUser = TypeOfUser.STANDARD;
    private BigDecimal balance = BigDecimal.ZERO;


}
