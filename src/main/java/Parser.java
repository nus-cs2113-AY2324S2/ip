public class Parser {
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

    private void checkCommandArguments(String[] splitCommand, int expectedArgs, TaskList tasks) {
        if (tasks.size() >= 100) {
            throw new ArrayIndexOutOfBoundsException();
        }
        if (splitCommand.length < expectedArgs) {
            throw new StringIndexOutOfBoundsException();
        }
    }
}
