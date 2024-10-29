package by.shift.task2.core.model.impl;

import by.shift.task2.core.model.FileData;
import by.shift.task2.core.model.Result;
import by.shift.task2.core.model.annotation.Figure;
import by.shift.task2.core.model.Calculator;

import java.util.LinkedHashMap;
import java.util.Map;

@Figure
public class Circle implements Calculator {
// приватный метод который проверяет fileData на количество параметров в строке parameters
    @Override
    public Result calculate(FileData fileData) {
        double radius = Integer.parseInt(fileData.getParameters());
        double perimeter = 2 * Math.PI * radius;
        double square = Math.PI * radius * radius;
        double diameter = radius + radius;
        Map<String,String> map = new LinkedHashMap<>();
        map.put("Площадь: ",String.valueOf(square));
        map.put("Периметр: ", String.valueOf(perimeter));
        map.put("Диаметр: ",String.valueOf(diameter));
        map.put("Радиус: ",String.valueOf(radius));
        return new Result("Круг", map );
    }

    @Override
    public boolean isSuitable(String name) {
        return name.equals("Circle");
    }
}
