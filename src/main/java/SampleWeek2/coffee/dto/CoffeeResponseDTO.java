package SampleWeek2.coffee.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
public class CoffeeResponseDTO {
    private long coffeeId;
    private String korName;
    private String engName;
    private int price;
}
