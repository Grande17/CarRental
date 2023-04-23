package com.grande.controller;

import com.grande.exceptions.NotEnoughMoneyOnBalance;
import com.grande.service.UserService;
import com.grande.domain.UserDto;
import com.grande.exceptions.UserNotFoundException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.http.MediaType;


import java.math.BigDecimal;
import java.util.Enumeration;
import java.util.List;

@RestController
@RequestMapping("v1/user")
@RequiredArgsConstructor
public class UserController {

    private final UserService service;

    @PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE)
    @ResponseStatus(HttpStatus.CREATED)
    public ResponseEntity<Void> createUser(@RequestBody UserDto userDto) throws Exception {
        service.createNewUser(userDto);
        return ResponseEntity.ok().build();

    }
    @GetMapping
    public List<UserDto> getAllUsers(){
        return service.getAllUsers();
    }
    @GetMapping(value = "/{id}")
    public UserDto getUser(@PathVariable Long id) throws Exception {
        return service.getUserById(id);
    }
    @PutMapping

    public void updateUser(@RequestBody UserDto userDto) throws Exception {
        service.updateUser(userDto);
    }
    @DeleteMapping(value = "/{id}")
    @ResponseStatus(HttpStatus.OK)
    public void deleteUser(@PathVariable Long id) throws Exception {
        service.deleteUserById(id);
    }
    @PutMapping(value = "/type/{id}/{typeOfAcc}")
    public ResponseEntity<Void> updateUserType(@PathVariable Long id, @PathVariable String typeOfAcc) throws UserNotFoundException {
        service.updateTypeOfUser(id,typeOfAcc);
        return ResponseEntity.ok().build();
    }
    @PutMapping("/balance/{id}/{value}")
    public void addBalance(@PathVariable Long id, @PathVariable BigDecimal value) throws UserNotFoundException {
        service.addBalance(id,value);
    }
    @PutMapping("/payForRental/{id}/{value}")
    public void payForRental(@PathVariable Long id, @PathVariable BigDecimal value) throws UserNotFoundException, NotEnoughMoneyOnBalance {
        service.payForRental(id,value);
    }
}
