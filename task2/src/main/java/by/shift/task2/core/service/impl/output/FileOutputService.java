package by.shift.task2.core.service.impl.output;

import by.shift.task2.core.service.OutputService;
import lombok.extern.slf4j.Slf4j;
import java.io.FileWriter;

@Slf4j
public class FileOutputService implements OutputService {
    private final String fileName;

    public FileOutputService(String fileName) {
        this.fileName = fileName;
    }

    @Override
    public void write(String figureDescription) {
        try (FileWriter writer = new FileWriter(fileName)){
            writer.write(String.valueOf(figureDescription));
        } catch (Exception e) {
            log.error("Can't write result in file");
            throw new RuntimeException(e.getMessage(), e.getCause());
        }
    }
}
