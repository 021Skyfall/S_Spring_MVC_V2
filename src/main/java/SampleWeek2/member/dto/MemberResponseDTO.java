package SampleWeek2.member.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
public class MemberResponseDTO {
    private long memberId;
    private String email;
    private String name;
    private String phone;
}
