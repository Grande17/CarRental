package com.grande;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UserService {

    private final UserMapper mapper;
    private final UserRepository repository;

    public void createNewUser(UserDto userDto){
        repository.save(mapper.mapToUser(userDto));
    }


}
