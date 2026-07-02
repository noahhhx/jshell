package integration.navigation;

import static org.junit.jupiter.api.Assertions.assertEquals;

import integration.ShellProcess;
import java.nio.file.Path;
import java.nio.file.Paths;
import org.junit.jupiter.api.Test;

public class PwdBuiltinTest {

    @Test
    void testPwdBuiltin() throws Exception {
        Path path = Paths.get("").toAbsolutePath();
        
        try (ShellProcess shell = new ShellProcess()) {
            assertEquals("$ ", shell.read(2));
            shell.writeLine("type pwd");
            assertEquals("pwd is a shell builtin", shell.readLine());

            assertEquals("$ ", shell.read(2));
            shell.writeLine("pwd");
            assertEquals(path.toString(), shell.readLine());
        }
    }
}
