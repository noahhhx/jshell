package commands.builtin;

import commands.ShellContext;
import commands.StdReturn;

public class Echo extends BuiltIn {

    public static final String COMMAND = "echo";
    
    @Override
    public StdReturn execute(String[] argument, ShellContext ctx) {
        StdReturn returnVal = new StdReturn();
        returnVal.setStdout(String.join(" ", argument) + "\n");
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
