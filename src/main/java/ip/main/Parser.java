package ip.main;

/**
 * Deals with making sense of the user command
 */
public class Parser {
    private boolean hasEnded = false;

    /**
     * Identifies the type of command from the input and executes the command.
     * Reminds the user when the input is invalid.
     * Returns to the caller whether the data file needs to be updated
     *
     * @param line the user's input
     * @param ui the user interface interacting with the user
     * @param tasks the list of tasks
     * @return whether the data file needs to be updated
     */
    public boolean parseInput(String line, Ui ui, TaskList tasks) {
        if (line.equals("bye")) {
            hasEnded = true;
            return false;
        }
        if (line.equals("list")) {
            tasks.printTaskList();
            return false;
        }
        if (line.startsWith("find")) {
            tasks.find(line);
            return false;
        }
        if (line.startsWith("mark")) {
            return tasks.markTask(line);
        }
        if (line.startsWith("unmark")) {
            return tasks.unmarkTask(line);
        }
        if (line.startsWith("delete")) {
            return tasks.deleteTask(line);
        }
        if (line.startsWith("todo")) {
            return tasks.addTodo(line);
        }
        if (line.startsWith("deadline")) {
            return tasks.addDeadline(line);
        }
        if (line.startsWith("event")) {
            return tasks.addEvent(line);
        }
        ui.print("Possible commands: bye, list, mark, unmark, todo, deadline, event");
        return false;
    }

    /**
     * @return whether the program should end
     */
    public boolean getEnded() {
        return hasEnded;
    }
}
