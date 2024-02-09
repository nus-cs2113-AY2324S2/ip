public class Parser {
    public static Command determineCommand(TaskList userTasks, String userInput) {
        String[] userWords = userInput.trim().split("\\s+", 2);
        if (userInput.equals("bye")) {
            return new ByeCommand();
        }
        if (userInput.equals("list")) {
            return new ListCommand(userTasks);
        }
        String userCommand = userWords[0];
        String userParams = userWords.length == 2 ? userWords[1] : "";
        switch(userCommand) {
        case "todo":
            return new ToDoCommand(userTasks, userParams);
        case "deadline":
            return new DeadlineCommand(userTasks, userParams);
        case "event":
            return new EventCommand(userTasks, userParams);
        case "mark":
            return new MarkCommand(userTasks, userParams);
        case "unmark":
            return new UnmarkCommand(userTasks, userParams);
        default:
            return new DefaultCommand();
        }
    }
}
