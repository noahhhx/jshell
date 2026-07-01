package commands.builtin;

import static helper.Helpers.findExecutable;

import commands.CommandRegistry;
import commands.ShellContext;
import java.util.Optional;

public class Type extends BuiltIn {

    public static final String COMMAND = "type";

    private final CommandRegistry registry;

    public Type(CommandRegistry registry) {
        this.registry = registry;
    }

    @Override
    public void execute(String[] argument, ShellContext ctx) {
        String command = argument[0];
        Optional<BuiltIn> builtin = registry.get(command);
        if (builtin.isPresent()) {
            System.out.println(command + " is a shell builtin");
            return;
        }

        findExecutable(command)
              .ifPresentOrElse(
                    path -> System.out.println(command + " is " + path),
                    () -> System.out.println(command + ": not found")
              );
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
