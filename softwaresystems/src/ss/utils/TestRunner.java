package ss.utils;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

/**
 * Provides a simple testing framework for test classes.
 * @author ciske
 *
 */
public abstract class TestRunner {
    private int passedTests;
    private int failedTests;
    private final String className;

    /**
     * Create a new TestRunner instance.
     * @param testClass The name of the class which is being tested.
     */
    public TestRunner(String testClass) {
        this.passedTests = 0;
        this.failedTests = 0;
        this.className = testClass;
    }

    /**
     * Begin running all tests.
     */
    public void runTests() {
        this.passedTests = 0;
        this.failedTests = 0;

        System.out.printf("[Running tests for: %s]\n", className);

        // Invoke all methods in the implementing class that have the TestMethod annotation and do
        // not have any parameters.
        for (Method method : this.getClass().getMethods()) {
            if (method.isAnnotationPresent(TestMethod.class)) {
                if (method.getParameterCount() == 0) {
                    try {
                        method.invoke(this, new Object[0]);
                    } catch (IllegalAccessException e) {
                        System.err.printf("Failed to invoke test method: %s > %s\n",
                                method.getName(), e.getMessage());
                    } catch (InvocationTargetException e) {
                        System.err.printf("Failed to invoke test method: %s > %s\n",
                                method.getName(), e.getMessage());
                    }
                }
            }
        }

        int totalTests = passedTests + failedTests;
        double percentagePassed = ((double) passedTests / totalTests) * 100;
        double percentageFailed = ((double) failedTests / totalTests) * 100;

        System.out.println("[Tests completed]");
        System.out.printf("Tests passed: %2d/%2d (%6.2f%%)\n", passedTests, totalTests,
                percentagePassed);
        System.out.printf("Tests failed: %2d/%2d (%6.2f%%)\n", failedTests, totalTests,
                percentageFailed);
    }

    private void testPassed(String name) {
        passedTests++;

        System.out.println("Test passed: " + name);
    }

    private void testFailed(String name, String message) {
        failedTests++;

        System.err.println("Test failed: " + name + " -> " + message);
    }

    private void testFailed(String name, String format, Object... args) {
        testFailed(name, String.format(format, args));
    }

    /**
     * Test if the specified value is True.
     * @param value The value to test.
     * @param name The name of the test.
     */
    protected void testTrue(boolean value, String name) {
        if (value) {
            testPassed(name);
        } else {
            testFailed(name, "Expected True, but got False.");
        }
    }

    /**
     * Test if the specified value is False.
     * @param value The value to test.
     * @param name The name of the test.
     */
    protected void testFalse(boolean value, String name) {
        if (value) {
            testFailed(name, "Expected False, but got True.");
        } else {
            testPassed(name);
        }
    }

    /**
     * Test if two objects are equal.
     * @param actual The actual value which must equal the expected value.
     * @param expected The expected value.
     * @param name The name of the test.
     */
    protected void testEqual(Object actual, Object expected, String name) {
        if (actual.equals(expected)) {
            testPassed(name);
        } else {
            testFailed(name, "%s != %s", actual, expected);
        }
    }

    /**
     * Test if two objects aren't equal.
     * @param actual The actual value which must not equal the expected value.
     * @param expected The expected value.
     * @param name The name of the test.
     */
    protected void testNotEqual(Object actual, Object expected, String name) {
        if (actual.equals(expected)) {
            testFailed(name, "%s == %s", actual, expected);
        } else {
            testPassed(name);
        }
    }

    @Retention(RetentionPolicy.RUNTIME)
    @Target(ElementType.METHOD)
    protected @interface TestMethod {
    }
}
