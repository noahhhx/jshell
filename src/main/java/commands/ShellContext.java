package commands;

import java.nio.file.Path;

public class ShellContext {

    private Path cwd;

    public ShellContext(Path cwd) {
        this.cwd = cwd;
    }

    public Path getCwd() {
        return cwd;
    }

    public void setCwd(Path cwd) {
        this.cwd = cwd;
    }
}
