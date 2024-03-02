package chatbot.commands;

import chatbot.ChatbotException;
import chatbot.tasks.Event;
import chatbot.tasks.Task;
import chatbot.tasks.TaskList;
import chatbot.ui.UI;

/**
 * Represents a command to add a Event object.
 */
public class EventCommand implements Command {
    private final TaskList taskList;
    private final String description;

    /**
     * Constructs a EventCommand object.
     *
     * @param taskList The list of tasks.
     * @param description The description of the task.
     */
    public EventCommand(TaskList taskList, String description) {
        this.taskList = taskList;
        this.description = description;
    }

    /**
     * Executes the command to add a Event object.
     *
     * @param isReading If the chatbot is reading a file, during which it will not display confirmation text.
     */
    @Override
    public void execute(boolean isReading) throws ChatbotException {
        Task newTask = new Event(description);
        taskList.addTask(false, newTask);
        if (!isReading) {
            UI.printAddTaskReply();
            UI.printData(newTask);
            UI.printSeparator();
        }
    }
}
