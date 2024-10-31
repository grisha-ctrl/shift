package by.shift.task2.core.service;

import by.shift.task2.core.model.Calculator;
import by.shift.task2.core.model.Context;
import by.shift.task2.core.model.FileData;
import by.shift.task2.core.model.Result;
import by.shift.task2.core.utils.DataReaderUtils;
import java.util.List;

public class InspectorImpl implements Inspector {

    @Override
    public Result inspect(String path) {
        FileData fileData = DataReaderUtils.read(path);
        List<Calculator> figures = Context.getFigures();
        for (Calculator figure : figures) {
            if (figure.isSuitable(fileData.getType())) {
                 return figure.calculate(fileData);
            }
        }
        return null;
    }
}
