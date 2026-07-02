package commands.builtin;

import commands.ShellContext;
import commands.StdReturn;

public class PrintWorkingDirectory extends BuiltIn {
    
    public static final String COMMAND = "pwd";

    @Override
    public StdReturn execute(String[] argument, ShellContext ctx) {
        StdReturn returnVal = new StdReturn();
        returnVal.setStdout(ctx.getCwd().toString() + "\n");
        return returnVal;
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
