package hu.webhejj.junit.stdio;

import org.junit.Test;
import org.junit.runner.RunWith;

import java.io.BufferedReader;
import java.io.InputStreamReader;

@RunWith(StdioSuite.class)
@StdioInputs({"000", "001"})
public class ToUpperTest {

    @Test
    public void testToUpper() throws Exception {
        BufferedReader stdin = new BufferedReader(new InputStreamReader(System.in));
        String line = "";
        while ((line = stdin.readLine()) != null){
            System.out.println(line.toUpperCase());
        }
    }
}
