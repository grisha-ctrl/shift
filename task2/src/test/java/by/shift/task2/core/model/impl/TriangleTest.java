package by.shift.task2.core.model.impl;

import by.shift.task2.core.model.Calculator;
import by.shift.task2.core.model.FileData;
import by.shift.task2.core.model.Result;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.LinkedHashMap;
import java.util.Map;


class TriangleTest {

    @Test
    void calculate() {
        FileData fileData = FileData.builder()
                .type("Triangle")
                .parameters("3 4 5")
                .build();
        Map<String, String> map = new LinkedHashMap<>();
        map.put("Площадь", "6.0");
        map.put("Периметр", "12.0");
        map.put("Сторона А и противолежащий угол", "3 0.6435011087932843");
        map.put("Сторона B и противолежащий угол", "4 0.9272952180016123");
        map.put("Сторона C и противолежащий угол", "5 1.5707963267948966");
        Result expected = new Result("Треугольник", map);
        Calculator triangle = new Triangle();
        Result actual = triangle.calculate(fileData);
        Assertions.assertThat(actual).isEqualToComparingFieldByField(expected);
    }
    //
    @Test
    void isSuitable() {
    }
}