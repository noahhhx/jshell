package integration;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.Writer;
import java.util.concurrent.TimeUnit;

/**
 * Spawns the Main program as a subprocess and exposes its stdin/stdout
 * for line-oriented interaction. Used by all stage tests.
 *
 * Usage:
 *   try (integration.ShellProcess shell = new integration.ShellProcess()) {
 *       assertEquals("$ ", shell.readLine());
 *       shell.writeLine("echo hello");
 *       ...
 *   }
 */
class ShellProcess implements AutoCloseable {

    private static final String JAVA = System.getProperty("java.home") + "/bin/java";

    private final Process process;
    private final BufferedReader stdout;
    private final Writer stdin;

    ShellProcess() throws IOException {
        ProcessBuilder pb = new ProcessBuilder(
                JAVA, "--enable-preview", "-cp", "target/classes", "Main");
        process = pb.start();
        stdout = new BufferedReader(new InputStreamReader(process.getInputStream()));
        stdin = new OutputStreamWriter(process.getOutputStream());
    }

    String readLine() throws IOException {
        return stdout.readLine();
    }

    String read(int numChars) throws IOException {
        StringBuilder sb = new StringBuilder(numChars);
        for (int i = 0; i < numChars; i++) {
            int c = stdout.read();
            if (c == -1) break;
            sb.append((char) c);
        }
        return sb.toString();
    }

    void writeLine(String line) throws IOException {
        stdin.write(line);
        stdin.write("\n");
        stdin.flush();
    }

    void waitForExit(long timeoutSeconds) throws InterruptedException {
        if (!process.waitFor(timeoutSeconds, TimeUnit.SECONDS)) {
            throw new AssertionError("Process did not exit within " + timeoutSeconds + "s");
        }
    }

    int exitCode() {
        return process.exitValue();
    }

    @Override
    public void close() {
        try {
            stdin.close();
        } catch (IOException ignored) {
        }
        try {
            stdout.close();
        } catch (IOException ignored) {
        }
        process.destroyForcibly();
    }
}