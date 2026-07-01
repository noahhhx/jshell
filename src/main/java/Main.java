import commands.CommandRegistry;
import commands.ShellContext;
import commands.builtin.Echo;
import commands.builtin.Exit;
import commands.builtin.Type;
import java.nio.file.Path;
import java.util.Arrays;
import java.util.Scanner;

public class Main {

    private static final CommandRegistry registry = new CommandRegistry();
    private static final ShellContext ctx = new ShellContext(Path.of("").toAbsolutePath());
    
    public static void main(String[] args) throws Exception {
        registerBuiltIns();

        try (Scanner scanner = new Scanner(System.in)) {
            while (true) {
                System.out.print("$ ");

                String input = scanner.nextLine().trim();

                if (input.isEmpty()) {
                    continue;
                }

                String[] parts = input.split("\\s+");
                String command = parts[0];
                String[] arguments = Arrays.copyOfRange(parts, 1, parts.length);

                registry.execute(command, arguments, ctx);
            }
        }
    }

    private static void registerBuiltIns() {
        registry.register(new Exit());
        registry.register(new Echo());
        registry.register(new Type(registry));
    }
}
