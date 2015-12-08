# JUnit STDIO

A JUnit extension to run tests with piping file inputs to STDIN and validation against STDOUT.

This can be useful for example when writing solutions as unit tests for coding challenges such as on [HackerRank](https://www.hackerrank.com/).

Example:

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

JUnit will run `testToUpper()` two times based on the @StdioInputs annotation.
The test runner first reads in `input000.txt`, pipes it to STDIN of the test and then compares its STDOUT to `output000.txt`.
Then, the same is done for `input001.txt` and `output001.txt` respectively. The files are read as resources
from the `CLASSPATH` in the same package where the class under test is located.

See also the [unit test that comes with this project](https://github.com/gnagy/junit-stdio/tree/master/src/test/).


## Downloads

Available on [Maven Central](http://mvnrepository.com/artifact/hu.webhejj.junit.stdio/junit-stdio/).


## License    
    
Copyright 2015 Gergely Nagy <greg@webhejj.hu>

Licensed under the Apache License, Version 2.0 (the "License");
you may not use this file except in compliance with the License.
You may obtain a copy of the License at

[http://www.apache.org/licenses/LICENSE-2.0](http://www.apache.org/licenses/LICENSE-2.0)

Unless required by applicable law or agreed to in writing, software
distributed under the License is distributed on an "AS IS" BASIS,
WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
See the License for the specific language governing permissions and
limitations under the License.