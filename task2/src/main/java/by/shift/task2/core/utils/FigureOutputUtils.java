package by.shift.task2.core.utils;

import by.shift.task2.core.model.Figure;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class FigureOutputUtils {
    public static void process(String[] args, Figure figure){
        if (args.length == 2){
            OutputWriterUtils.writeToFile(args[1], figure);
        } else {
            System.out.println(figure);
        }
    }
}
