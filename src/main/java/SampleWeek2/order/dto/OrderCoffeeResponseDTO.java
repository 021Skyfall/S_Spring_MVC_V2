package SampleWeek2.order.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class OrderCoffeeResponseDTO {
    private long coffeeId;
    private String korName;
    private String engName;
    private int price;
    private int quantity;
}
