package AddTask;

import ListCommands.SamException;
import sam.task.Deadline;
import sam.task.Task;

import java.util.ArrayList;

public class AddDeadline extends AddTask{

    public AddDeadline (ArrayList<Task> tasks){
        super(tasks);
    }

    public void execute(String description) throws SamException{
        // Split the description into parts
        String[] deadlineParts = description.split("/by");
        if (deadlineParts.length < 2) {
            throw new SamException("You messed up the deadline format: deadline <description> /by <date>"); // Throw exception if deadline format is incorrect
        }
        deadlineParts[0] = deadlineParts[0].trim(); // Remove leading and trailing whitespaces
        deadlineParts[1] = deadlineParts[1].trim(); // Remove leading and trailing whitespaces
        if (deadlineParts[0].equals("")) {
            throw new SamException("You forgot to add a description for this task."); // Throw exception if description is empty
        }
        if (deadlineParts[1].equals("")) {
            throw new SamException("You forgot to add a deadline for this task."); // Throw exception if deadline is empty
        }
        deadlineParts[1] = parseStringToDate(deadlineParts[1]); // Parse date string to desired format
        tasks.add(new Deadline(deadlineParts[0], deadlineParts[1])); // Add deadline task to the task list
        System.out.println("Got it. I've added this task:\n" + tasks.get(tasks.size() - 1)); // Print confirmation message
    }
}
