package chatbot.commands;

import chatbot.ChatbotException;
import chatbot.tasks.Task;
import chatbot.tasks.TaskList;
import chatbot.ui.UI;

import java.util.Scanner;

public class MarkCommand implements Command {
    private final TaskList taskList;
    private final int taskNum;
    public MarkCommand(TaskList taskList, String description) {
        this.taskList = taskList;
        this.taskNum = (new Scanner(description).useDelimiter("\\D+").nextInt()) - 1;
    }

    @Override
    public void execute(boolean isReading) throws ChatbotException {
        Task selectedTask = taskList.get(taskNum);
        selectedTask.markAsDone();
        UI.printMarkReply();
        selectedTask.printData();
        UI.printSeparator();
    }
}
