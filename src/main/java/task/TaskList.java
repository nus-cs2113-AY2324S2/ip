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

    public void addDeadline(String userInput) {
        int dividerPosition = userInput.indexOf(" ");
        try {
            if (dividerPosition == -1) {
                throw new EkudException();
            }
            else {
                int slashPosition = userInput.indexOf("/by");
                int descriptionStart = dividerPosition + 1;
                int descriptionEnd = slashPosition - 1;
                int byStart = slashPosition + 4;
                tasks.add(new Deadline(userInput.substring(descriptionStart, descriptionEnd), userInput.substring(byStart)));
                System.out.println("Got it. I've added this task:");
                System.out.println(tasks.get(taskCount));
                taskCount++;
                System.out.println("Now you have " + taskCount + " tasks in the list.");

                // writeToFile(filePath, tasks);
            }
        }
        catch (EkudException error) {
            System.out.println("The description of a deadline cannot be empty.");
        }
        /*
        catch (IOException error) {
            System.out.println("Something went wrong: " + error.getMessage());
        }
        */
    };

    public void addEvent(String userInput) {
        int dividerPosition = userInput.indexOf(" ");
        try {
            if (dividerPosition == -1) {
                throw new EkudException();
            }
            else {
                int descriptionStart = dividerPosition + 1;
                int descriptionEnd = userInput.indexOf("/from") - 1;
                int fromStart = userInput.indexOf("/from") + 6;
                int fromEnd = userInput.indexOf("/to") - 1;
                int toStart = userInput.indexOf("/to") + 4;

                tasks.add(new Event(userInput.substring(descriptionStart, descriptionEnd), userInput.substring(fromStart, fromEnd), userInput.substring(toStart)));
                System.out.println("Got it. I've added this task:");
                System.out.println(tasks.get(taskCount));
                taskCount++;
                System.out.println("Now you have " + taskCount + " tasks in the list.");

                // writeToFile(filePath, tasks);
            }
        }
        catch (EkudException error) {
            System.out.println("The description of an event cannot be empty.");
        }
        /*
        catch (IOException error) {
            System.out.println("Something went wrong: " + error.getMessage());
        }
        */
    };

    public void list() {
        System.out.println("Here are the tasks in your list:");
        for(int i = 0; i < taskCount; i++){
            System.out.println((i + 1) + "." + tasks.get(i));
        }
    }

    public void delete(String userInput) {
        String[] userInputWords = userInput.split(" ");
        try {
            if(userInputWords.length == 1 || Integer.parseInt(userInputWords[1]) > taskCount){
                throw new EkudException();
            }
            else{
                int taskIndex = Integer.parseInt(userInputWords[1]) - 1;
                System.out.println("Noted. I've removed this task:");
                System.out.println(tasks.get(taskIndex));
                tasks.remove(taskIndex);
                taskCount--;
                System.out.println("Now you have " + taskCount + " tasks in the list.");
            }
        }
        catch(EkudException error){
            System.out.println("The task number is not valid or not provided.");
        }
    }

    public void mark(String userInput) {
        String[] userInputWords = userInput.split(" ");
        try {
            if (userInputWords.length == 1 || Integer.parseInt(userInputWords[1]) > taskCount){
                throw new EkudException();
            }
            else {
                int taskIndex = Integer.parseInt(userInputWords[1]) - 1;
                tasks.get(taskIndex).markAsDone();
                System.out.println("Nice! I've marked this task as done:");
                System.out.println(tasks.get(taskIndex));
            }
        }
        catch (EkudException error) {
            System.out.println("The task number is not valid or not provided.");
        }
    }

    public void unmark(String userInput) {
        String[] userInputWords = userInput.split(" ");
        try {
            if (userInputWords.length == 1 || Integer.parseInt(userInputWords[1]) > taskCount){
                throw new EkudException();
            }
            else {
                int taskIndex = Integer.parseInt(userInputWords[1]) - 1;
                tasks.get(taskIndex).markAsNotDone();
                System.out.println("OK, I've marked this task as not done yet:");
                System.out.println(tasks.get(taskIndex));
            }
        }
        catch (EkudException error) {
            System.out.println("The task number is not valid or not provided.");
        }
    }

}
