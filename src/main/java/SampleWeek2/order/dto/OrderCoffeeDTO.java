package SampleWeek2.order.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;

import javax.validation.constraints.Positive;

@Getter
@AllArgsConstructor
public class OrderCoffeeDTO {
    @Positive
    private long coffeeId;
    @Positive
    private int quantity;
}
