package com.grande.order;

import com.grande.clients.car.CarDto;
import com.grande.clients.car.Classification;
import com.grande.order.domain.CreateOrderDto;
import com.grande.order.domain.OrderDto;
import lombok.RequiredArgsConstructor;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.List;

@RestController
@RequestMapping("v1/order")
@RequiredArgsConstructor
public class OrderController {

    private final OrderService orderService;

    @GetMapping
    public List<CarDto> cars(){
        return orderService.getList();
    }
    @GetMapping("/1")
    public CreateOrderDto getOrder(){
        return new CreateOrderDto(1L,1L, Classification.C,LocalDate.now(),LocalDate.of(2023,5,10));
    }
    @PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE)
    public OrderDto createOrder(@RequestBody CreateOrderDto createOrderDto) throws Exception {
        return orderService.createOrder(createOrderDto);
    }
}
