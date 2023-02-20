package SampleWeek2.hello_world.dto;

import lombok.Getter;

import javax.validation.constraints.NotBlank;

@Getter
public class MessagePostDTO {
    @NotBlank(message = "메시지는 비어 있을 수 없습니다.")
    private String message;
}
