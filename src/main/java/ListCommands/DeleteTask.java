package ListCommands;

import sam.task.Task;

import java.util.ArrayList;
import java.util.Scanner;

public class DeleteTask extends Command {

    public DeleteTask(ArrayList<Task> tasks) {
        super(tasks);
    }

    public void execute(String indexStr) throws SamException{
        int index = 0;
        try {
            index = Integer.parseInt(indexStr) - 1; // Convert index string to integer
        } catch (NumberFormatException e) {
            throw new SamException("You can only delete a within bounds list index of 'all' to delete the entire list"); // Throw exception if index is invalid
        }
        indexCheck(index); // Check if index is valid
        System.out.println("Noted. I've removed this task:\n" + tasks.get(index)); // Print confirmation message
        tasks.remove(index); // Remove task from the task list
        System.out.println("Now you have " + tasks.size() + (tasks.size() == 1 ? " task " : " tasks ") + "in the list."); // Print total number of tasks
    }

    public void deleteAllTasks(){
        System.out.println("You sure you want to completely clear your task list? Enter 'y'/'Y' to confirm");
        Scanner in = new Scanner(System.in);
        String confirmation = in.nextLine();
        confirmation = confirmation.toLowerCase();
        if (confirmation.equals("y")) {
            System.out.println("Confirmed, I deleted your entire list.");
            while (!tasks.isEmpty()) {
                tasks.remove(0);
            }
            return;
        }
        System.out.println("Understood, I won't clear your list.");
    }
}
