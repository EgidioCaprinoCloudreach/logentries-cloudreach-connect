package com.cloudreach.connect.logentries;

import org.junit.Test;

public class LogentriesRunnableTest {

    final Config testConfig = new TestConfig();
    final String message = "Testing " + LogentriesRunnable.class.getName() + "\n" +
            "with a multi-line message";

    @Test
    public void testDoNotThrowException() throws Exception {
        Runnable runnable = new LogentriesRunnable(testConfig, message);
        runnable.run();
    }

}
