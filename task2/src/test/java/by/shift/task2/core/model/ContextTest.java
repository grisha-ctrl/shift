package by.shift.task2.core.model;

import by.shift.task2.core.testModel.TestClassFirst;
import by.shift.task2.core.testModel.TestClassSecond;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;

import java.io.File;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.IntStream;

class ContextTest {


    @Test
    void getPackageName() {
        String expected = "by.shift.task2.core.testModel.TestClassThird";
        String actual = Context.getPackageName(new File("src/test/java/by/shift/task2/core/testModel/TestClassThird.java"));
        Assertions.assertThat(actual).isEqualTo(expected);
    }

    @Test
    void setUp() {

        try {
            Context.setUp(new File("src/test/java/by/shift/task2/core/testModel"));
            System.out.println(Context.getFigures());

            List<Calculator> expected = new ArrayList<>();

            Calculator testClassFirst = new TestClassFirst();

            Calculator testClassSecond = new TestClassSecond();

            expected.add(testClassFirst);
            expected.add(testClassSecond);

            List<Calculator> actual = Context.getFigures();

            Assertions.assertThat(actual).hasSameSizeAs(expected);

            boolean areSameClasses = IntStream.range(0, expected.size())
                    .allMatch(i -> actual.get(i).getClass().equals(expected.get(i).getClass()));

            Assertions.assertThat(areSameClasses).isTrue();
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }
}