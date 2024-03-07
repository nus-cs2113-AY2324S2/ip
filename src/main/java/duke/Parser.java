package duke;

public class Parser {
    private Storage storage = new Storage();

    /**
     * Tries to parse the input.
     * If an exception is thrown prints out that it is not a valid command
     *
     * @param input The user input read from UI
     */
    public void tryParseInput(String input)  {
        try {
            parseInput(input);
        } catch (PythiaException pe) {
            System.out.println("Not a valid command\n" + MoodSprite.getLineBreak());
        }
    }

    /**
     * Parses the input to determine what command was given and executes the relevant command
     *
     * @param input Input given by user
     * @throws PythiaException Thrown if no recognised commands are found in the user input
     */
    public void parseInput(String input) throws PythiaException {
        if (input.equalsIgnoreCase("list")) {
            Command.list();
        } else if (isTaskCommand(input)) {
            Command.tryAddTask(input);
        } else if (input.contains("unmark ")) {
            Command.unmark(input);
        } else if (input.contains("mark ")) {
            Command.mark(input);
        } else if (input.contains("delete ")) {
            Command.delete(input);
        } else if (input.contains("find ")) {
            Command.find(input);
        } else {
            throw new PythiaException();
        }
        storage.trySaveList();
        System.out.println(MoodSprite.getLineBreak());
    }

    /**
     * Removes any extra spaces for each of the inputs
     *
     * @param splitTask An array which contains a split version of the user input
     * @return Trimmed split array
     */
    public String[] splitTaskTrimmer(String[] splitTask) {
        for (int i = 0; i < splitTask.length; i++) {
            splitTask[i] = splitTask[i].trim();
        }
        return splitTask;
    }

    /**
     * Parses the input containing deadline command
     *
     * @param task The deadline command provided by the user
     * @return A array with just the time of the deadline
     */
    public String[] parseDeadline(String task) {
        task = task.replaceFirst("deadline", "");
        return splitTaskTrimmer(task.split("/"));
    }

    /**
     * Parses an input containing an event command
     *
     * @param task The event command provided by the user
     * @return An array with the start and end times of the event
     */
    public String[] parseEvent(String task) {
        task = task.replaceFirst("event", "");
        return splitTaskTrimmer((task.split("/")));
    }

    /**
     * Determines if a string contains one of the task related commands (todo, deadline, event)
     *
     * @param task The string containing the input from the user
     * @return True if the input contains a task command and false otherwise
     */
    public boolean isTaskCommand(String task) {
        return (task.contains("todo") | task.contains("deadline") | task.contains("event"));
    }
}
