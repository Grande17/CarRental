package com.grande.clients.car;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@FeignClient(
        value = "CAR",
        path = "v1/car"
)
public interface CarClient {
    @PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE)
    void addNewCar(@RequestBody CarDto carDto);

    @GetMapping
    List<CarDto> getCars();

    @GetMapping(value = "/{plates}")
    CarDto getCarByPlates(@PathVariable String plates);

    @DeleteMapping(value = "/plates/{plates}")
    void deleteCarByPlates(@PathVariable String plates);

    @DeleteMapping(value = "/{id}")
    void deleteCarById(@PathVariable Long id);

    @PutMapping
    void updateCar(@RequestBody CarDto carDto);

    @PutMapping("/status/{id}/{status}")
    void changeStatus(@PathVariable Long id,@PathVariable String status);
}
