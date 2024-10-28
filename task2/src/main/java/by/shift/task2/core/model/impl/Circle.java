package by.shift.task2.core.model.impl;

import by.shift.task2.core.model.FileData;
import by.shift.task2.core.model.Result;
import by.shift.task2.core.model.annotation.Figure;
import by.shift.task2.core.model.Calculator;

@Figure
public class Circle implements Calculator {

    @Override
    public Result calculate(FileData fileData) {
        double radius = Integer.parseInt(fileData.getParameters());
        double perimeter = 2 * Math.PI * radius;
        double square = Math.PI * radius * radius;
        double diameter = radius + radius;
        StringBuilder builder = new StringBuilder();
        builder.append("Тип фигуры: Круг").append(System.getProperty("line.separator"));
        builder.append("Площадь: ").append(square).append(System.getProperty("line.separator"));
        builder.append("Периметр: ").append(perimeter).append(System.getProperty("line.separator"));
        builder.append("Радиус: ").append(radius).append(System.getProperty("line.separator"));
        builder.append("Диаметр: ").append(diameter).append(System.getProperty("line.separator"));
        System.out.println(builder);
        return null;
    }

    @Override
    public boolean isSuitable(String name) {
        return name.equals("Circle");
    }
}
