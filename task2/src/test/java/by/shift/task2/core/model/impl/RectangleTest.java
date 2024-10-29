package by.shift.task2.core.model.impl;

import by.shift.task2.core.model.Calculator;
import by.shift.task2.core.model.FileData;
import by.shift.task2.core.model.Result;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import java.util.LinkedHashMap;
import java.util.Map;

class RectangleTest {

    @Test
    void calculate() {
        FileData fileData = FileData.builder()
                .type("Rectangle")
                .parameters("3 4")
                .build();
        Map<String, String> map = new LinkedHashMap<>();
        map.put("Площадь", "12.0");
        map.put("Периметр", "14.0");
        map.put("Сторона A", "3");
        map.put("Сторона B", "4");
        map.put("Диагональ", "5.0");
        Result expected = new Result("Прямоугольник", map);
        Calculator rectangle = new Rectangle();
        Result actual = rectangle.calculate(fileData);
        Assertions.assertThat(actual).isEqualToComparingFieldByField(expected);
    }

    @Test
    void isSuitable() {
        FileData fileData = FileData.builder()
                .type("Rectangle")
                .parameters("3 4")
                .build();
        Calculator rectangle = new Rectangle();
        boolean actual = rectangle.isSuitable(fileData.getType());
        Assertions.assertThat(actual).isEqualTo(true);
    }

    @Test
    void isNotSuitable(){
        FileData fileData = FileData.builder()
                .type("Circle")
                .parameters("3 4")
                .build();
        Calculator rectangle = new Rectangle();
        boolean actual = rectangle.isSuitable(fileData.getType());
        Assertions.assertThat(actual).isEqualTo(false);
    }
}