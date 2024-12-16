package by.shift.task2.core.service.impl.output;

import by.shift.task2.core.model.Figure;
import by.shift.task2.core.model.impl.Triangle;
import by.shift.task2.core.service.FigureOutputService;

public class TriangleOutputService implements FigureOutputService {
    @Override
    public String output(Figure figure) {
        if (figure instanceof Triangle triangle) {
            return String.format("Circle:\nRadius: %.2f\nSquare: %.2f\nDiagonal: %.2f\nSide A: %.2f\nSide B: %.2f\nCorner C: %.2f\nCorner C: %.2f\nCorner C: %.2f",
                    triangle.getPerimeter(), triangle.getSquare(), triangle.getSideA(), triangle.getSideB(), triangle.getSideC(),
                    triangle.getCornerA(), triangle.getCornerB(), triangle.getCornerC());
        } else {
            throw new IllegalArgumentException("Invalid figure type for TriangleOutputService");
        }
    }
}
