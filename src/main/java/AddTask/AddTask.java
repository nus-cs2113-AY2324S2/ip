package AddTask;

import ListCommands.SamException;
import sam.task.Task;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.ArrayList;

public abstract class AddTask {

    protected ArrayList<Task> tasks;

    public String parseStringToDate(String dateString) throws SamException {
        String[] parts = dateString.split(" ");
        for(int i = 0; i < parts.length; i++) {
            try {
                LocalDate parsedDate = LocalDate.parse(parts[i]);
                parts[i] = parsedDate.format(DateTimeFormatter.ofPattern("MMM d yyyy"));
            } catch (DateTimeParseException e) {
                // If parsing fails, keep the original part as it is
            }
        }
        return String.join(" ", parts); // Return formatted date string
    }

    public AddTask(ArrayList<Task> tasks){
        this.tasks = tasks;
    }

    public void execute() throws SamException{
    }
}
