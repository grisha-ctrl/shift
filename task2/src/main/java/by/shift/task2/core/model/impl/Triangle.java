package by.shift.task2.core.model.impl;

import by.shift.task2.core.model.FileData;
import by.shift.task2.core.model.Result;
import by.shift.task2.core.model.annotation.Figure;
import by.shift.task2.core.model.Calculator;
import java.util.LinkedHashMap;
import java.util.Map;

@Figure
public class Triangle implements Calculator {
    @Override
    public Result calculate(FileData fileData) {
        String[] numberStrings = fileData.getParameters().split(" ");

        int sideA = Integer.parseInt(numberStrings[0]);
        int sideB = Integer.parseInt(numberStrings[1]);
        int sideC = Integer.parseInt(numberStrings[2]);

        double perimeter = sideA + sideB + sideC;
        double halfPerimeter = perimeter / 2;
        double sqrt = halfPerimeter * ((halfPerimeter - sideA) * (halfPerimeter - sideB) * (halfPerimeter - sideC));
        double square = Math.sqrt(sqrt);

        double cornerA = Math.acos((double) ((sideB*sideB)+(sideC*sideC)-(sideA*sideA))/(2*sideB*sideC));
        double cornerB = Math.acos((double) ((sideA*sideA)+(sideC*sideC)-(sideB*sideB))/(2*sideA*sideC));
        double cornerC = Math.acos((double) (sideB*sideB+sideA*sideA-sideC*sideC)/2*sideB*sideA);

        Map<String,String> map = new LinkedHashMap<>();
        map.put("Площадь",String.valueOf(square));
        map.put("Периметр", String.valueOf(perimeter));
        map.put("Сторона А и противолежащий угол", sideA + " " + cornerA);
        map.put("Сторона B и противолежащий угол", sideB + " " + cornerB);
        map.put("Сторона C и противолежащий угол", sideC + " " + cornerC);
        return new Result("Треугольник", map );
    }

    @Override
    public boolean isSuitable(String name) {
        return name.equals("Triangle");
    }
}