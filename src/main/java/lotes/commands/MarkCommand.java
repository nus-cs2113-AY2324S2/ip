package lotes.commands;

import lotes.task.TaskList;
import lotes.ui.UserInterface;

public class MarkCommand extends Command {
    public static final String COMMAND_WORD = "mark";

    /**
     * Mark the task number in the task list.
     * @param userInput
     */
    public void run(String userInput) {
        String inputString = userInput.substring(5);
        int taskListIndex = (Integer.parseInt(inputString) - 1);

        TaskList.taskList.get(taskListIndex).setDone(true);

        System.out.println(UserInterface.line + UserInterface.separator
                + UserInterface.indent +" Nice! I've marked this task as done:"
                + UserInterface.separator + UserInterface.indent + " " + TaskList.taskList.get(taskListIndex)
                + UserInterface.separator + UserInterface.line);
    }

    @Override
    public void execute() {

    }
}
