package by.shift.chatshift.websocket;

import org.springframework.web.socket.TextMessage;
import org.springframework.web.socket.WebSocketSession;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;
import java.net.URI;
import java.util.Objects;

public class CustomSocket {

    private final Socket tcpSocket;
    private final PrintWriter tcpOut;
    private final BufferedReader tcpIn;
    private final WebSocketSession session;
    private final String userName;

    public CustomSocket(WebSocketSession session) throws IOException {
        tcpSocket = new Socket("127.0.0.1", 8082);
        tcpOut = new PrintWriter(tcpSocket.getOutputStream(), true);
        tcpIn = new BufferedReader(new InputStreamReader(tcpSocket.getInputStream()));
        listenToServer();
        this.session = session;
        this.userName = getUsernameFromUrl(Objects.requireNonNull(session.getUri()));
    }


    private void listenToServer() {
        new Thread(() -> {
            try {
                String message;
                while ((message = tcpIn.readLine()) != null) {
                    System.out.println("Сокет пользователя " + userName + " получил сообщение от TCP-сервера: " + message);
                    session.sendMessage(new TextMessage(message));
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }).start();
    }

    public static String getUsernameFromUrl(URI uri) {
        String query = uri.getQuery();

        if (query != null && !query.isEmpty()) {
            String[] params = query.split("&");

            for (String param : params) {
                if (param.startsWith("username=")) {
                    return param.split("=")[1];
                }
            }
        }

        throw new IllegalArgumentException("Параметр 'username' не найден в URL.");
    }

    public Socket getTcpSocket() {
        return tcpSocket;
    }

    public PrintWriter getTcpOut() {
        return tcpOut;
    }

    public BufferedReader getTcpIn() {
        return tcpIn;
    }

    public WebSocketSession getSession() {
        return session;
    }

    public String getUserName() {
        return userName;
    }
}
