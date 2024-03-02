package chatbot.commands;

import chatbot.ChatbotException;
import chatbot.tasks.Event;
import chatbot.tasks.Task;
import chatbot.tasks.TaskList;
import chatbot.tasks.Todo;
import chatbot.ui.UI;

public class EventCommand implements Command {
    private final TaskList taskList;
    private final String description;
    public EventCommand(TaskList taskList, String description) {
        this.taskList = taskList;
        this.description = description;
    }
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
