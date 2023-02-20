package SampleWeek2.hello_world.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class MessageResponseDTO {
    private long messageId;
    private String message;
}
