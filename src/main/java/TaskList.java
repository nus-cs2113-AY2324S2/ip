import exceptions.DuckDeleteOutofBoundsException;
import exceptions.DuckEmptyDeleteDescriptionException;
import tasks.Task;

import java.util.ArrayList;

/**
 * Represents the methods that can be executed in the application
 * Methods include: Mark and unmark, list, delete, find
 */
public class TaskList {

    private static final String LINE_SEPARATOR = "____________________________________________________________";
    private static final String REMOVE_MESSAGE= "Noted. I've removed this task:";

    /**
     * Changes specified task to marked and prints marked message
     * @param tasks arraylist of tasks
     * @param userInput user input that needs to be formatted
     * @param index number of tasks in arraylist tasks
     */
    public static void markTask(ArrayList<Task> tasks, String userInput, int index){
        String[] split = userInput.split(" ");
        int number = Integer.parseInt(split[1]);
        if (number <= index) {
            tasks.get(number - 1).setDone(true);
            System.out.println(LINE_SEPARATOR);
            System.out.println("Nice! I've marked this task as done:");
            System.out.println("[X] " + tasks.get(number - 1).getDescription());
            System.out.println(LINE_SEPARATOR + "\n");
        } else {
            System.out.println("Task does not exist yet!\n" + LINE_SEPARATOR);
        }
    }

    /**
     * Changes specified task to unmarked and prints unmarked message
     * @param tasks arraylist of tasks
     * @param userInput user input that needs to be formatted
     * @param index number of tasks in arraylist tasks
     */
    public static void unmarkTask(ArrayList<Task> tasks, String userInput, int index){
        String[] split = userInput.split(" ");
        int number = Integer.parseInt(split[1]);
        if (number <= index) {
            tasks.get(number - 1).setDone(false);
            System.out.println(LINE_SEPARATOR);
            System.out.println("OK, I've marked this task as not done yet:");
            System.out.println("[ ] " + tasks.get(number - 1).getDescription());
            System.out.println(LINE_SEPARATOR + "\n");
        } else {
            System.out.println("Task does not exist yet!\n" + LINE_SEPARATOR);
        }
    }

    /**
     * Lists out all tasks stored in arraylist tasks
     * @param tasks arraylist of tasks
     * @param index number of tasks in arraylist tasks
     */
    public static void listTasks(ArrayList<Task> tasks, int index){
        System.out.println(LINE_SEPARATOR);
        System.out.println("Here are the tasks in your list:");
        for (int i = 0; i < index; i++) {
            System.out.print(i+1 + ".");
            System.out.println(tasks.get(i));
        }
    }

    /**
     * Deletes specified task from arraylist
     * @param tasks arraylist of tasks
     * @param userInput user input that needs to be formatted
     * @param totalTasks number of tasks in arraylist tasks
     * @return totalTasks returns the number of tasks remaining in the arraylist
     * @throws DuckEmptyDeleteDescriptionException If user did not type the index to delete in the argument
     * @throws DuckDeleteOutofBoundsException If user tried to delete index that did not exist in the arraylist
     */
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

    /**
     * Finds name of task specified in the user input and
     * prints out all tasks that description matches the description in the user input
     * @param tasks arraylist of tasks
     * @param userInput user input that needs to be formatted
     */
    public static void findTask(ArrayList<Task> tasks, String userInput) {
        String search = userInput.substring(5);
        ArrayList<Task> matchingTasks = new ArrayList<>();
        for (Task task: tasks){
            if (task.getDescription().contains(search)) {
                matchingTasks.add(task);
            }
        }
        System.out.println(LINE_SEPARATOR);
        System.out.println("Here are the matching tasks in your list:\n");
        for (Task match: matchingTasks) {
            System.out.println(match);
        }
    }
}
