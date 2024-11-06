package by.shift.task2.core.model;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Getter
@Builder
@Setter
public class FileData {
    private String type;
    private String parameters;
}
