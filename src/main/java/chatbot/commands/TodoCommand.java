package chatbot.commands;

import chatbot.tasks.Task;
import chatbot.tasks.TaskList;
import chatbot.tasks.Todo;
import chatbot.ui.UI;

/**
 * Represents a command to add a Todo object.
 */
public class TodoCommand implements Command {
    private final TaskList taskList;
    private final String description;

    /**
     * Constructs a TodoCommand object.
     *
     * @param taskList The list of tasks.
     * @param description The description of the task.
     */
    public TodoCommand(TaskList taskList, String description) {
        this.taskList = taskList;
        this.description = description;
    }

    /**
     * Executes the command to add a Todo object.
     *
     * @param isReading If the chatbot is reading a file, during which it will not display confirmation text.
     */
    @Override
    public void execute(boolean isReading) {
        Task newTask = new Todo(description);
        taskList.addTask(false, newTask);
        if (!isReading) {
            UI.printAddTaskReply();
            UI.printData(newTask);
            UI.printSeparator();
        }
    }
}
