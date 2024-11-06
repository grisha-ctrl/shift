package by.shift.task2.core.utils;

import by.shift.task2.core.model.Result;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class ResultProcessor {
    public static void process(String[] args, Result result){
        if (args.length == 2){
            OutputWriter.writeToFile(args[1], result);
        } else {
            System.out.println(result);
        }
    }
}
