package integration.navigation;

import static org.junit.jupiter.api.Assertions.assertEquals;

import integration.ShellProcess;
import java.nio.file.Path;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.io.TempDir;

public class CdBuiltinTest {

    @Test
    void testCdBuiltin(@TempDir Path tempDir) throws Exception {
        Path nonexistent = tempDir.resolve("non-existing-directory");

        try (ShellProcess shell = new ShellProcess()) {
            assertEquals("$ ", shell.read(2));

            shell.writeLine("cd " + tempDir);
            assertEquals("$ ", shell.read(2));
            shell.writeLine("pwd");
            assertEquals(tempDir.toString(), shell.readLine());

            assertEquals("$ ", shell.read(2));

            shell.writeLine("cd " + nonexistent);
            assertEquals("cd: " + nonexistent + ": No such file or directory", shell.readLine());
        }
    }
}
