package ip.main;

/**
 * Deals with making sense of the user command
 */
public class Parser {
    private boolean hasEnded = false;

    /**
     * Identifies the type of command from the input and executes the command.
     * Returns to the caller whether the data file needs to be updated
     *
     * @param line the user's input
     * @param ui the user interface interacting with the user
     * @param tasks the list of tasks
     * @param updater the updater that updates the tasks list
     * @return whether the data file needs to be updated
     */
    public boolean parseInput(String line, Ui ui, TaskList tasks, TaskListUpdater updater) {
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
            return updater.markTask(tasks, line);
        }
        if (line.startsWith("unmark")) {
            return updater.unmarkTask(tasks, line);
        }
        if (line.startsWith("delete")) {
            return updater.deleteTask(tasks, line);
        }
        if (line.startsWith("todo")) {
            return updater.addTodo(tasks, line);
        }
        if (line.startsWith("deadline")) {
            return updater.addDeadline(tasks, line);
        }
        if (line.startsWith("event")) {
            return updater.addEvent(tasks, line);
        }
        ui.warnInvalidCommand();
        return false;
    }

    /**
     * @return whether the program should end
     */
    public boolean getEnded() {
        return hasEnded;
    }
}
