package by.shift.task2.core.utils;

import by.shift.task2.core.model.FileData;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class DataReaderUtils {

    public static FileData read(String filePath) {
        FileData fileData = FileData.builder().build();
        try (BufferedReader reader = new BufferedReader(new FileReader(filePath))) {
            String type = reader.readLine();
            String parameters = reader.readLine();
            fileData = FileData.builder()
                    .type(type)
                    .parameters(parameters)
                    .build();
        } catch (IOException e) {
            System.out.println("zxc");
        }
        return fileData;
    }
}
