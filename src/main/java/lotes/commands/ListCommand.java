package lotes.commands;

import lotes.task.Task;
import lotes.ui.UserInterface;
import lotes.task.TaskList;

public class ListCommand extends Command {
    public static final String COMMAND_WORD = "list";

    /**
     * Prints out the list of tasks to the user.
     */
    @Override
    public void execute() {
        System.out.println(UserInterface.line);

        if (TaskList.taskList.isEmpty()) {
            System.out.println(UserInterface.indent + " List is empty,"
                    + " please enter some text to add to list.");

        } else {
            System.out.println(UserInterface.indent +" Here are the tasks in your list:");
            int taskListIndex = 0;
            for (Task task : TaskList.taskList) {
                taskListIndex++;
                String taskFormatter = String.format("%s %d. %s",
                        UserInterface.indent, taskListIndex, task);

                System.out.println(taskFormatter);
            }
        }
        System.out.println(UserInterface.line);
    }
}
