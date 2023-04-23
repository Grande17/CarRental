package com.grande.car.service;

import com.grande.car.exceptions.CarNotFoundException;
import com.grande.car.repository.CarRepository;
import com.grande.car.exceptions.PlatesAlreadyUsedException;
import com.grande.car.exceptions.PlatesNotFoundException;
import com.grande.car.domain.Car;
import com.grande.car.domain.CarDto;
import com.grande.car.mapper.CarMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class CarService {

    private final CarRepository carRepository;
    private final CarMapper carMapper;

    public void addNewCar(CarDto carDto) throws PlatesAlreadyUsedException {
        if (carRepository.findByPlates(carDto.getPlates()).isPresent()){
            throw new PlatesAlreadyUsedException();
        }else{
            carRepository.save(carMapper.mapToCar(carDto));
        }
    }
    public CarDto getCarByPlates(String plates) throws PlatesNotFoundException {
        Optional<Car> byPlates = carRepository.findByPlates(plates);
        if (byPlates.isEmpty()){
            throw new PlatesNotFoundException();
        }else{
            return carMapper.mapToCarDto(byPlates.get());
        }
    }
    public List<CarDto> getAllCars(){
        return carMapper.mapToCarDtoList(carRepository.findAll());
    }
    public void deleteCarByPlates(String plates) throws PlatesNotFoundException {
        if (carRepository.findByPlates(plates).isEmpty()){
            throw new PlatesNotFoundException();
        }else{
            carRepository.deleteByPlates(plates);
        }
    }
    public void deleteCarById(Long id) throws CarNotFoundException {
        if (carRepository.findById(id).isEmpty()){
            throw new CarNotFoundException();
        }else{
            carRepository.deleteById(id);
        }
    }
    public void updateCar(CarDto carDto) throws CarNotFoundException {
        if (carRepository.findById(carDto.getId()).isEmpty()){
            throw new CarNotFoundException();
        }else{
            carRepository.save(carMapper.mapToCar(carDto));
        }
    }

}
