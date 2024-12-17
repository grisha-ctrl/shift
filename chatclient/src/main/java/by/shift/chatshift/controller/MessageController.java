package by.shift.chatshift.controller;

import by.shift.chatshift.model.Message;
import by.shift.chatshift.repository.MessageRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.List;


@RestController
@RequestMapping("/messages")
@RequiredArgsConstructor
public class MessageController {

    private MessageRepository messageRepository;

    public MessageController(MessageRepository messageRepository) {
        this.messageRepository = messageRepository;
    }

    @GetMapping
    public List<Message> getLastMessages(@RequestParam int offset, @RequestParam int amount) {
        return messageRepository.findLastMessages(offset, amount);
    }

    @PostMapping
    public Message addMessage(@RequestBody Message message) {
        message.setDate(LocalDateTime.now());
        return messageRepository.saveMessage(message);
    }
}
