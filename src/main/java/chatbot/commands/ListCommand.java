package chatbot.commands;

import chatbot.ChatbotException;
import chatbot.tasks.TaskList;
import chatbot.ui.UI;

public class ListCommand implements Command {
    private final TaskList taskList;
    public ListCommand(TaskList taskList) {
        this.taskList = taskList;
    }

    @Override
    public void execute(boolean isReading) throws ChatbotException {
        if (taskList.getListLength() == 0) {
            System.out.println("There's nothing here. ");
        } else {
            taskList.printAll();
        }
        UI.printSeparator();
    }
}
