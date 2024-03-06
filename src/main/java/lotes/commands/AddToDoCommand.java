package lotes.commands;

import lotes.task.Task;
import lotes.task.TaskList;
import lotes.task.ToDo;
import lotes.ui.UserInterface;

public class AddToDoCommand extends Command {
    public static final String COMMAND_WORD = "todo";

    public void run(String description) {

        String toDoDescription = description.substring(5);

        Task newTask = new ToDo(toDoDescription);
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
