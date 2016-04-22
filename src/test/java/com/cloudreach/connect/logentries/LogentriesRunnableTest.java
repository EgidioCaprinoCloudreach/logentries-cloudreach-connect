package com.cloudreach.connect.logentries;

import org.junit.Test;

public class LogentriesRunnableTest {

    final Config testConfig = new TestConfig();
    final String message = "Testing " + getClass().getName();

    @Test
    public void testDoNotThrowException() throws Exception {
        Runnable runnable = new LogentriesRunnable(testConfig, message);
        runnable.run();
    }

}
