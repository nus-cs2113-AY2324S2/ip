package suv.Command;

import suv.Helpers.Ui;
import suv.Task.Deadline;

import static suv.Task.TaskList.tasksList;

public class AddDeadlineCommand {
    final static int DEADLINE_KEYWORD_END_INDEX = 8;
    final static String LINE = "____________________________________________________________\n";

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
