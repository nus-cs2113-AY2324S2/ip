package commands;
import java.io.IOException;
import java.util.ArrayList;

import Exceptions.ThawException;
import Tasks.*;

public class DeleteTask {

    public static void deleteTask(ArrayList<Task> task, String usersInput) throws ThawException, IOException {
        int taskIndex = Integer.parseInt(usersInput.substring(7)) - 1;
        System.out.println("Noted. I've removed this task:");
        System.out.println(task.get(taskIndex).getStatusIcon());
        System.out.println("Now you have " + (task.size() - 1) + " tasks in the list.");
        task.remove(taskIndex);
    }
}
