package commands.builtin;

import commands.ShellContext;

public class Exit extends BuiltIn {

    private static final String COMMAND = "exit";

    @Override
    public void execute(String[] argument, ShellContext ctx) {
        System.exit(0);
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
