package lotes.commands;

import lotes.task.Deadline;
import lotes.task.Task;
import lotes.task.TaskList;
import lotes.ui.UserInterface;

public class AddDeadlineCommand extends Command {
    public static final String COMMAND_WORD = "deadline";

    // Adds a new Deadline task to the task list,
    // tasks that need to be done before a specific date/time (/by).

    public void run(String description) {
        String deadlineDescription = description.substring(9);

        String[] descriptionArguments = deadlineDescription.split("/by");

        String properDescription= descriptionArguments[0].trim();
        String byDate = descriptionArguments[1].trim();

        String formattedDescription = String.format("%s (by: %s)",
                properDescription, byDate);

        Task newTask = new Deadline(formattedDescription, byDate);
        TaskList.taskList.add(newTask);

        String formattedString = String.format("%s%s Got it. I've added this task: %s" +
                        "       %s%s     Now you have %d tasks in the list%s"
                , UserInterface.line + UserInterface.separator, UserInterface.indent, UserInterface.separator
                , newTask, UserInterface.separator, TaskList.taskList.size(), UserInterface.separator + UserInterface.line);

        System.out.println(formattedString);
    }

    @Override
    public void execute() {

    }

}
