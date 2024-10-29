package by.shift.task2.core.model.impl;

import by.shift.task2.core.model.FileData;
import by.shift.task2.core.model.Result;
import by.shift.task2.core.model.annotation.Figure;
import by.shift.task2.core.model.Calculator;

import java.util.LinkedHashMap;
import java.util.Map;

@Figure
public class Rectangle implements Calculator {

    @Override
    public Result calculate(FileData fileData) {
        String[] numberStrings = fileData.getParameters().split(" ");

        int sideA = Integer.parseInt(numberStrings[0]);
        int sideB = Integer.parseInt(numberStrings[1]);

        double perimeter = (sideA * 2) + (sideB * 2);
        double square = sideA * sideB;
        double diagonal = Math.sqrt(sideA*sideA+sideB*sideB);

        Map<String,String> map = new LinkedHashMap<>();
        map.put("Площадь",String.valueOf(square));
        map.put("Периметр", String.valueOf(perimeter));
        map.put("Сторона A", String.valueOf(sideA));
        map.put("Сторона B", String.valueOf(sideB));
        map.put("Диагональ", String.valueOf(diagonal));
        return new Result("Прямоугольник", map );
    }

    @Override
    public boolean isSuitable(String name) {
        return name.equals("Rectangle");
    }
}
