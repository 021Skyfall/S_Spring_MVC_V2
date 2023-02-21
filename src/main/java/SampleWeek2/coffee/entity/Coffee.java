package SampleWeek2.coffee.entity;

import lombok.Getter;
import lombok.Setter;
import org.springframework.data.annotation.Id;

@Getter
@Setter
public class Coffee {
    @Id // (1) 식별자 지정
    private long coffeeId;
    private String KorName;
    private String engName;
    private Integer price;
    private String coffeeCode; // (2) 컬럼 추가
}
