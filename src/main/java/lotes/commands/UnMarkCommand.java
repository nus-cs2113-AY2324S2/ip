package lotes.commands;

import lotes.task.TaskList;
import lotes.ui.UserInterface;

public class UnMarkCommand extends Command {
    public static final String COMMAND_WORD = "unmark";

    //  Unmark the task number in the task list.
    public void run(String userInput) {
        String inputString = userInput.substring(7);
        int taskListIndex = (Integer.parseInt(inputString) - 1);

        TaskList.taskList.get(taskListIndex).setDone(false);

        System.out.println(UserInterface.line + UserInterface.separator
                + UserInterface.indent + " OK, I've marked this task as not done yet:"
                + UserInterface.separator + UserInterface.indent + " " + TaskList.taskList.get(taskListIndex)
                + UserInterface.separator + UserInterface.line);
    }

    @Override
    public void execute() {

    }
}
