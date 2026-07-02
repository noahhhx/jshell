package commands;

import static helper.Helpers.findExecutable;

import java.nio.file.Path;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class ExternalCommand {

    public StdReturn execute(String command, String[] args) {
        Optional<Path> executablePath = findExecutable(command);

        if (executablePath.isEmpty()) {
            StdReturn error = new StdReturn();
            error.setStderr(command + ": command not found\n");
            return error;
        }

        try {
            List<String> commandArgs = new ArrayList<>();
            commandArgs.add(command);
            commandArgs.addAll(List.of(args));
            Process process = new ProcessBuilder()
                  .command(commandArgs)
                  .start();

            String[] stdout = new String[1];
            String[] stderr = new String[1];

            Thread stdoutThread = Thread.ofVirtual().start(() -> {
                try {
                    stdout[0] = new String(process.getInputStream().readAllBytes());
                } catch (Exception ignored) {
                }
            });
            Thread stderrThread = Thread.ofVirtual().start(() -> {
                try {
                    stderr[0] = new String(process.getErrorStream().readAllBytes());
                } catch (Exception ignored) {
                }
            });

            process.getOutputStream().close();
            stdoutThread.join();
            stderrThread.join();
            process.waitFor();

            StdReturn returnVal = new StdReturn();

            if (stderr[0] != null && !stderr[0].isEmpty()) {
                returnVal.setStderr(stderr[0]);
            }
            if (stdout[0] != null && !stdout[0].isEmpty()) {
                returnVal.setStdout(stdout[0]);
            }
            return returnVal;

        } catch (Exception e) {
            System.out.println("oops! something went wrong: " + e.getMessage());
        }
        return null;
    }
}
