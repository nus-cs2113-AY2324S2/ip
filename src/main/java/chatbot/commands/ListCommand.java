package chatbot.commands;

import chatbot.tasks.TaskList;
import chatbot.ui.UI;

/**
 * Represents a command to list all tasks.
 */
public class ListCommand implements Command {
    private final TaskList taskList;
    public ListCommand(TaskList taskList) {
        this.taskList = taskList;
    }

    /**
     * Executes the command to list all tasks.
     *
     * @param isReading If the chatbot is reading a file, during which it will not display confirmation text.
     */
    @Override
    public void execute(boolean isReading) {
        if (isReading) {
            return;
        }
        if (taskList.getListLength() == 0) {
            System.out.println("There's nothing here. ");
        } else {
            System.out.println("Have you forgotten already? ");
            taskList.printAll();
        }
        UI.printSeparator();
    }
}
