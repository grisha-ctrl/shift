package by.shift.chatshift;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.socket.config.annotation.EnableWebSocket;

@SpringBootApplication
@EnableWebSocket
public class ChatShiftClientApplication {
    public static void main(String[] args) {
        SpringApplication.run(ChatShiftClientApplication.class, args);
    }
}
