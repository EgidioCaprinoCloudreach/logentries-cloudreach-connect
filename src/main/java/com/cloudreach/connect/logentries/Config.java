package com.cloudreach.connect.logentries;

import lombok.AccessLevel;
import lombok.Getter;

/**
 * Defines where to send the log messages.
 *
 * In a standard configuration only the token is required.
 */
public class Config {

    /**
     * Logentries server host.
     */
    @Getter(AccessLevel.PUBLIC)
    final String host;

    /**
     * Logentries server port
     */
    @Getter(AccessLevel.PUBLIC)
    final int port;

    /**
     * Logentries token.
     */
    @Getter(AccessLevel.PUBLIC)
    final String token;

    /**
     * Use standard configuration.
     */
    public Config(String token) {
        this("data.logentries.com", 10000, token);
    }

    /**
     * Set custom configuration.
     */
    public Config(String host, int port, String token) {
        this.host = host;
        this.port = port;
        this.token = token;
    }

}
