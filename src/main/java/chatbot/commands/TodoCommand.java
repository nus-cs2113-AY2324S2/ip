package chatbot.commands;

import chatbot.ChatbotException;
import chatbot.tasks.Task;
import chatbot.tasks.TaskList;
import chatbot.tasks.Todo;
import chatbot.ui.UI;

public class TodoCommand implements Command {
    private final TaskList taskList;
    private final String description;
    public TodoCommand(TaskList taskList, String description) {
        this.taskList = taskList;
        this.description = description;
    }

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
