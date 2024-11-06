package by.shift.task2.core.utils;

import by.shift.task2.core.model.FileData;
import org.junit.jupiter.api.Test;
import org.assertj.core.api.Assertions;

class DataReaderUtilsTest {

    @Test
    void read() {
        FileData expected = FileData.builder()
                .type("Triangle")
                .parameters("3 4 5")
                .build();
        FileData actual = DataReaderUtils.read("src/test/java/by/shift/task2/core/TestFile");
        Assertions.assertThat(actual).isEqualToComparingFieldByField(expected);
    }
}