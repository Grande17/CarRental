package com.grande.car.service;

import com.grande.car.domain.Classification;
import com.grande.car.exceptions.CarNotFoundException;
import com.grande.car.exceptions.WrongStatusException;
import com.grande.car.repository.CarRepository;
import com.grande.car.exceptions.PlatesAlreadyUsedException;
import com.grande.car.exceptions.PlatesNotFoundException;
import com.grande.car.domain.Car;
import com.grande.car.domain.CarDto;
import com.grande.car.mapper.CarMapper;
import jakarta.annotation.PostConstruct;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

import static com.grande.car.domain.Status.*;

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
    public void changeStatus(Long id, String status) throws CarNotFoundException, WrongStatusException {
        Optional<Car> byId = carRepository.findById(id);
        if (byId.isEmpty()){
            throw new CarNotFoundException();
        }
        switch (status.toUpperCase()) {
            case "AVAILABLE" -> {
                byId.get().setStatus(AVAILABLE);
                carRepository.save(byId.get());
            }
            case "NOT_AVAILABLE" -> {
                byId.get().setStatus(NOT_AVAILABLE);
                carRepository.save(byId.get());
            }
            case "DAMAGED" -> {
                byId.get().setStatus(DAMAGED);
                carRepository.save(byId.get());
            }
            default -> throw new WrongStatusException();
        }
    }
    public void bringBackCar(Long id, Integer newKm) throws CarNotFoundException {
        Optional<Car> byId = carRepository.findById(id);
        if (byId.isPresent()){
            byId.get().setStatus(AVAILABLE);
            byId.get().setKm(newKm);
            carRepository.save(byId.get());
        }else{
            throw new CarNotFoundException();
        }
    }
    @PostConstruct
    public void data(){

        List<Car> cars = List.of(
                new Car("Chevrolet", "Spark", 2019, Classification.A, 56411, "WWE11112", AVAILABLE),
                new Car("Fiat", "500", 2022, Classification.A, 11263, "WWE11113",AVAILABLE),
                new Car("Kia", "Picanto", 2017, Classification.A, 54899, "WWE11114",AVAILABLE),
                new Car("Ford", "Fiesta", 2019, Classification.B, 78422, "WWE11115",AVAILABLE),
                new Car("Kia", "Rio", 2019, Classification.B, 11234, "WWE11116",AVAILABLE),
                new Car("Opel", "Corsa", 2018, Classification.B, 22111, "WWE11117",AVAILABLE),
                new Car("Honda", "Civic", 2019, Classification.C, 33214, "WWE11118",AVAILABLE),
                new Car("Audi", "A3", 2019, Classification.C, 55678, "WWE11119",AVAILABLE),
                new Car("Volkswagen", "Golf", 2019, Classification.C, 98532, "WWE11110",AVAILABLE),
                new Car("Ford", "Mondeo", 2015, Classification.D, 76087, "WWE11120",AVAILABLE),
                new Car("Toyota", "Camry", 2019, Classification.D, 53919, "WWE11121",AVAILABLE),
                new Car("Audi", "A4", 2023, Classification.D, 3316, "WWE11122",AVAILABLE),
                new Car("Audi", "A6", 2022, Classification.E, 12626, "WWE11123",AVAILABLE),
                new Car("BMW", "5", 2019, Classification.E, 2727, "WWE11124",AVAILABLE),
                new Car("Tesla", "Model S", 2019, Classification.E, 65626, "WWE11125",AVAILABLE),
                new Car("Audi", "A8", 2019, Classification.F, 12148, "WWE11126",AVAILABLE),
                new Car("BMW", "7", 2019, Classification.F, 30358, "WWE11127",AVAILABLE),
                new Car("Porsche", "Panamera", 2019, Classification.F, 9896, "WWE11134",AVAILABLE),
                new Car("Chevrolet", "Camaro", 2018, Classification.S, 94764, "WWE11135",AVAILABLE),
                new Car("Porsche", "Boxster", 2019, Classification.S, 90935, "WWE11136",AVAILABLE),
                new Car("Mazda", "MX5", 2019, Classification.S, 30795, "WWE11137",AVAILABLE),
                new Car("Toyota", "RAV4", 2019, Classification.J, 51135, "WWE11138",AVAILABLE),
                new Car("Ford", "Edge", 2019, Classification.J, 50354, "WWE11199",AVAILABLE),
                new Car("Volvo XC90", "Spark", 2019, Classification.J, 76069, "WWE11198",AVAILABLE)

        );
        carRepository.saveAll(cars);
    }

}
