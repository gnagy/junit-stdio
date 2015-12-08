package hu.webhejj.junit.stdio;

import org.junit.Assert;
import org.junit.runners.BlockJUnit4ClassRunner;
import org.junit.runners.model.FrameworkMethod;
import org.junit.runners.model.InitializationError;
import org.junit.runners.model.Statement;

import java.io.BufferedReader;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.PrintStream;

public class StdioRunner extends BlockJUnit4ClassRunner {

    private final String inputResource;
    private final String expectedOutputResource;

    public StdioRunner(Class<?> classUnderTest, String inputResource, String expectedOutputResource) throws InitializationError {
        super(classUnderTest);
        this.inputResource = inputResource;
        this.expectedOutputResource = expectedOutputResource;
    }

    @Override
    protected Statement methodBlock(final FrameworkMethod method) {
        return new Statement() {
            @Override
            public void evaluate() throws Throwable {
                InputStream in = System.in;
                PrintStream out = System.out;
                try {
                    InputStream input = openExistingStream(inputResource, "Input resource not found: ");
                    System.setIn(input);

                    ByteArrayOutputStream actualOutput = new ByteArrayOutputStream();
                    System.setOut(new PrintStream(actualOutput));

                    StdioRunner.super.methodBlock(method).evaluate();

                    assertCorrectOutput(actualOutput);

                } finally {
                    System.setIn(in);
                    System.setOut(out);
                }
            }
        };
    }

    protected InputStream openExistingStream(String path, String errorPrefix) throws FileNotFoundException {
        InputStream input = getTestClass().getJavaClass().getResourceAsStream(path);
        if(input == null) {
            throw new FileNotFoundException(errorPrefix + path);
        }
        return input;
    }

    protected void assertCorrectOutput(ByteArrayOutputStream actualOutput) throws IOException {

        BufferedReader actualLines = new BufferedReader(new InputStreamReader(new ByteArrayInputStream(actualOutput.toByteArray())));
        BufferedReader expectedLines = new BufferedReader(new InputStreamReader(openExistingStream(expectedOutputResource, "Output resource not found: ")));

        int i = 0;
        for(;;) {
            String actualLine = actualLines.readLine();
            String expectedLine = expectedLines.readLine();
            Assert.assertEquals("Line " + i++, expectedLine, actualLine);
            if(actualLine == null || expectedLine == null) {
                break;
            }
        }
    }
}
