package SampleWeek2.hello_world.mapper;

import SampleWeek2.hello_world.dto.MessagePatchDTO;
import SampleWeek2.hello_world.dto.MessagePostDTO;
import SampleWeek2.hello_world.dto.MessageResponseDTO;
import SampleWeek2.hello_world.entity.Message;
import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;

import java.util.List;

@Mapper(componentModel = "spring", unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface MessageMapper {
    Message messagePostDtoToMessage(MessagePostDTO messagePostDTO);
    Message messagePatchDtoToMessage(MessagePatchDTO messagePatchDTO);
    MessageResponseDTO messageToMessageResponseDTO(Message message);
    List<MessageResponseDTO> messagesToMessageResponseDto(List<Message> messages);
}
