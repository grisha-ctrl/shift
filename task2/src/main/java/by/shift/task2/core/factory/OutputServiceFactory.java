package by.shift.task2.core.factory;

import by.shift.task2.core.service.impl.output.ConsoleOutputService;
import by.shift.task2.core.service.impl.output.FileOutputService;
import by.shift.task2.core.service.OutputService;

public class OutputServiceFactory {
    public static OutputService create(String type, String fileName) {
        return switch (type.toLowerCase()) {
            case "file" -> new FileOutputService(fileName);
            case "console" -> new ConsoleOutputService();
            default -> throw new IllegalArgumentException("Unsupported output type: " + type);
        };
    }
}
