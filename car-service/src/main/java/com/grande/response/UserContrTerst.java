package com.grande.response;

import com.grande.clients.user.UserClient;
import com.grande.clients.user.UserDto;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/v1/test")
@RequiredArgsConstructor
public class UserContrTerst {

    private final UserClient userClient;

    @GetMapping
    public UserDto getAllUsers(){
        return userClient.getUser(2L);
    }
}
