import java.util.ArrayList;
import java.io.*;

import org.junit.runner.*;
import org.junit.runner.notification.*;

public class TestRunner {
    public static void main(String[] args) {
        PrintStream stdOut = System.out;
        PrintStream nullOut = new PrintStream(new OutputStream() {
            public void write(int b) {
                //DO NOTHING
            }
        });



        ArrayList<Class> classesToTest = new ArrayList<Class>();
        boolean anyFailures = false;

        // ADD ANY MORE CLASSES YOU WISH TO TEST HERE

        // classesToTest.add(CatTest.class);
        // classesToTest.add(CustomerTest.class);
        classesToTest.add(RentCatTest.class);
        classesToTest.add(CatTest.class);

        // For all test classes added, loop through and use JUnit
        // to run them.

        for (Class c: classesToTest) {
            System.setOut(nullOut);
            Result r = JUnitCore.runClasses(c);

            // Print out any failures for this class.

            System.setOut(stdOut);
            for (Failure f : r.getFailures()) {
                System.out.println(f.toString());
            }

            // If r is not successful, there was at least one
            // failure.  Thus, set anyFailures to true - this
            // can never be set back to false (no amount of
            // successes will ever eclipse the fact that there
            // was at least one failure.

            if (!r.wasSuccessful()) {
                anyFailures = true;
            }

        }

        // After completion, notify user if all tests passed or any failed.
        System.setOut(stdOut);
        if (anyFailures) {
            System.out.println("\n!!! - At least one failure, see above.");
        } else {
            System.out.println("\nALL TESTS PASSED");
        }
    }
}
