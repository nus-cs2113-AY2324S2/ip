package utilities;

public class CommandIdentifier {
    public static String commandIdentifier(String userInput) {
        String commandType;

        if (userInput.trim().equals("bye")) {
            commandType = "BYE";
        } else if (userInput.trim().equals("list")) {
            commandType = "LIST";
        } else if (userInput.trim().startsWith("unmark")) {
            commandType = "UNMARK";
        } else if (userInput.trim().startsWith("mark")) {
            commandType = "MARK";
        } else if (userInput.trim().startsWith("delete")) {
            commandType = "DELETE";
        } else if (userInput.trim().startsWith("todo")) {
            commandType = "TODO";
        } else if (userInput.trim().startsWith("deadline")) {
            commandType = "DEADLINE";
        } else if (userInput.trim().startsWith("event")) {
            commandType = "EVENT";
        } else if (userInput.trim().equals("help")) {
            commandType = "HELP";
        } else {
            commandType = "INVALID";
        }
        return commandType;
    }
}
