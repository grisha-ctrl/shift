package by.shift.task2.core.service.impl.output;

import by.shift.task2.core.model.Figure;
import by.shift.task2.core.model.impl.Circle;
import by.shift.task2.core.service.FigureOutputService;

public class CircleOutputService implements FigureOutputService {
    @Override
    public String output(Figure figure) {
        if (figure instanceof Circle circle) {
            return String.format("Circle:\nRadius: %.2f\nSquare: %.2f\nDiameter: %.2f\\n",
                    circle.getRadius(),circle.getSquare(),circle.getDiameter());
        } else {
            throw new IllegalArgumentException("Invalid figure type for CircleOutputService");
        }
    }
}
