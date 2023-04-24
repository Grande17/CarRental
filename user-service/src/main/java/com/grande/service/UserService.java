package com.grande.service;

import com.grande.domain.TypeOfUser;
import com.grande.domain.User;
import com.grande.domain.UserDto;
import com.grande.exceptions.EmailAlreadyUsedException;
import com.grande.exceptions.NotEnoughMoneyOnBalance;
import com.grande.exceptions.UserNotFoundException;
import com.grande.mapper.UserMapper;
import com.grande.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
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
        switch (typeOfUser.toUpperCase()) {
            case "STANDARD" -> {
                byId.get().setTypeOfUser(TypeOfUser.STANDARD);
                repository.save(byId.get());
            }
            case "PREMIUM" -> {
                byId.get().setTypeOfUser(TypeOfUser.PREMIUM);
                byId.get().setBalance(byId.get().getBalance().subtract(BigDecimal.valueOf(99.99)));
                repository.save(byId.get());
            }
        }

    }
    public void addBalance(Long id, BigDecimal value) throws UserNotFoundException {
        Optional<User> byId = repository.findById(id);
        if (byId.isPresent()){
            byId.get().setBalance(value);
            repository.save(byId.get());
        }else{
            throw new UserNotFoundException();
        }
    }

    public void payForRental(Long id, BigDecimal value) throws UserNotFoundException, NotEnoughMoneyOnBalance {
        Optional<User> byId = repository.findById(id);
        if (byId.isPresent()){
            checkBalanceOnPlus(byId.get().getBalance(), value);
            byId.get().setBalance(byId.get().getBalance().subtract(value));
            repository.save(byId.get());

        }else {
            throw new UserNotFoundException();
        }
    }
    private void checkBalanceOnPlus(BigDecimal onBalance, BigDecimal toPay) throws NotEnoughMoneyOnBalance {
        int i = onBalance.compareTo(toPay);
        if (i < 0){
         throw new NotEnoughMoneyOnBalance();
        }
    }

}
