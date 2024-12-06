package by.shift.task2.core.factory.impl;

import by.shift.task2.core.factory.FigureFactory;
import by.shift.task2.core.model.Figure;
import by.shift.task2.core.model.impl.Circle;
import by.shift.task2.core.model.FileData;
import by.shift.task2.core.service.impl.CircleValidationService;

public class CircleFactory implements FigureFactory {
    @Override
    public Figure createFigure(FileData fileData) {
        boolean valid = new CircleValidationService().isValidParameters(fileData);
        if (valid){
            return new Circle();
        }
        return null;
    }

}
