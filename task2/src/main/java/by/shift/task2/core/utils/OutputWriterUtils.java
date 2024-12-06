package by.shift.task2.core.utils;

import by.shift.task2.core.model.Figure;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import java.io.FileWriter;

@Slf4j
@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class OutputWriterUtils {
    public static void writeToFile(String args, Figure result) {
        try (FileWriter writer = new FileWriter(args)){
            writer.write(String.valueOf(result));
        } catch (Exception e) {
            log.error("Can't write result in file");
            throw new RuntimeException(e.getMessage(), e.getCause());
        }
    }
}
