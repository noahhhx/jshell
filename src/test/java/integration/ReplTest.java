package integration;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

public class ReplTest {
    
    @Test
    void testRepl() throws Exception {
        try (ShellProcess shell = new ShellProcess()) {
            assertEquals("$ ", shell.read(2));
            shell.writeLine("invalid_command_1");
            assertEquals("invalid_command_1: command not found", shell.readLine());

            assertEquals("$ ", shell.read(2));
            shell.writeLine("invalid_command_2");
            assertEquals("invalid_command_2: command not found", shell.readLine());

            assertEquals("$ ", shell.read(2));
            shell.writeLine("invalid_command_2");
            assertEquals("invalid_command_2: command not found", shell.readLine());
        }
    }
}
