/**
 * Handles parsing of user commands and conversion of stored data back into task objects.
 * This class is responsible for interpreting user input and executing corresponding actions
 * in the TaskList.
 */
public class Parser {

    /**
     * Parses and executes a command input by the user.
     * It interprets various commands such as adding, deleting, marking, unmarking tasks, listing tasks,
     * finding tasks by keyword, and exiting the application.
     *
     * @param input The user input string to be parsed.
     * @param tasks The TaskList to operate on.
     * @param ui The Ui instance for user interaction.
     * @return true if the application should continue running, false if it should terminate.
     */

    public static boolean parseCommand(String input, TaskList tasks, Ui ui) {
        try {
            if (input.equalsIgnoreCase("bye")) {
                Ui.printExit();
                return false;
            } else if (input.toLowerCase().startsWith("find ")) {
                String keyword = input.substring(5);
                TaskList.findTask(keyword);
                return true;
            } else if (input.toLowerCase().startsWith("delete ")) {
                TaskList.deleteTask(input);
            } else if (input.equalsIgnoreCase("list")) {
                TaskList.displayTasks();
            } else if (input.toLowerCase().startsWith("mark ")) {
                TaskList.markTask(input);
            } else if (input.toLowerCase().startsWith("unmark ")) {
                TaskList.unmarkTask(input);
            } else {
                TaskList.addTask(input);
            }

        } catch (Exception e) {
            Ui.printParseError();
        }
        return true;
    }

    /**
     * Parses a task from its string representation stored in a file back into a Task object.
     * This method is designed to support different types of tasks (ToDo, Deadline, Event)
     * based on their data representation.
     *
     * @param data The string representation of the task from the storage file.
     * @return The Task object parsed from the string, or null if the string format is incorrect.
     */
    public static Task parseTaskFromString(String data) {
        String[] parts = data.split("\\|");

        if (parts.length < 3) return null;

        String type = parts[0].trim();
        boolean isDone = parts[1].trim().equals("1");
        String description = parts[2].trim();
        Task task = null;

        switch (type) {
            case "T":
                task = new ToDo(description);
                break;
            case "D":
                if (parts.length < 4) return null;
                String by = parts[3].trim();
                task = new Deadline(description, by);
                break;
            case "E":
                if (parts.length < 5) return null;
                String from = parts[3].trim();
                String to = parts[4].trim();
                task = new Event(description, from, to);
                break;
        }

        if (task != null && isDone) {
            task.markAsDone();
        }
        return task;
    }
}
