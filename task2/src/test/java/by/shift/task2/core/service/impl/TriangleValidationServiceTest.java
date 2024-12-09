package by.shift.task2.core.service.impl;

import by.shift.task2.core.model.FileData;
import by.shift.task2.core.service.impl.validation.RectangleValidationService;
import by.shift.task2.core.service.impl.validation.TriangleValidationService;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertThrows;

public class TriangleValidationServiceTest {

    @Test
    void isValid(){
        FileData fileData = FileData.builder()
                .type("Rectangle")
                .parameters("3 3")
                .build();
        boolean actual = new RectangleValidationService().isValidParameters(fileData);
        Assertions.assertThat(actual).isEqualTo(true);
    }

    @Test
    void isNoValid(){
        FileData fileData = FileData.builder()
                .type("Rectangle")
                .parameters("3")
                .build();
        FileData fileDataMoreThanNecessary = FileData.builder()
                .type("Rectangle")
                .parameters("4 5 2 2")
                .build();
        FileData fileDataNull = FileData.builder()
                .type("Rectangle")
                .parameters("")
                .build();
        FileData fileDataMinus = FileData.builder()
                .type("Rectangle")
                .parameters("-2 -6")
                .build();
        FileData fileDataWords = FileData.builder()
                .type("Rectangle")
                .parameters("asd asd")
                .build();
        FileData fileDataZero = FileData.builder()
                .type("Rectangle")
                .parameters("0 0 0")
                .build();
        assertThrows(RuntimeException.class, ()->{
            new TriangleValidationService().isValidParameters(fileData);
            new TriangleValidationService().isValidParameters(fileDataMoreThanNecessary);
            new TriangleValidationService().isValidParameters(fileDataNull);
            new TriangleValidationService().isValidParameters(fileDataMinus);
            new TriangleValidationService().isValidParameters(fileDataWords);
            new TriangleValidationService().isValidParameters(fileDataZero);
        });
    }
}
