package commands.builtin;

import commands.ShellContext;
import commands.StdReturn;

public abstract class BuiltIn {

    public abstract StdReturn execute(String[] argument, ShellContext ctx);
    
    public abstract String getHelp();
    
    public abstract String getName();
}
