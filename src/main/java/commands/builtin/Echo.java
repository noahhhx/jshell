package commands.builtin;

import commands.ShellContext;

public class Echo extends BuiltIn {

    @Override
    public void execute(String[] argument, ShellContext ctx) {
        System.out.println(String.join(" ", argument));
    }

    @Override
    public String getHelp() {
        return "";
    }

    @Override
    public String getName() {
        return "echo";
    }
}
