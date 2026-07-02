package commands;

import commands.builtin.BuiltIn;
import helper.Redirect;
import helper.StreamHandler;
import java.util.HashMap;
import java.util.List;
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

    public void execute(String name, List<String> args, Redirect redirect, ShellContext ctx) {
        StdReturn stdReturn;
        Optional<BuiltIn> command = get(name);
        if (command.isPresent()) {
            stdReturn = command.get().execute(args.toArray(new String[0]), ctx);
        } else {
            stdReturn = externalCommand.execute(name, args.toArray(new String[0]));
        }
        StreamHandler.handle(stdReturn, redirect);
    }

}
