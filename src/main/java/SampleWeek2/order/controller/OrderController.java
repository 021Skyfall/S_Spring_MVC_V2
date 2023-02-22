package SampleWeek2.order.controller;

import SampleWeek2.coffee.service.CoffeeService;
import SampleWeek2.order.dto.OrderPostDTO;
import SampleWeek2.order.dto.OrderResponseDTO;
import SampleWeek2.order.entity.Order;
import SampleWeek2.order.mapper.OrderMapper;
import SampleWeek2.order.service.OrderService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

import javax.validation.constraints.Positive;
import java.net.URI;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/v1/orders")
@AllArgsConstructor
@Validated
public class OrderController {
    private final static String ORDER_DEFAULT_URL = "/v1/orders";
    private final OrderService orderService;
    private final OrderMapper mapper;
    private final CoffeeService coffeeService;

    @PostMapping
    public ResponseEntity postOrder(@Validated @RequestBody OrderPostDTO orderPostDTO) {
        Order order = orderService.createOrder(mapper.orderPostDtoToOrder(orderPostDTO));

        URI location =
                UriComponentsBuilder
                        .newInstance()
                        .path(ORDER_DEFAULT_URL + "/{order-id}")
                        .buildAndExpand(order.getOrderId())
                        .toUri();
        return ResponseEntity.created(location).build();
    }

    @GetMapping("/{order-id}")
    public ResponseEntity getOrder(@PathVariable("order-id") @Positive long orderId) {
        Order order = orderService.findOrder(orderId);

        return new ResponseEntity<>(mapper.orderToOrderResponseDto(coffeeService,order), HttpStatus.OK);
    }

    @GetMapping
    public ResponseEntity getOrders() {
        List<Order> orders = orderService.findOrders();

        List<OrderResponseDTO> response = orders.stream()
                .map(order -> mapper.orderToOrderResponseDto(coffeeService,order))
                .collect(Collectors.toList());

        return new ResponseEntity<>(response,HttpStatus.OK);
    }

    @DeleteMapping("/{order-id}")
    public ResponseEntity cancelOrder(@PathVariable("order-id") @Positive long orderId) {
        orderService.cancelOrder(orderId);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}
