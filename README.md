# JShell

Building a POSIX compliant in Java following the 
["Build Your Own Shell" Challenge](https://app.codecrafters.io/courses/shell/overview) 
from CodeCrafters for practise and learning.

# Local testing workflow

As to not fully rely on the code crafters testing workflow, the below has been setup to write
equivalent tests easily.

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
