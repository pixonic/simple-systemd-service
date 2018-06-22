package com.pixonic.simplesystemdservice;

import java.io.IOException;

public class Systemd {
    /**
     * Call this when your app is started.
     */
    public static void sendReady() {
        try {
            Process process = new ProcessBuilder("systemd-notify", "--ready")
                    .redirectOutput(ProcessBuilder.Redirect.INHERIT)
                    .redirectError(ProcessBuilder.Redirect.INHERIT)
                    .start();

            int exitCode = process.waitFor();
            if (exitCode != 0) {
                throw new RuntimeException("Can't notify systemd");
            }
        } catch (IOException | InterruptedException e) {
            throw new RuntimeException("Can't notify systemd", e);
        }
    }
}
