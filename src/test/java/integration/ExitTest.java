package integration;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

public class ExitTest {

    @Test
    void testExitCommand() throws Exception {
        try (ShellProcess shell = new ShellProcess()) {
            assertEquals("$ ", shell.read(2));
            shell.writeLine("invalid_strawberry_command");
            assertEquals("invalid_strawberry_command: command not found", shell.readLine());

            assertEquals("$ ", shell.read(2));
            shell.writeLine("exit");
            shell.waitForExit(5);
            
            assertEquals(0, shell.exitCode());
        }
    }
}
