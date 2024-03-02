package chatbot.commands;

import chatbot.ChatbotException;
import chatbot.tasks.Deadline;
import chatbot.tasks.Task;
import chatbot.tasks.TaskList;
import chatbot.tasks.Todo;
import chatbot.ui.UI;

public class DeadlineCommand implements chatbot.commands.Command {
    private final TaskList taskList;
    private final String description;
    public DeadlineCommand(TaskList taskList, String description) {
        this.taskList = taskList;
        this.description = description;
    }
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
