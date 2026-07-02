package helper;

import commands.StdReturn;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.StandardOpenOption;

public class StreamHandler {

    public static void handle(StdReturn stdReturn, Redirect redirect) {
        if (stdReturn == null) {
            return;
        }
        if (redirect != null && redirect.hasStdoutRedirect()) {
            String content = stdReturn.getStdout() != null ? stdReturn.getStdout() : "";
            writeToFile(Path.of(redirect.getStdoutFile()), content, redirect.isStdoutAppend());
        } else if (stdReturn.getStdout() != null) {
            System.out.print(stdReturn.getStdout());
        }
        if (redirect != null && redirect.hasStderrRedirect()) {
            String content = stdReturn.getStderr() != null ? stdReturn.getStderr() : "";
            writeToFile(Path.of(redirect.getStderrFile()), content, redirect.isStderrAppend());
        } else if (stdReturn.getStderr() != null) {
            System.out.print(stdReturn.getStderr());
        }
    }

    private static void writeToFile(Path path, String content, boolean append) {
        try {
            if (append) {
                Files.writeString(path, content, StandardOpenOption.CREATE, StandardOpenOption.APPEND);
            } else {
                Files.writeString(path, content);
            }
        } catch (IOException e) {
            System.out.println("Unable to write to file: " + e.getMessage());
        }
    }
}
