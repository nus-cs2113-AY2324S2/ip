package AddTask;

import ListCommands.SamException;
import sam.task.Event;
import sam.task.Task;

import java.util.ArrayList;

public class AddEvent extends AddTask{

    public AddEvent (ArrayList<Task> tasks){
        super(tasks);
    }

    public void execute(String description) throws SamException{
        // Split the description into parts
        String[] eventParts = description.split("/from|/to");
        if (eventParts.length < 3) {
            throw new SamException("You messed up the event format: event <description> /from <date> /to <date>"); // Throw exception if event format is incorrect
        }
        eventParts[0] = eventParts[0].trim(); // Remove leading and trailing whitespaces
        eventParts[1] = eventParts[1].trim(); // Remove leading and trailing whitespaces
        eventParts[2] = eventParts[2].trim(); // Remove leading and trailing whitespaces
        if (eventParts[0].equals("")) {
            throw new SamException("You forgot to add a description for this task."); // Throw exception if description is empty
        }
        if (eventParts[1].equals("")) {
            throw new SamException("You forgot to add a start date for this task."); // Throw exception if start date is empty
        }
        if (eventParts[2].equals("")) {
            throw new SamException("You forgot to add an end date for this task."); // Throw exception if end date is empty
        }

        eventParts[1] = parseStringToDate(eventParts[1]); // Parse start date string to desired format
        eventParts[2] = parseStringToDate(eventParts[2]); // Parse end date string to desired format

        tasks.add(new Event(eventParts[0], eventParts[1], eventParts[2])); // Add event task to the task list
        System.out.println("Got it. I've added this task:\n" + tasks.get(tasks.size() - 1)); // Print confirmation message
    }
}
