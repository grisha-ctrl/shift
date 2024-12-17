package by.shift;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Collections;
import java.util.HashSet;
import java.util.Set;

public class Main {
    private static final int PORT = 8082;
    private static final Set<Socket> clients = Collections.synchronizedSet(new HashSet<>());

    public static void main(String[] args) {
        System.out.println("TCP-сервер запущен на порту " + PORT);

        try (ServerSocket serverSocket = new ServerSocket(PORT)) {
            while (true) {
                Socket clientSocket = serverSocket.accept();
                System.out.println("Новый клиент подключён: " + clientSocket);
                clients.add(clientSocket);

                new Thread(() -> handleClient(clientSocket)).start();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private static void handleClient(Socket clientSocket) {
        try (BufferedReader in = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()))) {
            String message;
            while ((message = in.readLine()) != null) {
                System.out.println("Получено сообщение: " + message);
                if (!"close".equals(message)) {
                    broadcastMessage(message, clientSocket);
                } else {
                    clientSocket.close();
                    clients.remove(clientSocket);
                }
            }
        } catch (IOException e) {
            System.out.println("Клиент отключён: " + clientSocket);
        } finally {
            clients.remove(clientSocket);
            try {
                clientSocket.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    private static void broadcastMessage(String message, Socket sender) {
        synchronized (clients) {
            for (Socket client : clients) {
                if (!client.equals(sender)) {
                    try {
                        PrintWriter out = new PrintWriter(client.getOutputStream(), true);
                        out.println(message);
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }
            }
        }
    }
}