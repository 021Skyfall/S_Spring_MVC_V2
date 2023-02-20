package SampleWeek2.coffee.dto;

import SampleWeek2.validator.NotSpace;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.validator.constraints.Range;

import javax.validation.constraints.Pattern;

@Getter
@Setter
@AllArgsConstructor
public class CoffeePatchDTO {
    private long coffeeId;
    @NotSpace(message = "커피명(한글)은 공백이 아니어야 합니다.")
    private String korName;
    @Pattern(regexp = "^([A-Za-z])(\\s?[A-Za-z])*$", message = "커피명(영문)은 영문이어야 합니다. 예) Cafe Latte")
    private String engName;
    @Range(max = 50000,min = 100)
    private Integer price;
}
