package helper;

import java.io.File;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.Optional;

public class Helpers {

    public static Optional<Path> findExecutable(String command) {
        for (String path : getPathEnvs()) {
            Path executablePath = Path.of(path, command);
            if (Files.exists(executablePath) && Files.isExecutable(executablePath)) {
                return Optional.of(executablePath);
            }
        }

        return Optional.empty();
    }

    private static String[] getPathEnvs() {
        String path = System.getenv("PATH");

        if (path == null || path.isBlank()) {
            return new String[0];
        }

        return path.split(File.pathSeparator);
    }
}
