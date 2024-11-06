package by.shift.task2.core.model.impl;

import by.shift.task2.core.model.Calculator;
import by.shift.task2.core.model.FileData;
import by.shift.task2.core.model.Result;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.LinkedHashMap;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.assertThrows;

class CircleTest {

    @Test
    void calculateWithOneParameter() {
        FileData fileData = FileData.builder()
                .type("Circle")
                .parameters("3")
                .build();
        Map<String, String> map = new LinkedHashMap<>();
        map.put("Площадь: ", "28.274333882308138");
        map.put("Периметр: ", "18.84955592153876");
        map.put("Диаметр: ", "6.0");
        map.put("Радиус: ", "3.0");
        Result expected = new Result("Круг", map);
        Calculator circle = new Circle();
        Result actual = circle.calculate(fileData);
        Assertions.assertThat(actual).isEqualToComparingFieldByField(expected);
    }

    @Test
    void calculateWithMultipleParameters() {
        FileData fileData = FileData.builder()
                .type("Circle")
                .parameters("3 4 5 6")
                .build();

        Exception exception = assertThrows(RuntimeException.class, () -> {
            Calculator circle = new Circle();
            circle.calculate(fileData);
        });
        Assertions.assertThat("Circle should have only one parameter").isEqualTo(exception.getMessage());
    }
    @Test
    void calculateWithNullParameters() {
        FileData fileData = FileData.builder()
                .type("Circle")
                .parameters(null)
                .build();

        Exception exception = assertThrows(RuntimeException.class, () -> {
            Calculator circle = new Circle();
            circle.calculate(fileData);
        });
        Assertions.assertThat("Circle should have only one parameter").isEqualTo(exception.getMessage());
    }

    @Test
    void isSuitable() {
        FileData fileData = FileData.builder()
                .type("Circle")
                .parameters("3")
                .build();
        Calculator rectangle = new Rectangle();
        boolean actual = rectangle.isSuitable(fileData.getType());
        Assertions.assertThat(actual).isEqualTo(true);
    }

    @Test
    void isNotSuitable() {
        FileData fileData = FileData.builder()
                .type("Triangle")
                .parameters("3")
                .build();
        Calculator rectangle = new Rectangle();
        boolean actual = rectangle.isSuitable(fileData.getType());
        Assertions.assertThat(actual).isEqualTo(false);
    }
}