package integration;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

public class EchoTest {

    @Test
    void testEcho() throws Exception {
        try (ShellProcess shell = new ShellProcess()) {
            assertEquals("$ ", shell.read(2));
        }
    }
}
