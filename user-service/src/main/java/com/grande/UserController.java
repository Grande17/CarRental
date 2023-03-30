package com.grande;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;
import org.springframework.http.MediaType;


import java.time.LocalDate;

@RestController
@RequestMapping("v1/user")
@RequiredArgsConstructor
public class UserController {

    private final UserService service;

    @PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE)
    public void createUser(@RequestBody UserDto userDto){
        service.createNewUser(userDto);
    }
    @GetMapping
    public User getUser(){
        return new User(1L,"Mateusz","Markowski","email@email.pl", LocalDate.of(1995,7,19),TypeOfUser.PREMIUM);
    }
}
