package task;

import java.util.ArrayList;
import java.io.IOException;
import exception.EkudException;

public class TaskList {

    private int taskCount = 0;
    private ArrayList<Task> tasks;

    public TaskList() {
        this.tasks = new ArrayList<Task>();
    }

    public int taskCount() {
        return this.taskCount;
    }

    public void addTodo(String userInput) {
        int dividerPosition = userInput.indexOf(" ");
        try {
            if (dividerPosition == -1) {
                throw new EkudException();
            }
            else {
                int descriptionStart = dividerPosition + 1;
                tasks.add(new Todo(userInput.substring(descriptionStart)));
                System.out.println("Got it. I've added this task:");
                System.out.println(tasks.get(taskCount));
                taskCount++;
                System.out.println("Now you have " + taskCount + " tasks in the list.");

                // writeToFile(filePath, tasks);
            }
        }
        catch (EkudException error) {
            System.out.println("The description of a todo cannot be empty.");
        }
        /*
        catch (IOException error) {
            System.out.println("Something went wrong: " + error.getMessage());
        }
        */
    }
}
