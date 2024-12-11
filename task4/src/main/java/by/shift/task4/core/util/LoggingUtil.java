package by.shift.task4.core.util;


import com.sun.tools.javac.Main;

import java.io.IOException;
import java.util.logging.*;

public class LoggingUtil {
    private static final Logger LOGGER = Logger.getLogger(Main.class.getName());
    public static void setupLogging() {
    try {
        // Логгер для консоли
        ConsoleHandler consoleHandler = new ConsoleHandler();
        consoleHandler.setLevel(Level.INFO);
        consoleHandler.setFormatter(new SimpleFormatter());

        // Логгер для файла
        FileHandler fileHandler = new FileHandler("series_calculator.log", true);
        fileHandler.setLevel(Level.INFO);
        fileHandler.setFormatter(new SimpleFormatter());

        // Настройка логгера
        LOGGER.addHandler(consoleHandler);
        LOGGER.addHandler(fileHandler);
        LOGGER.setLevel(Level.INFO);
        LOGGER.setUseParentHandlers(false);
    } catch (IOException e) {
        System.err.println("Ошибка настройки логирования: " + e.getMessage());
    }
}
}
