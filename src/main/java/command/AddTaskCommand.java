package command;

import task.TaskList;
import ui.Message;
import ui.Parser;

/**
 * The AddTaskCommand class represents a command to add a task to the task list.
 */
public class AddTaskCommand implements Command {
    private final Parser token;
    private final String[] taskDescriptions;

    /**
     * Creates an AddTaskCommand object.
     *
     * @param token The token of the task.
     * @param taskDescriptions The descriptions of the task.
     */
    public AddTaskCommand(Parser token, String[] taskDescriptions) {
        this.token = token;
        this.taskDescriptions = taskDescriptions;
    }

    /**
     * Executes the command to add a task to the task list.
     *
     * @param tasks The list of tasks.
     * @return False because the program should continue running.
     */
    @Override
    public boolean execute(TaskList tasks) {
        tasks.addTask(token, taskDescriptions);
        System.out.println(Message.ADD_TASK_MESSAGE_FRONT
                + tasks.getTask(tasks.size())
                + Message.ADD_TASK_MESSAGE_MIDDLE
                + tasks.size()
                + Message.ADD_TASK_MESSAGE_END);
        return false;
    }
}
