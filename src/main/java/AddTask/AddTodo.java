package AddTask;

import ListCommands.SamException;
import sam.task.Task;
import sam.task.Todo;

import java.util.ArrayList;

public class AddTodo extends AddTask{

    public AddTodo (ArrayList<Task> tasks){
        super(tasks);
    }

    public void execute(String description) throws SamException{
        description = description.trim(); // Remove leading and trailing whitespaces
        if (description.isEmpty()) {
            throw new SamException("You forgot to add a description for this task."); // Throw exception if description is empty
        }
        tasks.add(new Todo(description)); // Add todo task to the task list
        System.out.println("Got it. I've added this task:\n" + tasks.get(tasks.size() - 1)); // Print confirmation message
    }
}
