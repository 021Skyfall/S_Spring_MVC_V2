package SampleWeek2.member.entity;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import org.springframework.data.annotation.Id;

@Getter
@Setter
@AllArgsConstructor
public class Member {
    @Id
    private long memberId;
    private String email;
    private String name;
    private String phone;
}
