package lotes.commands;

import lotes.task.Event;
import lotes.task.Task;
import lotes.task.TaskList;
import lotes.ui.UserInterface;

public class AddEventCommand extends Command {
    public static final String COMMAND_WORD = "event";

    public void run(String description) {
        String deadlineDescription = description.substring(6);

        String[] descriptionArguments = deadlineDescription.split("/from");
        String[] fromAndToArguments = descriptionArguments[1].split("/to");

        String properDescription= descriptionArguments[0];
        String from = fromAndToArguments[0].trim();
        String to = fromAndToArguments[1].trim();

        String formattedDescription = String.format("%s(from: %s to: %s)",
                properDescription, from, to);

        Task newTask = new Event(formattedDescription, from, to);
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
