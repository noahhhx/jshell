package integration;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

public class EchoTest {

    @Test
    void testEcho() throws Exception {
        try (ShellProcess shell = new ShellProcess()) {
            assertEquals("$ ", shell.read(2));
            shell.writeLine("echo grape blueberry mango");
            assertEquals("grape blueberry mango", shell.readLine());

            assertEquals("$ ", shell.read(2));
            shell.writeLine("echo mango orange");
            assertEquals("mango orange", shell.readLine());
        }
    }
}
