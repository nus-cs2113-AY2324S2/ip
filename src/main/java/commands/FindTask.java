package commands;

import Tasks.Task;
import Tasks.TaskList;
import Exceptions.ThawException;

import java.util.ArrayList;

public class FindTask extends Command {
    public FindTask(String usersInput, ArrayList<Task> tasks)  throws ThawException {
        TaskList foundTasks = new TaskList();
        if (commandWithoutDescription(usersInput)) {
            throw new ThawException("Empty command " + usersInput);
        } else if (!commandWithoutDescription(usersInput)){
            ui.printFoundCommandAcknowledgementMessage();
            for (Task task : tasks) {
                if (task.getDescription().contains(usersInput.substring(5))) {
                    foundTasks.addTask(task);
                }
            }
            foundTasks.printTask();
        }
    }
}
