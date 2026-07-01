[![progress-banner](https://backend.codecrafters.io/progress/shell/e23c6231-1161-4aaf-94e2-62abd004928b)](https://app.codecrafters.io/users/noahhhx?r=2qF)

This is a starting point for Java solutions to the
["Build Your Own Shell" Challenge](https://app.codecrafters.io/courses/shell/overview).

In this challenge, you'll build your own POSIX compliant shell that's capable of
interpreting shell commands, running external programs and builtin commands like
cd, pwd, echo and more. Along the way, you'll learn about shell command parsing,
REPLs, builtin commands, and more.

**Note**: If you're viewing this repo on GitHub, head over to
[codecrafters.io](https://codecrafters.io) to try the challenge.

# Passing the first stage

The entry point for your `shell` implementation is in `src/main/java/Main.java`.
Study and uncomment the relevant code, then run the command below to execute the
tests on our servers:

```sh
codecrafters submit
```

Time to move on to the next stage!

# Stage 2 & beyond

Note: This section is for stages 2 and beyond.

1. Ensure you have `mvn` installed locally
1. Run `./your_program.sh` to run your program, which is implemented in
   `src/main/java/Main.java`.
1. Run `codecrafters submit` to submit your solution to CodeCrafters. Test
   output will be streamed to your terminal.

# Local testing workflow

Each stage's tests live on CodeCrafters' servers and aren't visible directly.
To write local JUnit tests that mirror them, follow this loop for every stage:

1. **See what the tester expects.** Debug mode is on in `codecrafters.yml`,
   so the tester logs exactly what it sends to stdin and reads from stdout:
   ```sh
   codecrafters test
   ```
2. **Write a local test.** Add a test method to `src/test/java/integration.MainTest.java`
   using the `integration.ShellProcess` helper (`src/test/java/integration.ShellProcess.java`), which
   spawns `Main` and exposes `readLine()` / `writeLine()` for line-oriented
   interaction. Example:
   ```java
   @Test
   void testEcho() throws Exception {
       try (integration.ShellProcess shell = new integration.ShellProcess()) {
           assertEquals("$ ", shell.readLine());
           shell.writeLine("echo hello");
           assertEquals("hello", shell.readLine());
           assertEquals("$ ", shell.readLine());
       }
   }
   ```
3. **Run the local tests** against the Java 26 JDK:
   ```sh
   JAVA_HOME=/usr/lib/jvm/java-26-openjdk mvn test
   ```
4. **Verify against the real tests** once your local tests pass:
   ```sh
   codecrafters test
   ```
5. **Submit** to move on to the next stage:
   ```sh
   codecrafters submit
   ```

Note: `.codecrafters/compile.sh` uses `-DskipTests` so your JUnit tests are
skipped on CodeCrafters' servers — only their own tests run there.
