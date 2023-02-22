package SampleWeek2.order.dto;

import SampleWeek2.order.entity.Order;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;
import java.util.List;

@Getter
@Setter
public class OrderResponseDTO {
    private long orderId;
    private long memberId;
    private Order.OrderStatus orderStatus;
    private List<OrderCoffeeResponseDTO> orderCoffees;
    private LocalDateTime createdAt;
}
