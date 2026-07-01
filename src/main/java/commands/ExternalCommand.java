package commands;

import static helper.Helpers.findExecutable;

import java.nio.file.Path;
import java.util.Optional;

public class ExternalCommand {

    public void execute(String command, String[] args) {
        Optional<Path> executablePath = findExecutable(command);

        if (executablePath.isEmpty()) {
            System.out.println(command + ": command not found");
            return;
        }

        try {
            String commandArg = command + String.join(" ", args);
            Process process = new ProcessBuilder()
                  .command(commandArg)
                  .inheritIO()
                  .start();
            process.waitFor();
        } catch (Exception e) {
            System.out.println("oops! something went wrong: " + e.getMessage());
        }
    }
}
