package com.grande.order;

import com.grande.clients.car.CarClient;
import com.grande.clients.car.CarDto;
import com.grande.clients.car.Status;
import com.grande.clients.user.UserClient;
import com.grande.clients.user.UserDto;
import com.grande.order.domain.CreateOrderDto;
import com.grande.order.domain.Order;
import com.grande.order.domain.OrderDto;
import com.grande.order.domain.RentalStatus;
import com.netflix.discovery.converters.Auto;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.List;

@Service
@RequiredArgsConstructor
public class OrderService {

    private final OrderRepository repository;
    private final OrderMapper mapper;
    private final CarClient carClient;
    private final UserClient userClient;



    public List<CarDto> getList(){
        return carClient.getCars();
    }
    public OrderDto createOrder(CreateOrderDto createOrderDto) throws Exception {
        int i = createOrderDto.getRentalEnds().compareTo(createOrderDto.getRentalStart());
        UserDto user = userClient.getUser(createOrderDto.getUserId());
        List<CarDto> list = carClient.getCars().stream()
                .filter(x -> x.getClassification().equals(createOrderDto.getClassification()) && x.getStatus().equals(Status.AVAILABLE))
                .toList();
        BigDecimal cost = BigDecimal.valueOf(i).multiply(BigDecimal.valueOf(createOrderDto.getClassification().getRates()));

        if(user != null){
            if (list.size() > 0){
                Order order = new Order(createOrderDto.getId(),createOrderDto.getUserId(), list.get(0).getId(),createOrderDto.getRentalStart(),createOrderDto.getRentalEnds(),cost, RentalStatus.BOOKED);
                repository.save(order);
                return mapper.mapToOrderDto(order);

            }else{
                throw new Exception("NO CARS POSSIBLE TO RENT");
            }

        }else{
            throw new Exception("User not found");
        }

    }


}
