package SampleWeek2.hello_world.service;

import SampleWeek2.hello_world.entity.Message;
import SampleWeek2.hello_world.repository.MessageRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
public class MessageService {
    private final MessageRepository messageRepository;

    public Message createMessage(Message message) {
        return messageRepository.save(message);
    }

    public Message updateMessage(Message message) {
        return messageRepository.save(message);
    }

    public Message findMessage(long messageId) {
        return messageRepository.findById(messageId).get();
    }

    public List<Message> findMessages() {
        return (List<Message>) messageRepository.findAll();
    }

    public void deleteMessage(long messageId) {
        messageRepository.deleteById(messageId);
    }
}
