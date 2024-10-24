package by.shift.task2;
import by.shift.task2.core.model.Calculation;
import by.shift.task2.core.model.FileData;
import by.shift.task2.core.utils.DataReaderUtils;
import by.shift.task2.core.model.impl.Circle;
import by.shift.task2.core.model.impl.Rectangle;
import by.shift.task2.core.model.impl.Triangle;

import java.util.ArrayList;
import java.util.List;


public class Main {
    public static void main(String[] args) {
        FileData fileData = DataReaderUtils.read("file");
        List<Calculation> figures = new ArrayList<>();
        figures.add(new Circle());
        figures.add(new Rectangle());
        figures.add(new Triangle());

        String type = fileData.getType();
        for (Calculation figure: figures) {
            if (figure.isSuitable(type)){
                figure.calculate(fileData);
                break;
            }
        }

    }

}