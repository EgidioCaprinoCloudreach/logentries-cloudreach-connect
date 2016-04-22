package com.cloudreach.connect.logentries;

import com.cloudreach.connect.api.LogService;

import org.apache.commons.lang3.exception.ExceptionUtils;

/**
 * Logentries logger.
 *
 * {@inheritDoc}
 */
public class LogentriesLogService implements LogService {

    /**
     * Configuration to use for sending the messages.
     */
    final Config config;

    /**
     * Custom configuration.
     */
    public LogentriesLogService(Config config) {
        this.config = config;
    }

    /**
     * Standard configuration.
     */
    public LogentriesLogService(String token) {
        config = new Config(token);
    }

    /**
     * {@inheritDoc}
     */
    public void info(String message) {
        log(message);
    }

    /**
     * {@inheritDoc}
     */
    public void error(String message) {
        log(message);
    }

    /**
     * {@inheritDoc}
     */
    public void error(String message, Exception exception) {
        log(message);
        if (exception != null) {
            log(ExceptionUtils.getStackTrace(exception));
        }
    }

    /**
     * Start a new thread which sends the message to Logentries.
     */
    void log(String message) {
        LogentriesRunnable runnable = new LogentriesRunnable(config, message);
        Thread thread = new Thread(runnable);
        thread.start();
    }

}
