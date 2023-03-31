package com.grande;

import lombok.RequiredArgsConstructor;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("v1/car")
@RequiredArgsConstructor
public class CarController {

    private final CarService carService;

    @PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE)
    public void addNewCar(@RequestBody CarDto carDto) throws PlatesAlreadyUsedException {
        carService.addNewCar(carDto);
    }

    @GetMapping
    public List<CarDto> getCars(){
        return carService.getAllCars();
    }
    @GetMapping(value = "/{plates}")
    public CarDto getCarByPlates(@PathVariable String plates) throws PlatesNotFoundException {
        return carService.getCarByPlates(plates);
    }
    @DeleteMapping(value = "/plates/{plates}")
    public void deleteCarByPlates(@PathVariable String plates) throws PlatesNotFoundException {
        carService.deleteCarByPlates(plates);
    }
    @DeleteMapping(value = "/{id}")
    public void deleteCarById(@PathVariable Long id) throws CarNotFoundException {
        carService.deleteCarById(id);
    }
    @PutMapping
    public void updateCar(@RequestBody CarDto carDto) throws CarNotFoundException {
        carService.updateCar(carDto);
    }
}
