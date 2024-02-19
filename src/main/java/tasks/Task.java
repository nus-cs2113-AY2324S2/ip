package tasks;

import exceptions.DuckEmptyDeleteDescriptionException;
import exceptions.DuckDeleteOutofBoundsException;

import java.util.ArrayList;

public class Task{

    private static final String LINE_SEPARATOR = "____________________________________________________________";
    private static final String REMOVE_MESSAGE= "Noted. I've removed this task:";
    protected String description;
    protected boolean isDone;

    public Task(String description) {
        this.description = description;
        this.isDone = false;
    }

    public String getDescription() {
        return description;
    }

    public void setDone(boolean done) {
        isDone = done;
    }

    public String toString() {
        String done = "[ ] ";
        if (isDone) {
            done = "[X] ";
        }
        return done + description;
    }

    public static void markTask(ArrayList<Task> tasks, String userInput, int index){
        String[] split = userInput.split(" ");
        int number = Integer.parseInt(split[1]);
        if (number <= index) {
            tasks.get(number - 1).setDone(true);
            System.out.println(LINE_SEPARATOR);
            System.out.println("Nice! I've marked this task as done:");
            System.out.println("[X] " + tasks.get(number-1).getDescription());
            System.out.println(LINE_SEPARATOR + "\n");
        } else {
            System.out.println("Task does not exist yet!\n" + LINE_SEPARATOR);
        }
    }

    public static void unmarkTask(ArrayList<Task> tasks, String userInput, int index){
        String[] split = userInput.split(" ");
        int number = Integer.parseInt(split[1]);
        if (number <= index) {
            tasks.get(number - 1).setDone(false);
            System.out.println(LINE_SEPARATOR);
            System.out.println("OK, I've marked this task as not done yet:");
            System.out.println("[ ] " + tasks.get(number-1).getDescription());
            System.out.println(LINE_SEPARATOR + "\n");
        } else {
            System.out.println("Task does not exist yet!\n" + LINE_SEPARATOR);
        }
    }

    public static void listTasks(ArrayList<Task> tasks, int index){
        System.out.println(LINE_SEPARATOR);
        System.out.println("Here are the tasks in your list:");
        for (int i = 0; i < index; i++) {
            System.out.print(i+1 + ".");
            System.out.println(tasks.get(i));
        }
    }

    public static int deleteTask(ArrayList<Task> tasks, String userInput, int totalTasks) {
        String[] split = userInput.split(" ");
        try {
            int index = Integer.parseInt(split[1]) - 1; //index of arraylist to delete
            if (split.length != 2) {
                throw new DuckEmptyDeleteDescriptionException();
            }
            if (index + 1 > totalTasks || index == -1) {
                throw new DuckDeleteOutofBoundsException();
            }
            System.out.println(LINE_SEPARATOR);
            System.out.println(REMOVE_MESSAGE);
            System.out.println(tasks.get(index));
            tasks.remove(index);
            totalTasks--;
            System.out.println("Now you have " + totalTasks + " tasks in the list.");
            System.out.println(LINE_SEPARATOR);
        } catch(DuckEmptyDeleteDescriptionException e) {
            System.out.println("Invalid Delete input. Please type in format: delete [index]");
        } catch(DuckDeleteOutofBoundsException e) {
            System.out.println("Invalid index. There is only " + totalTasks +  " tasks. Please try again");
        } return totalTasks;
    }
}