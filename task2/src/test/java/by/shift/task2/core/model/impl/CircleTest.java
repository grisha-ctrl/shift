package by.shift.task2.core.model.impl;

import by.shift.task2.core.model.FileData;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;

class CircleTest {
    @Test
    void calculateWithOneParameter() {
        FileData fileData = FileData.builder()
                .type("Circle")
                .parameters("3")
                .build();

        double radius = 3;
        double perimeter = 18.84955592153876;
        double square = 28.274333882308138;
        double diameter = 6;

        Circle circle = new Circle();
        circle.calculate(fileData);

        double actualRadius = circle.getRadius();
        double actualPerimeter = circle.getPerimeter();
        double actualSquare = circle.getSquare();
        double actualDiameter = circle.getDiameter();

        Assertions.assertThat(actualRadius).isEqualTo(radius);
        Assertions.assertThat(actualPerimeter).isEqualTo(perimeter);
        Assertions.assertThat(actualSquare).isEqualTo(square);
        Assertions.assertThat(actualDiameter).isEqualTo(diameter);
    }
}