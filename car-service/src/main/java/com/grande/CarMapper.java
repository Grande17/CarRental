package com.grande;

import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class CarMapper {

    private final ModelMapper modelMapper;

    public Car mapToCar(CarDto carDto){
        return modelMapper.map(carDto,Car.class);
    }
    public CarDto mapToCarDto(Car car){
        return modelMapper.map(car, CarDto.class);
    }
    public List<CarDto> mapToCarDtoList(final List<Car> cars){
        return cars.stream()
                .map(this::mapToCarDto)
                .toList();
    }
}
