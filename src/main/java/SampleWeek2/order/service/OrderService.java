package SampleWeek2.order.service;

import SampleWeek2.coffee.service.CoffeeService;
import SampleWeek2.exception.BusinessLogicException;
import SampleWeek2.exception.ExceptionCode;
import SampleWeek2.member.service.MemberService;
import SampleWeek2.order.entity.Order;
import SampleWeek2.order.repository.OrderRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@AllArgsConstructor
public class OrderService {
    final private OrderRepository orderRepository;
    final private MemberService memberService;
    final private CoffeeService coffeeService;

    // 주문 등록
    public Order createOrder(Order order) {
        // 회원이 존재하는지 확인
        memberService.findVerifiedMember(order.getMemberId().getId());

        // 커피가 존재하는지 조회
        order.getOrderCoffees()
                .forEach(coffeeRef -> {
                    coffeeService.findVerifiedCoffee(coffeeRef.getCoffeeId());
                });
        return orderRepository.save(order);
    }

    // 주문 조회
    public Order findOrder(long orderId) {
        return findVerifiedOrder(orderId);
    }

    // 전체 주문 조회
    public List<Order> findOrders() {
        return (List<Order>) orderRepository.findAll();
    }

    // 주문 취소
    public void cancelOrder(long orderId) {
        Order findOrder = findVerifiedOrder(orderId);
        int step = findOrder.getOrderStatus().getStepNumber();

        // 주문 확정 이전에만 취소 가능하게
        if (step >= 2) {
            throw new BusinessLogicException(ExceptionCode.CANNOT_CHANGE_ORDER);
        }

        findOrder.setOrderStatus(Order.OrderStatus.ORDER_CANCEL);
        orderRepository.save(findOrder);
    }

    private Order findVerifiedOrder(long orderId) {
        Optional<Order> optionalOrder = orderRepository.findById(orderId);
        Order findOrder =
                optionalOrder.orElseThrow(() ->
                        new BusinessLogicException(ExceptionCode.ORDER_NOT_FOUND));
        return findOrder;
    }
}
