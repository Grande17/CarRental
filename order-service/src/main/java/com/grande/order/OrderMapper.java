package com.grande.order;

import com.grande.order.domain.Order;
import com.grande.order.domain.OrderDto;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class OrderMapper {

    private final ModelMapper mapper;

    public Order mapToOrder(OrderDto orderDto){
        return mapper.map(orderDto,Order.class);
    }
    public OrderDto mapToOrderDto(Order order){
        return mapper.map(order, OrderDto.class);
    }
    public List<OrderDto> mapToListOrderDto(final List<Order> orders){
        return orders.stream()
                .map(this::mapToOrderDto)
                .toList();
    }
}
