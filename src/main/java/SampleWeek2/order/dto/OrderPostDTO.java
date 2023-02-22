package SampleWeek2.order.dto;

import lombok.Getter;

import javax.validation.Valid;
import javax.validation.constraints.Positive;
import java.util.List;

@Getter
public class OrderPostDTO {
    @Positive
    private long memberId;
    @Valid
    private List<OrderCoffeeDTO> orderCoffees;
}
