package com.grande;

import com.grande.exceptions.UserNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.http.MediaType;


import java.util.List;

@RestController
@RequestMapping("v1/user")
@RequiredArgsConstructor
public class UserController {

    private final UserService service;

    @PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE)
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
    public void deleteUser(@PathVariable Long id) throws Exception {
        service.deleteUserById(id);
    }
    @PutMapping(value = "/type/{id}/{typeOfAcc}")
    public ResponseEntity<Void> updateUserType(@PathVariable Long id, @PathVariable String typeOfAcc) throws UserNotFoundException {
        service.updateTypeOfUser(id,typeOfAcc);
        return ResponseEntity.ok().build();
    }
}
