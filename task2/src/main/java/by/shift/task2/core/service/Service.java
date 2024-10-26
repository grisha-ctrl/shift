package by.shift.task2.core.service;

import by.shift.task2.core.model.Calculator;
import by.shift.task2.core.model.Context;
import by.shift.task2.core.model.FileData;
import by.shift.task2.core.utils.DataReaderUtils;

import java.util.List;


//тут методы для main
public class Service {
   public void service(){
       FileData fileData = DataReaderUtils.read("file");
       List<Calculator> figures = Context.getFigures();
       System.out.println(figures);
       for (Calculator figure : figures) {
           if (figure.isSuitable(fileData.getType())) {
               figure.calculate(fileData);
               break;
           }
       }
   }


}
