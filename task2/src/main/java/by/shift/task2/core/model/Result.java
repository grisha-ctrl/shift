package by.shift.task2.core.model;

import java.util.Map;

public class Result {
    private final String name;
    private final Map<String, String> map;

    public Result(String name, Map<String,String> map){
        this.name = name;
        this.map = map;
    }

    @Override
    public String toString() {
        StringBuilder result = new StringBuilder();
        result.append("Figure: ").append(name).append(System.lineSeparator());

        map.forEach((key, value) -> result.append(System.lineSeparator()).append(key).append(": ").append(value).append(System.lineSeparator()));

        return result.toString();
    }
}
