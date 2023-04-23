package com.grande.mapper;

import com.grande.domain.User;
import com.grande.domain.UserDto;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class UserMapper {

    private final ModelMapper mapper;

    public User mapToUser(UserDto userDto){
        return mapper.map(userDto,User.class);
    }

    public UserDto mapToUserDto(User user){
        return mapper.map(user, UserDto.class);
    }

    public List<UserDto> mapToUserDtoList(final List<User> users){
        return users.stream()
                .map(this::mapToUserDto)
                .toList();
    }
}
