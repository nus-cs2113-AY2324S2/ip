package suv.Command;

import suv.Helpers.Ui;
import suv.Task.Deadline;

import static suv.Task.TaskList.tasksList;

/**
 * The AddDeadlineCommand class represents a command to add a new deadline task.
 * It takes an input string containing the description and deadline details of the task.
 */
public class AddDeadlineCommand {
    final static int DEADLINE_KEYWORD_END_INDEX = 8;
    final static String LINE = "____________________________________________________________\n";

    /**
     * Constructs a new AddDeadlineCommand object and performs the addition of the deadline task.
     *
     * @param input The input string containing the description and deadline details of the task.
     * @throws SuvException If there is an error while executing the add deadline command.
     */
    public AddDeadlineCommand(String input) throws SuvException{
        if(input.trim().length() > 8 && input.contains("/by")){
            String by = input.split("/by")[1].trim();
            String description = input.split("/by")[0].substring(DEADLINE_KEYWORD_END_INDEX).trim();

            Deadline newTask = new Deadline(description, by);
            tasksList.add(newTask);
            Ui.printAddDeadlineMessage(newTask, tasksList.size());
        } else {
            throw new SuvException(LINE +"Oh no! You are missing the suv.Task.Deadline details\n" + LINE);
        }
    }
}
