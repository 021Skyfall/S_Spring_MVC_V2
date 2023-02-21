package SampleWeek2.hello_world.controller;

import SampleWeek2.hello_world.dto.MessagePatchDTO;
import SampleWeek2.hello_world.dto.MessagePostDTO;
import SampleWeek2.hello_world.dto.MessageResponseDTO;
import SampleWeek2.hello_world.entity.Message;
import SampleWeek2.hello_world.mapper.MessageMapper;
import SampleWeek2.hello_world.service.MessageService;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.validation.constraints.Positive;
import java.util.List;

@RestController
@RequestMapping("/v1/messages")
@AllArgsConstructor
@Slf4j
public class MessageController {
    // 메세지 서비스 DI
    private MessageService service;
    // 메세지 매퍼 DI
    private MessageMapper mapper;

    @PostMapping
    public ResponseEntity postMessage(@Validated @RequestBody MessagePostDTO messagePostDTO) {
        // 서비스 로직 연동
        Message message = service.createMessage(mapper.messagePostDtoToMessage(messagePostDTO));
        // 응답
        return ResponseEntity.ok(mapper.messageToMessageResponseDTO(message));
    }

    @PatchMapping("/{message-id}")
    public ResponseEntity patchMessage(@PathVariable("message-id") @Positive long messageId,
                                       @Validated @RequestBody MessagePatchDTO messagePatchDTO) {
        messagePatchDTO.setMessageId(messageId);
        Message message = service.updateMessage(mapper.messagePatchDtoToMessage(messagePatchDTO));
        return ResponseEntity.ok(mapper.messageToMessageResponseDTO(message));
    }

    @GetMapping("/{message-id}")
    public ResponseEntity getMessage(@PathVariable("message-id") @Positive long messageId) {
        Message response = service.findMessage(messageId);
        return ResponseEntity.ok(mapper.messageToMessageResponseDTO(response));
    }

    @GetMapping
    public ResponseEntity getMessages() {
        List<Message> messages = service.findMessages();
        List<MessageResponseDTO> response = mapper.messagesToMessageResponseDto(messages);
        return ResponseEntity.ok(response);
    }

    @DeleteMapping("/{member-id}")
    public ResponseEntity deleteMessage(@PathVariable("member-id") @Positive long messageId) {
        service.deleteMessage(messageId);
        return new ResponseEntity<>(HttpStatus.OK);
    }
}
