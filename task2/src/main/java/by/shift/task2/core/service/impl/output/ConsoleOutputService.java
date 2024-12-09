package by.shift.task2.core.service.impl.output;

import by.shift.task2.core.service.OutputService;

public class ConsoleOutputService implements OutputService {

    @Override
    public void write(String figureDescription) {
        System.out.println(figureDescription);
    }
}
