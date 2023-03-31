package com.grande;

import com.grande.exceptions.EmailAlreadyUsedException;
import com.grande.exceptions.UserNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;


@Service
@RequiredArgsConstructor
public class UserService {

    private final UserMapper mapper;
    private final UserRepository repository;

    public void createNewUser(UserDto userDto) throws Exception {
        if (repository.findByEmail(userDto.getEmail()).isEmpty()) {
            repository.save(mapper.mapToUser(userDto));
        } else {
            throw new EmailAlreadyUsedException();
        }
    }
    public UserDto getUserById(Long id) throws Exception {
        Optional<User> byId = repository.findById(id);
        if(byId.isPresent()){
            return mapper.mapToUserDto(byId.get());
        }else{
            throw new UserNotFoundException();
        }
    }
    public List<UserDto> getAllUsers(){
        return mapper.mapToUserDtoList(repository.findAll());
    }
    public void updateUser(UserDto userDto) throws Exception {
        if(repository.findById(userDto.getId()).isEmpty()){
            throw new UserNotFoundException();
        }else{
            repository.save(mapper.mapToUser(userDto));
        }
    }
    public void deleteUserById(Long id) throws Exception {
        if (repository.findById(id).isEmpty()){
            throw new UserNotFoundException();
        }else{
            repository.deleteById(id);
        }
    }
    public void updateTypeOfUser(Long id, String typeOfUser) throws UserNotFoundException {
        Optional<User> byId = repository.findById(id);
        if (byId.isEmpty()){
            throw new UserNotFoundException();
        }
        switch (typeOfUser.toUpperCase()){
            case "STANDARD":
                byId.get().setTypeOfUser(TypeOfUser.STANDARD);
                repository.save(byId.get());
                break;
            case "PREMIUM":
                byId.get().setTypeOfUser(TypeOfUser.PREMIUM);
                repository.save(byId.get());
                break;
        }

    }

}
