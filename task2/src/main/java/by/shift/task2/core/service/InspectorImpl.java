package by.shift.task2.core.service;

import by.shift.task2.core.model.Calculator;
import by.shift.task2.core.model.Context;
import by.shift.task2.core.model.FileData;
import by.shift.task2.core.model.Result;
import by.shift.task2.core.utils.DataReaderUtils;

import java.util.List;
//вернуть резалт
public class InspectorImpl implements Inspector {
    //почитать про mockito как им пользоваться и зачем он нужен
    //посмотреть как замокать статик методы
    @Override
    public void inspect(String path) {
        FileData fileData = DataReaderUtils.read(path);
        List<Calculator> figures = Context.getFigures();
        for (Calculator figure : figures) {
            if (figure.isSuitable(fileData.getType())) {
                Result result = figure.calculate(fileData);
                System.out.println(result);
                break;
            }
        }
    }
}
