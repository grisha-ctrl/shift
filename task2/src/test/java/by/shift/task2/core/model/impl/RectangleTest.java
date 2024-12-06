package by.shift.task2.core.model.impl;

import by.shift.task2.core.model.FileData;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;

class RectangleTest {
    @Test
    void calculate() {
        FileData fileData = FileData.builder()
                .type("Rectangle")
                .parameters("3 4")
                .build();

        double square = 12;
        double perimeter = 14;
        double diagonal = 5;
        double sideA = 3;
        double sideB = 4;

        Rectangle rectangle = new Rectangle();
        rectangle.calculate(fileData);

        double actualSideA = rectangle.getSideA();
        double actualSideB = rectangle.getSideB();
        double actualPerimeter = rectangle.getPerimeter();
        double actualSquare = rectangle.getSquare();
        double actualDiagonal = rectangle.getDiagonal();

        Assertions.assertThat(actualSideA).isEqualTo(sideA);
        Assertions.assertThat(actualSideB).isEqualTo(sideB);
        Assertions.assertThat(actualPerimeter).isEqualTo(perimeter);
        Assertions.assertThat(actualSquare).isEqualTo(square);
        Assertions.assertThat(actualDiagonal).isEqualTo(diagonal);
    }
}