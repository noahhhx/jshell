package commands;

import commands.builtin.BuiltIn;
import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

public class CommandRegistry {

    private final Map<String, BuiltIn> commands = new HashMap<>();
    private final ExternalCommand externalCommand = new ExternalCommand();

    public void register(BuiltIn command) {
        commands.put(command.getName(), command);
    }

    public Optional<BuiltIn> get(String command) {
        return Optional.ofNullable(commands.get(command));
    }

    public boolean contains(String command) {
        return commands.containsKey(command);
    }

    public void execute(String name, String[] args, ShellContext ctx) {
        get(name)
              .ifPresentOrElse(cmd -> cmd.execute(args, ctx),
                    () -> externalCommand.execute(name, args));
    }

}
