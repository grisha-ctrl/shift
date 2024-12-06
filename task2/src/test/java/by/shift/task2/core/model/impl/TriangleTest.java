package by.shift.task2.core.model.impl;

import by.shift.task2.core.model.FileData;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;

class TriangleTest {
    @Test
    void calculate() {
        FileData fileData = FileData.builder()
                .type("Triangle")
                .parameters("3 4 5")
                .build();

        double square = 6;
        double perimeter = 12;
        double sideA = 3;
        double sideB = 4;
        double sideC = 5;
        double cornerA = 0.6435011087932843;
        double cornerB = 0.9272952180016123;
        double cornerC = 1.5707963267948966;

        Triangle triangle = new Triangle();
        triangle.calculate(fileData);

        double actualSideA = triangle.getSideA();
        double actualSideB = triangle.getSideB();
        double actualSideC = triangle.getSideC();
        double actualPerimeter = triangle.getPerimeter();
        double actualSquare = triangle.getSquare();
        double actualCornerA = triangle.getCornerA();
        double actualCornerB = triangle.getCornerB();
        double actualCornerC = triangle.getCornerC();

        Assertions.assertThat(actualSideA).isEqualTo(sideA);
        Assertions.assertThat(actualSideB).isEqualTo(sideB);
        Assertions.assertThat(actualSideC).isEqualTo(sideC);
        Assertions.assertThat(actualPerimeter).isEqualTo(perimeter);
        Assertions.assertThat(actualSquare).isEqualTo(square);
        Assertions.assertThat(actualCornerA).isEqualTo(cornerA);
        Assertions.assertThat(actualCornerB).isEqualTo(cornerB);
        Assertions.assertThat(actualCornerC).isEqualTo(cornerC);
    }
}