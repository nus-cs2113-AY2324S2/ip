package command;

import task.Task;
import task.TaskList;
import task.Todo;
import task.Deadline;
import task.Event;
import tool.ResponseManager;

public class AddCommand implements Command {
    private final CommandType taskType;
    private final String[] taskDetails;

    public AddCommand (CommandType taskType, String[] taskDetails) {
        this.taskType = taskType;
        this.taskDetails = taskDetails;
    }

    @Override
    public void run(TaskList tasks) {
        Task newTask;
        switch (taskType) {
        case TODO:
            newTask = new Todo(taskDetails[0]);
            break;

        case DEADLINE:
            newTask = new Deadline(taskDetails);
            break;

        default:
            newTask = new Event(taskDetails);
            break;
        }
        tasks.add(newTask);
        ResponseManager.sendTaskAddedToUser(newTask + "\n" + tasks);
    }

    @Override
    public boolean isExit() {
        return false;
    }


}
