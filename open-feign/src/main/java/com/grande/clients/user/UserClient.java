package com.grande.clients.user;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@FeignClient(
        value = "USER",
        path = "v1/user"
)
public interface UserClient {

    @GetMapping(value = "/{id}")
    UserDto getUser(@PathVariable Long id);

}
