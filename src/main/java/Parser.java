public class Parser {
    private final String userInput;

    public Parser(String userInput) {
        this.userInput = userInput;
    }

    public Command parseCommand() {
        if (userInput.startsWith("mark")) {
            return new MarkCommand(Integer.parseInt(userInput.substring(5)) - 1);
        } else if (userInput.startsWith("unmark")) {
            return new UnmarkCommand(Integer.parseInt(userInput.substring(7)) - 1);
        } else if (userInput.equalsIgnoreCase("list")) {
            return new ListCommand();
        } else if (userInput.startsWith("todo")) {
            if (userInput.trim().length() > 4) {
                String description = userInput.substring(5).trim();
                return new AddTaskCommand(new ToDo(description));
            }
        } else if (userInput.startsWith("deadline")) {
            if (userInput.trim().length() > 8) {
                String[] parts = userInput.substring(9).split("/by");
                String description = parts[0].trim();
                String by = parts[1].trim();
                return new AddTaskCommand(new Deadline(description, by));
            }
        } else if (userInput.startsWith("event")) {
            if (userInput.trim().length() > 5) {
                String[] parts = userInput.substring(6).split("/from|/to");
                String description = parts[0].trim();
                String from = parts[1].trim();
                String to = parts[2].trim();
                return new AddTaskCommand(new Event(description, from, to));
            }
        } else if (userInput.startsWith("delete")) {
            return new RemoveTaskCommand(Integer.parseInt(userInput.split(" ")[1]) - 1);
        } else if (userInput.startsWith("search")) {
            if (userInput.trim().length() > 7) {
                String keyword = userInput.substring(7).trim();
                return new SearchCommand(keyword);
            }
        }
        return null;
    }
}