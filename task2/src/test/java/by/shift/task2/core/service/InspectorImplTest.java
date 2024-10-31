package by.shift.task2.core.service;

import by.shift.task2.core.model.Calculator;
import by.shift.task2.core.model.Context;
import by.shift.task2.core.model.FileData;
import by.shift.task2.core.model.Result;
import by.shift.task2.core.testModel.TestClassFirst;
import by.shift.task2.core.testModel.TestClassSecond;
import by.shift.task2.core.utils.DataReaderUtils;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.mockito.MockedStatic;
import org.mockito.Mockito;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

class InspectorImplTest {

    @Test
    void inspect() {
        String path = "src/test/resources/TestFile";

        Map<String, String> map = new LinkedHashMap<>();
        map.put("Площадь", "6.0");
        map.put("Периметр", "12.0");
        map.put("Сторона А и противолежащий угол", "3 0.6435011087932843");
        map.put("Сторона B и противолежащий угол", "4 0.9272952180016123");
        map.put("Сторона C и противолежащий угол", "5 1.5707963267948966");

        try {
            Calculator testClassFirst = new TestClassFirst();
            Calculator testClassSecond = new TestClassSecond();
            Calculator testClassThird = new TestClassFirst();

            List<Calculator> figures = List.of(testClassFirst, testClassSecond, testClassThird);

            FileData fileData = FileData.builder()
                    .type("Triangle")
                    .parameters("3 4 5")
                    .build();
            try (MockedStatic<DataReaderUtils> readerUtilsMockedStatic = Mockito.mockStatic(DataReaderUtils.class);
                 MockedStatic<Context> contextMockedStatic = Mockito.mockStatic(Context.class)) {
                readerUtilsMockedStatic.when(() -> DataReaderUtils.read(path))
                        .thenReturn(fileData);
                contextMockedStatic.when(Context::getFigures)
                        .thenReturn(figures);
            }
            Inspector inspector = new InspectorImpl();
            Result actual = inspector.inspect(path);
            Result expected = new Result("Triangle", map);
            Assertions.assertThat(actual).isEqualToComparingFieldByField(expected);

        } catch (Exception e) {
            System.out.println(e.getMessage());
        }


    }
}