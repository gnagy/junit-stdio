package hu.webhejj.junit.stdio;

import org.junit.runner.Runner;
import org.junit.runners.Suite;
import org.junit.runners.model.InitializationError;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class StdioSuite extends Suite {

    public StdioSuite(Class<?> classUnderTest) throws InitializationError {
        super(classUnderTest, createRunners(classUnderTest));
    }

    private static List<Runner> createRunners(Class<?> classUnderTest) throws InitializationError {
        StdioInputs annotation = classUnderTest.getAnnotation(StdioInputs.class);
        if(annotation == null) {
            return Collections.emptyList();
        }
        List<Runner> runners = new ArrayList<Runner>(annotation.value().length);
        for(String id: annotation.value()) {
            StdioRunner runner = new StdioRunner(classUnderTest,
                    String.format("input%s.txt", id),
                    String.format("output%s.txt", id));
            runners.add(runner);
        }
        return runners;
    }
}
