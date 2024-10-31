package by.shift.task2.core.service;

import by.shift.task2.core.model.Calculator;
import by.shift.task2.core.model.Context;
import by.shift.task2.core.model.FileData;
import by.shift.task2.core.model.Result;
import by.shift.task2.core.model.impl.Circle;
import by.shift.task2.core.model.impl.Rectangle;
import by.shift.task2.core.model.impl.Triangle;
import by.shift.task2.core.testModel.TestClassFirst;
import by.shift.task2.core.testModel.TestClassSecond;
import by.shift.task2.core.utils.DataReaderUtils;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.mockito.MockedStatic;
import org.mockito.Mockito;

import java.io.File;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

class InspectorImplTest {

    @Test
    void inspect() {
        try (MockedStatic<DataReaderUtils> readerUtilsMockedStatic = Mockito.mockStatic(DataReaderUtils.class);
             MockedStatic<Context> contextMockedStatic = Mockito.mockStatic(Context.class)) {
            contextMockedStatic.when(Context::getFigures).thenReturn(List.of(new Circle(),new Rectangle(), new Triangle()));
            String path = "src/test/resources/TestFile";

            Map<String, String> map = new LinkedHashMap<>();
            map.put("Square", "6.0 sq. mm");
            map.put("Perimeter", "12.0 mm");
            map.put("Side A", "3 mm and the opposite angle: 36.86989764584401 degrees");
            map.put("Side B", "4 mm and the opposite angle: 53.13010235415599 degrees");
            map.put("Side C", "5 mm and the opposite angle: 90.0 degrees");


            FileData fileData = FileData.builder()
                    .type("Triangle")
                    .parameters("3 4 5")
                    .build();
            readerUtilsMockedStatic.when(() -> DataReaderUtils.read(path))
                    .thenReturn(fileData);
            Inspector inspector = new InspectorImpl();
            Result actual = inspector.inspect(path);
            Result expected = new Result("Triangle", map);
            Assertions.assertThat(actual).isEqualToComparingFieldByField(expected);
        }



    }
}