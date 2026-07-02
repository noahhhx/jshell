package integration;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

public class ExternalProgramTest {
    
    @Test
    void testInvalidCommand() throws Exception {
        try (ShellProcess shell = new ShellProcess()) {
            assertEquals("$ ", shell.read(2));
            shell.writeLine("curl -h");
            assertEquals("Usage: curl [options...] <url>", shell.readLine());
        }
    }
}
