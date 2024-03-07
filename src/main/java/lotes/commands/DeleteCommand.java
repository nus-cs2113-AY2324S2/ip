package lotes.commands;

import lotes.task.TaskList;
import lotes.ui.UserInterface;

public class DeleteCommand extends Command {
    public static final String COMMAND_WORD = "delete";

    /**
     * Deletes the user task accordingly
     * @param userInput
     */
    public void run(String userInput) {
        String inputString = userInput.substring(7);
        int taskListIndex = (Integer.parseInt(inputString) - 1);

        System.out.println(UserInterface.line + UserInterface.separator
                + UserInterface.indent +" Noted. I've removed this task:"
                + UserInterface.separator + UserInterface.indent + " " + TaskList.taskList.get(taskListIndex)
                + UserInterface.separator + UserInterface.line);

        TaskList.taskList.remove(taskListIndex);
    }

    @Override
    public void execute() {

    }
}
