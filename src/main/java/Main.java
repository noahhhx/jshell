import commands.builtin.ChangeDirectory;
import commands.CommandRegistry;
import commands.builtin.Echo;
import commands.builtin.Exit;
import commands.builtin.PrintWorkingDirectory;
import commands.ShellContext;
import commands.builtin.Type;
import helper.Input;
import helper.InputParser;
import java.nio.file.Path;
import java.util.Scanner;

public class Main {
    
    private static final CommandRegistry registry = new CommandRegistry();
    private static final ShellContext ctx = new ShellContext(Path.of("").toAbsolutePath());

    public static void main(String[] args) {
        registerBuiltIns();
        
        try (Scanner scanner = new Scanner(System.in)) {
            while (true) {
                System.out.print("$ ");

                String input = scanner.nextLine().trim();

                if (input.isEmpty()) {
                    continue;
                }
                
                Input parsedInput = InputParser.parse(input);
                registry.execute(parsedInput.getCommand(), parsedInput.getArguments(), parsedInput.getRedirect(), ctx);
            }
        }
    }
    
    private static void registerBuiltIns() {
        registry.register(new Echo());
        registry.register(new Type(registry));
        registry.register(new Exit());
        registry.register(new PrintWorkingDirectory());
        registry.register(new ChangeDirectory());
    }
}