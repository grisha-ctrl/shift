package by.shift.task2.core.service.impl;

import by.shift.task2.core.model.FileData;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertThrows;

public class CircleValidationServiceTest {

    @Test
    void isValid(){
        FileData fileData = FileData.builder()
                .type("Circle")
                .parameters("3")
                .build();
        boolean actual = new CircleValidationService().isValidParameters(fileData);
        Assertions.assertThat(actual).isEqualTo(true);
    }

    @Test
    void isNoValid(){
        FileData fileData = FileData.builder()
                .type("Circle")
                .parameters("3 3")
                .build();
        FileData fileDataNull = FileData.builder()
                .type("Circle")
                .parameters("")
                .build();
        FileData fileDataMinus = FileData.builder()
                .type("Circle")
                .parameters("-2")
                .build();
        FileData fileDataWords = FileData.builder()
                .type("Circle")
                .parameters("asd")
                .build();
        FileData fileDataZero = FileData.builder()
                .type("Rectangle")
                .parameters("0")
                .build();
        assertThrows(RuntimeException.class, ()->{
            new CircleValidationService().isValidParameters(fileData);
            new CircleValidationService().isValidParameters(fileDataNull);
            new CircleValidationService().isValidParameters(fileDataMinus);
            new CircleValidationService().isValidParameters(fileDataWords);
            new TriangleValidationService().isValidParameters(fileDataZero);
        });
    }
}

