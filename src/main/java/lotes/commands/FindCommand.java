package lotes.commands;

import lotes.task.Task;
import lotes.task.TaskList;
import lotes.ui.UserInterface;

public class FindCommand extends Command {
    public static final String COMMAND_WORD = "find";

    public void run(String userInput) {
        System.out.println(UserInterface.line);
        String inputString = userInput.substring(5);

        if (TaskList.taskList.isEmpty()) {
            System.out.println(UserInterface.indent + " List is empty,"
                    + " please enter add to list before performing a find command.");

        } else {
            System.out.println(UserInterface.indent +" Here are the matching tasks in your list:");
            int taskListIndex = 0;
            for (Task task : TaskList.taskList) {
                taskListIndex++;

                if (task.toString().contains(inputString)) {
                    String taskFormatter = String.format("%s %d. %s",
                            UserInterface.indent, taskListIndex, task);
                    System.out.println(taskFormatter);
                }
            }
        }
        System.out.println(UserInterface.line);
    }
}
