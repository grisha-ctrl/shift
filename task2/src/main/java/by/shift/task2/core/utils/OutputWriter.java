package by.shift.task2.core.utils;

import by.shift.task2.core.model.Result;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import java.io.FileWriter;

@Slf4j
@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class OutputWriter {
    public static void writeToFile(String args, Result result) {
        try (FileWriter writer = new FileWriter(args)){
            writer.write(String.valueOf(result));
        } catch (Exception e) {
            log.error("Can't write result in file");
            throw new RuntimeException(e.getMessage(), e.getCause());
        }
    }
}
