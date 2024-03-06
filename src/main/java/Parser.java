public class Parser {

    /**
     * Extracts and returns the first word from the input line as the command.
     *
     * @param line The input string from which the command is extracted.
     * @return The first word of the input string, interpreted as the command.
     */
    public static String getCommand(String line) { //get the command at the start of input
        return line.split(" ")[0];
    }

    /**
     * Checks if the input string is empty or contains only whitespace characters.
     *
     * @param line The string to be checked.
     * @return true if the string is empty or contains only whitespace, false otherwise.
     */
    public static boolean stringIsEmpty(String line) {
        return line.trim().isEmpty();
    }

    /**
     * Determines if the provided description contains an invalid command.
     *
     * @param description The command description to be evaluated.
     * @return true if the command is not one of the recognized commands, false otherwise.
     */
    public static boolean isInvalidCommand(String description) { //check for invalid commands to throw exception in main
        return !description.contains("list") && !description.contains("event") && !description.contains("deadline")
                && !description.contains("todo") && !description.contains("mark")
                && !description.contains("delete") && !description.contains("find");
    }

    /**
     * Extracts the task index from the end of the description and adjusts it to a zero-based index.
     *
     * @param description The command description from which to extract the task index.
     * @return The zero-based index of the task.
     * @throws DukeException If the description does not end with a number or the number is less than 1.
     */
    public static int getTaskIndex(String description) throws DukeException {
        String indexToMark = description.substring(description.lastIndexOf(" ") + 1);
        try {
            Integer.parseInt(indexToMark);
        }
        catch (NumberFormatException n) {
            throw new DukeException("Hey, the description is empty!");
        }
        int taskIndex = Integer.parseInt(indexToMark) - 1;
        if(taskIndex < 0) {
            throw new DukeException("Hey, the index is out of bounds!");
        }
        return taskIndex;
    }

    /**
     * Checks if the description starts with a task command (todo, event, or deadline).
     *
     * @param description The command description to be evaluated.
     * @return true if the description starts with a task command, false otherwise.
     */
    public static boolean isTask(String description) {
        return description.startsWith("todo") || description.startsWith("event") || description.startsWith("deadline");
    }

    /**
     * Creates a Todo task from the given line.
     *
     * @param line The command line input specifying the todo task.
     * @return A new Todo object with the specified name.
     * @throws MissingParameterException If the todo name is missing.
     */
    public static Task getTodo(String line) throws MissingParameterException {
        String todo = line.replace("todo", "");
        String todoName = todo.trim();
        if(todoName.isEmpty()) {
            throw new MissingParameterException("todo");
        }
        return new Todo(todoName);
    }

    /**
     * Creates an Event task from the given line.
     *
     * @param line The command line input specifying the event task.
     * @return A new Event object with the specified name and timing.
     * @throws MissingParameterException If any of the event parameters are missing.
     */
    public static Task getEvent(String line) throws MissingParameterException {
        String event = line.replace("event", "");
        String[] segments = event.split("/from");
        if(segments.length < 2) {
            throw new MissingParameterException("event");
        }
        String eventName = segments[0].trim();
        if(eventName.isEmpty()) {
            throw new MissingParameterException("event");
        }
        segments = segments[1].split("/to");
        if(segments.length < 2) {
            throw new MissingParameterException("event");
        }
        String from = segments[0].trim();
        String to = segments[1].trim();
        if(from.isEmpty() || to.isEmpty()) {
            throw new MissingParameterException("event");
        }
        return new Event(eventName, from, to);
    }

    /**
     * Creates a Deadline task from the given line.
     *
     * @param line The command line input specifying the deadline task.
     * @return A new Deadline object with the specified name and deadline.
     * @throws MissingParameterException If any of the deadline parameters are missing.
     */
    public static Task getDeadline(String line) throws MissingParameterException {
        String deadline = line.replace("deadline", "");
        String[] segments = deadline.split("/by");
        if(segments.length < 2) {
            throw new MissingParameterException("deadline");
        }
        String deadlineName = segments[0].trim();
        String by = segments[1].trim();
        if(deadlineName.isEmpty() || by.isEmpty()) {
            throw new MissingParameterException("deadline");
        }
        return new Deadline(deadlineName, by);
    }

    /**
     * Extracts and returns the string to find from the input line.
     *
     * @param line The input line containing the find command and the string to find.
     * @return The string to find.
     */
    public static String getStringToFind(String line) {

        return line.split(" ")[1];
    }
}
