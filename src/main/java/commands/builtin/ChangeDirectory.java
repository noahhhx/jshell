package commands.builtin;

import commands.ShellContext;
import commands.StdReturn;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

public class ChangeDirectory extends BuiltIn {

    private static final String COMMAND = "cd";

    @Override
    public StdReturn execute(String[] arguments, ShellContext ctx) {
        Path targetPath = null;
        if (arguments.length > 1) {
            StdReturn error = new StdReturn();
            error.setStderr("cd: too many arguments\n");
            error.setExitCode(1);
            return error;
        } else if (arguments.length == 0) {
            String home = System.getenv("HOME");
            if (home == null) {
                home = System.getProperty("user.home");
            }
            targetPath = Paths.get(home);
        } else {
            String arg = arguments[0];
            if (arg.contains("~") && !arg.startsWith("~")) {
                StdReturn error = new StdReturn();
                error.setStderr("cd: " + arg + ": ~ must be first\n");
                error.setExitCode(1);
                return error;
            }
            if (arg.startsWith("~")) {
                String home = System.getenv("HOME");
                if (home == null) {
                    home = System.getProperty("user.home");
                }
                arg = arg.replaceFirst("^~", home);
            }
            targetPath = ctx.getCwd().resolve(arg).normalize();
        }
        if (targetPath != null) {
            if (Files.isDirectory(targetPath)) {
                ctx.setCwd(targetPath.toAbsolutePath());
            } else {
                StdReturn error = new StdReturn();
                error.setStderr("cd: " + targetPath + ": No such file or directory\n");
                error.setExitCode(1);
                return error;
            }
        }
        return null;
    }

    @Override
    public String getHelp() {
        return "";
    }

    @Override
    public String getName() {
        return COMMAND;
    }
}
