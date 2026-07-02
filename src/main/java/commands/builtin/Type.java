package commands.builtin;

import static helper.Helpers.findExecutable;

import commands.CommandRegistry;
import commands.ShellContext;
import commands.StdReturn;
import java.nio.file.Path;
import java.util.Optional;

public class Type extends BuiltIn {

    public static final String COMMAND = "type";
    
    private final CommandRegistry registry;
    
    public Type(CommandRegistry registry) {
        this.registry = registry;
    }
    
    @Override
    public StdReturn execute(String[] argument, ShellContext ctx) {
        String command = argument[0];
        Optional<BuiltIn> builtin = registry.get(command);
        if (builtin.isPresent()) {
            StdReturn returnVal = new StdReturn();
            returnVal.setStdout(command + " is a shell builtin\n");
            return returnVal;
        }
        
        Optional<Path> path = findExecutable(command);
        StdReturn stdReturn = new StdReturn();
        if (path.isPresent()) {
            stdReturn.setStdout(command + " is " + path.get() + "\n");
        } else {
            stdReturn.setStdout(command + ": not found\n");
        }
        return stdReturn;
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
