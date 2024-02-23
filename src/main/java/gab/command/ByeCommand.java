package gab.command;

import gab.Gab;
import gab.task.TaskList;
import gab.ui.Ui;

/**
 * Exits the program
 */

public class ByeCommand extends Command {
    /**
     * Change isExit boolean to true to exit the chatbot
     * @param taskList arraylist of tasks
     */

    @Override
    public void execute (TaskList taskList) {
        Gab.isExit = true;
        Ui.printLine();
        System.out.println("All the best!");
        Ui.printLine();
    }
}
