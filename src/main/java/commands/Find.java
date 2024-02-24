package commands;

import Tasks.Task;
import Tasks.TaskList;
import Exceptions.ThawException;
import PrintMessages.UI;

import java.util.ArrayList;

public class Find extends Command {
    public static void find(String usersInput, ArrayList<Task> tasks)  throws ThawException {
        TaskList foundTasks = new TaskList();
        if (!commandWithoutDescription(usersInput)) {
            UI.printFoundCommandAcknowledgementMessage();
            for (Task task : tasks) {
                if (task.getDescription().contains(usersInput.substring(5))) {
                    foundTasks.addTask(task);
                }
            }
            foundTasks.printTask();
        } else if (commandWithoutDescription(usersInput)){
            throw new ThawException("Empty command " + usersInput);
        }
    }
}
