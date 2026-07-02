package helper;

import java.util.ArrayList;
import java.util.List;

public class InputParser {
    
    private static final char SINGLE_QUOTE = '\'';
    private static final char DOUBLE_QUOTE = '"';
    private static final char BACKSLASH = '\\';

    public static Input parse(String input) {
        List<String> result = new ArrayList<>();
        StringBuilder current = new StringBuilder();
        State state = State.DEFAULT;
        
        for (int i = 0; i < input.length(); i++) {
            char c = input.charAt(i);
            
            switch (state) {
                case DEFAULT -> {
                    if (c == SINGLE_QUOTE) {
                        state = State.INSIDE_SINGLE_QUOTE;
                    } else if (c == DOUBLE_QUOTE) {
                        state = State.INSIDE_DOUBLE_QUOTE;
                    } else if (c == BACKSLASH) {
                        if (i + 1 < input.length()) {
                            current.append(input.charAt(++i));
                        }
                    } else if (Character.isWhitespace(c)) {
                        if (!current.isEmpty()) {
                            result.add(current.toString());
                        }
                        current = new StringBuilder();
                    } else {
                        current.append(c);
                    }
                }
                case INSIDE_SINGLE_QUOTE -> {
                    if (c == SINGLE_QUOTE) {
                        state = State.DEFAULT;
                    } else {
                        current.append(c);
                    }
                }
                case INSIDE_DOUBLE_QUOTE -> {
                    if (c == DOUBLE_QUOTE) {
                        state = State.DEFAULT;
                    } else if (c == BACKSLASH) {
                        if (i + 1 < input.length()) {
                            current.append(input.charAt(++i));
                        }
                    } else {
                        current.append(c);
                    }
                }
                default -> {
                    throw new IllegalStateException("Unexpected state: " + state);
                }
            }
        }
        
        // Handle the last word
        if (!current.isEmpty()) {
            result.add(current.toString());
        }
        
        Input parsedInput = new Input();
        parsedInput.setCommand(result.getFirst());
        List<String> arguments = result.subList(1, result.size());

        Redirect redirect = new Redirect();
        List<String> cleanArgs = new ArrayList<>();

        for (int i = 0; i < arguments.size(); i++) {
            String arg = arguments.get(i);

            if (arg.equals(">") || arg.equals("1>")) {
                if (i + 1 < arguments.size()) {
                    redirect.setStdoutFile(arguments.get(i + 1));
                    redirect.setStdoutAppend(false);
                    i++;
                }
            } else if (arg.equals(">>") || arg.equals("1>>")) {
                if (i + 1 < arguments.size()) {
                    redirect.setStdoutFile(arguments.get(i + 1));
                    redirect.setStdoutAppend(true);
                    i++;
                }
            } else if (arg.equals("2>")) {
                if (i + 1 < arguments.size()) {
                    redirect.setStderrFile(arguments.get(i + 1));
                    redirect.setStderrAppend(false);
                    i++;
                }
            } else if (arg.equals("2>>")) {
                if (i + 1 < arguments.size()) {
                    redirect.setStderrFile(arguments.get(i + 1));
                    redirect.setStderrAppend(true);
                    i++;
                }
            } else {
                cleanArgs.add(arg);
            }
        }

        parsedInput.setArguments(cleanArgs);
        if (redirect.hasStdoutRedirect() || redirect.hasStderrRedirect()) {
            parsedInput.setRedirect(redirect);
        }
        return parsedInput;
    }
    
    private enum State {
        DEFAULT,
        INSIDE_SINGLE_QUOTE,
        INSIDE_DOUBLE_QUOTE,
    }
}
