package ListCommands;

import sam.task.Task;

import java.util.ArrayList;

public class ListTasks extends Command{

    public ListTasks(ArrayList<Task> tasks){super(tasks);}

    public void execute(){
        // Listing tasks
        if (tasks.isEmpty()) {
            System.out.println("Your list is completely empty."); // Print message if task list is empty
            return;
        }
        System.out.println("Here are the tasks in your list:");
        for (int j = 0; j < tasks.size(); j++) {
            System.out.println((j + 1) + "." + tasks.get(j)); // Print each task with index
        }
        System.out.println("Now you have " + tasks.size() + (tasks.size() == 1 ? " task " : " tasks ") + "in the list."); // Print total number of tasks
    }
}
