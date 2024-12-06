package by.shift.task2.core.model.impl;

import by.shift.task2.core.model.Figure;
import by.shift.task2.core.model.FileData;
import lombok.Getter;

@Getter
public class Circle implements Figure {

    String name = "Circle";

    public double getRadius() {
        return radius;
    }

    double radius;
    double perimeter;
    double square;
    double diameter;

    @Override
    public void calculate(FileData fileData) {
        radius = Integer.parseInt(fileData.getParameters());
        perimeter = 2 * Math.PI * radius;
        square = Math.PI * radius * radius;
        diameter = radius + radius;
    }

    @Override
    public String toString() {
        return "Figure: " + name +
                System.lineSeparator() +
                "Square " + square + " sq. mm" + System.lineSeparator() +
                "Perimeter " + square + " sq. mm" + System.lineSeparator() +
                "Diameter " + diameter + " mm" + System.lineSeparator() +
                "Radius " + radius + " mm";
    }
}
