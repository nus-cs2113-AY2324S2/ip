import java.util.ArrayList;

/**
 * Deals with making sense of the user command
 */
public class Parser {
    protected static boolean isContinue = true;

    /**
     * Parses the command given by the user and executes it accordingly
     *
     * @param command The command given by the user which is to be executed
     * @param taskList The list of tasks
     * @throws NehsikException If an error occurs when executing a command
     */
    protected static void parseCommand(String command, ArrayList<Task> taskList) throws NehsikException {
        if (command.equals("list")) {
            TaskList.displayTaskList(taskList);
        } else if (command.startsWith("mark")) {
            TaskList.markTask(command, taskList);
        } else if (command.startsWith("unmark")) {
            TaskList.unmarkTask(command, taskList);
        }  else if (command.startsWith("todo")) {
            TaskList.addTodoTask(command, taskList);
            TaskList.acknowledgeTaskAdded(taskList);
        } else if (command.startsWith("deadline")) {
            TaskList.addDeadlineTask(command, taskList);
            TaskList.acknowledgeTaskAdded(taskList);
        } else if (command.startsWith("event")) {
            TaskList.addEventTask(command, taskList);
            TaskList.acknowledgeTaskAdded(taskList);
        } else if (command.startsWith("delete")) {
            TaskList.deleteTask(command, taskList);
        } else if (command.startsWith("find")) {
            TaskList.findTask(command, taskList);
        } else if (command.equals("bye")) {
            isContinue = false;
        } else {
            throw new NehsikException("Invalid Command");
        }
    }

}
