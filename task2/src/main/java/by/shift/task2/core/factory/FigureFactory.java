package by.shift.task2.core.factory;

import by.shift.task2.core.model.Figure;
import by.shift.task2.core.model.FileData;

public interface FigureFactory {
    Figure createFigure(FileData fileData);
}
