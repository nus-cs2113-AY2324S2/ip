/**
 * The Parser class is responsible for interpreting user commands and executing corresponding actions on the TaskList.
 * It parses user input strings, identifies the command type, and delegates the execution to the appropriate methods
 * in the TaskList class.
 */
public class Parser {

    /**
     * Parses the given command and executes corresponding actions on the TaskList.
     *
     * @param command The command to be parsed and executed.
     * @param tasks   The TaskList object on which actions will be executed.
     * @return True if the program should exit after executing the command, false otherwise.
     * @throws DukeException If an error occurs during command execution.
     */
    public boolean parseCommand(String command, TaskList tasks) throws DukeException {
        String[] splitCommand = command.split(" ");
        try {
            if (splitCommand[0].equals("list")) {
                tasks.listTasks();
            } else if (splitCommand[0].equals("mark")) {
                checkCommandArguments(splitCommand, 2, tasks);
                tasks.markTaskAsCompleted(Integer.parseInt(splitCommand[1]));
            } else if (splitCommand[0].equals("unmark")) {
                checkCommandArguments(splitCommand, 2, tasks);
                tasks.markTaskAsNotCompleted(Integer.parseInt(splitCommand[1]));
            } else if (splitCommand[0].equals("todo")) {
                checkCommandArguments(splitCommand, 2, tasks);
                tasks.addTask(new ToDo(command));
            } else if (splitCommand[0].equals("deadline")) {
                checkCommandArguments(splitCommand, 2, tasks);
                tasks.addTask(new Deadline(command));
            } else if (splitCommand[0].equals("event")) {
                checkCommandArguments(splitCommand, 2, tasks);
                tasks.addTask(new Event(command));
            } else if (splitCommand[0].equals("delete")) {
                checkCommandArguments(splitCommand, 2, tasks);
                tasks.deleteTask(Integer.parseInt(splitCommand[1]));
            } else if (splitCommand[0].equals("find")) {
                checkCommandArguments(splitCommand, 2, tasks);
                tasks.findTasks(splitCommand[1], tasks.getTasks());
            } else if (splitCommand[0].equals("bye")) {
                return true; // Indicate that the program should exit
            } else {
                throw new IllegalArgumentException();
            }
        } catch (Exception e) {
            DukeException.handleException(e, command);
        }
        finally {
            Storage.save(tasks.getTasks());
        }
        return false; // Default return value if the command does not require exiting
    }

    /**
     * Checks if the number of command arguments is valid.
     *
     * @param splitCommand The array containing the split command.
     * @param expectedArgs The expected number of arguments for the command.
     * @param tasks        The TaskList object on which actions are being performed.
     * @throws ArrayIndexOutOfBoundsException If the number of tasks exceeds 100.
     * @throws StringIndexOutOfBoundsException If the number of arguments is less than expected.
     */
    private void checkCommandArguments(String[] splitCommand, int expectedArgs, TaskList tasks) {
        if (tasks.size() >= 100) {
            throw new ArrayIndexOutOfBoundsException();
        }
        if (splitCommand.length < expectedArgs) {
            throw new StringIndexOutOfBoundsException();
        }
    }
}