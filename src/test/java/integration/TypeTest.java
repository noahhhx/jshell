package integration;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

public class TypeTest {

    @Test
    void testTypeCommand() throws Exception {
        try (ShellProcess shell = new ShellProcess()) {
            assertEquals("$ ", shell.read(2));
            shell.writeLine("type echo");
            assertEquals("echo is a shell builtin", shell.readLine());

            assertEquals("$ ", shell.read(2));
            shell.writeLine("type exit");
            assertEquals("exit is a shell builtin", shell.readLine());

            assertEquals("$ ", shell.read(2));
            shell.writeLine("type type");
            assertEquals("type is a shell builtin", shell.readLine());

            assertEquals("$ ", shell.read(2));
            shell.writeLine("type invalid_banana_command");
            assertEquals("invalid_banana_command: not found", shell.readLine());
        }
    }
}
