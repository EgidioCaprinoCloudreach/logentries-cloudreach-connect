package com.cloudreach.connect.logentries;

import com.cloudreach.alumina.utils.UT;

import org.apache.commons.io.IOUtils;
import org.apache.commons.lang3.StringUtils;

import java.io.IOException;
import java.io.OutputStream;
import java.net.Socket;

/**
 * Runnable for sending a message to Logentries.
 */
class LogentriesRunnable implements Runnable {

    /**
     * Configuration to use for sending the message to Logentries.
     */
    final Config config;

    /**
     * Message to send.
     */
    final String message;

    /**
     * Define the {@link #config} to use for sending the {@link #message}.
     */
    LogentriesRunnable(Config config, String message) {
        this.config = config;
        this.message = message;
    }

    /**
     * {@inheritDoc}
     */
    public void run() {
        try {
            sendToLogentries(message);
        } catch (Exception e) {
            fallback();
            throw new RuntimeException("Unable to send log message to Logentries " +
                    "because of the error: " + e.getMessage(), e);
        }
    }

    /**
     * Sends each line separately, adding the token defined in {@link #config}.
     */
    void sendToLogentries(String message) throws IOException {
        if (message != null) {
            String[] lines = message.split("\\r?\\n");
            for (String line : lines) {
                line = StringUtils.trimToNull(line);
                if (line != null) {
                    try (Socket socket = new Socket(config.getHost(), config.getPort())) {
                        try (OutputStream outputStream = socket.getOutputStream()) {
                            IOUtils.write(config.getToken() + " " + line, outputStream);
                        }
                    }
                }
            }
        }
    }

    /**
     * In case of error while sending the message to Logentries, write it in the Cloudreach Connect
     * application log.
     */
    void fallback() {
        if (message != null) {
            UT.LOG(getClass().getName() + " - " + message);
        }
    }

}
