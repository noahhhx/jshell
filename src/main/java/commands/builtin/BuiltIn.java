package commands.builtin;

import commands.ShellContext;

public abstract class BuiltIn {

    public abstract void execute(String[] argument, ShellContext ctx);

    public abstract String getHelp();

    public abstract String getName();
}
