package SampleWeek2.order.mapper;

import SampleWeek2.coffee.entity.Coffee;
import SampleWeek2.coffee.service.CoffeeService;
import SampleWeek2.order.dto.OrderCoffeeResponseDTO;
import SampleWeek2.order.dto.OrderPostDTO;
import SampleWeek2.order.dto.OrderResponseDTO;
import SampleWeek2.order.entity.CoffeeRef;
import SampleWeek2.order.entity.Order;
import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;
import org.springframework.data.jdbc.core.mapping.AggregateReference;

import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@Mapper(componentModel = "spring", unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface OrderMapper {
    default Order orderPostDtoToOrder(OrderPostDTO orderPostDTO) {
        Order order = new Order();

        order.setMemberId(new AggregateReference.IdOnlyAggregateReference<>(orderPostDTO.getMemberId()));
        Set<CoffeeRef> orderCoffees = orderPostDTO.getOrderCoffees()
                .stream()
                .map(orderCoffeeDTO ->
                        CoffeeRef.builder()
                                .coffeeId(orderCoffeeDTO.getCoffeeId())
                                .quantity(orderCoffeeDTO.getQuantity())
                                .build())
                .collect(Collectors.toSet());
        order.setOrderCoffees(orderCoffees);

        return order;
    }

    default OrderResponseDTO orderToOrderResponseDto(CoffeeService coffeeService, Order order) {

        long memberId = order.getMemberId().getId();

        List<OrderCoffeeResponseDTO> orderCoffees =
                orderCoffeesToOrderCoffeeResponseDto(coffeeService, order.getOrderCoffees());

        OrderResponseDTO orderResponseDTO = new OrderResponseDTO();
        orderResponseDTO.setOrderCoffees(orderCoffees);
        orderResponseDTO.setMemberId(memberId);
        orderResponseDTO.setCreatedAt(order.getCreatedAt());
        orderResponseDTO.setOrderId(order.getOrderId());
        orderResponseDTO.setOrderStatus(order.getOrderStatus());

        return orderResponseDTO;
    }

    default List<OrderCoffeeResponseDTO> orderCoffeesToOrderCoffeeResponseDto(
            CoffeeService coffeeService,
            Set<CoffeeRef> orderCoffees) {

        return orderCoffees.stream()
                .map(coffeeRef -> {
                    Coffee coffee = coffeeService.findCoffee(coffeeRef.getCoffeeId());
                    return new OrderCoffeeResponseDTO(coffee.getCoffeeId(),
                            coffee.getKorName(),
                            coffee.getEngName(),
                            coffee.getPrice(),
                            coffeeRef.getQuantity());
                }).collect(Collectors.toList());

    }
}
