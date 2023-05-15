package com.grande.clients.user;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.math.BigDecimal;
import java.util.List;

@FeignClient(
        value = "USER",
        path = "v1/user"
)
public interface UserClient {

    @PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE)
    ResponseEntity<Void> createUser(@RequestBody UserDto userDto);

    @GetMapping
    List<UserDto> getAllUsers();
    @GetMapping(value = "/{id}")
    UserDto getUser(@PathVariable Long id);
    @PutMapping
    void updateUser(@RequestBody UserDto userDto);
    @DeleteMapping(value = "/{id}")
    void deleteUser(@PathVariable Long id);
    @PutMapping(value = "/type/{id}/{typeOfAcc}")
    ResponseEntity<Void> updateUserType(@PathVariable Long id, @PathVariable String typeOfAcc);
    @PutMapping("/balance/{id}/{value}")
    void addBalance(@PathVariable Long id, @PathVariable BigDecimal value);
    @PutMapping("/payForRental/{id}/{value}")
    void payForRental(@PathVariable Long id, @PathVariable BigDecimal value);
}
