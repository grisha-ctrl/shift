package by.shift.task2.core.model;

import java.util.Map;

public class Result {
    private String name;
    private Map<String, String> map;

    public Result(String name, Map<String,String> map){
        this.name = name;
        this.map = map;
    }

    @Override
    public String toString() {
        StringBuilder result = new StringBuilder();
        result.append("Figure: ").append(name).append("\n");

        map.forEach((key, value) -> {
            result.append(key).append(": ").append(value).append("\n");
        });

        return result.toString();
    }
}
