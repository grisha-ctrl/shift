package by.shift.task2.core.service.impl.output;

import by.shift.task2.core.model.Figure;
import by.shift.task2.core.model.impl.Rectangle;
import by.shift.task2.core.service.FigureOutputService;

public class RectangleOutputService implements FigureOutputService {
    @Override
    public String output(Figure figure) {
        if (figure instanceof Rectangle rectangle) {
            return String.format("Circle:\nRadius: %.2f\nSquare: %.2f\nDiagonal: %.2f\nSide A: %.2f\nSide B: %.2f\n",
                    rectangle.getPerimeter(),rectangle.getSquare(),rectangle.getDiagonal(),rectangle.getSideA(),rectangle.getSideB());
        } else {
            throw new IllegalArgumentException("Invalid figure type for RectangleOutputService");
        }
    }
}
