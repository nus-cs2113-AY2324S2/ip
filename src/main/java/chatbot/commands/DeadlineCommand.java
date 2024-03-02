package chatbot.commands;

import chatbot.ChatbotException;
import chatbot.tasks.Deadline;
import chatbot.tasks.Task;
import chatbot.tasks.TaskList;
import chatbot.ui.UI;

/**
 * Represents a command to add a Deadline object.
 */
public class DeadlineCommand implements chatbot.commands.Command {
    private final TaskList taskList;
    private final String description;

    /**
     * Constructs a DeadlineCommand object.
     *
     * @param taskList The list of tasks.
     * @param description The description of the task.
     */
    public DeadlineCommand(TaskList taskList, String description) {
        this.taskList = taskList;
        this.description = description;
    }
    /**
     * Executes the command to add a Deadline object.
     *
     * @param isReading If the chatbot is reading a file, during which it will not display confirmation text.
     */
    @Override
    public void execute(boolean isReading) throws ChatbotException {
        Task newTask = new Deadline(description);
        taskList.addTask(false, newTask);
        if (!isReading) {
            UI.printAddTaskReply();
            UI.printData(newTask);
            UI.printSeparator();
        }
    }
}
