package suv.Helpers;

import suv.Task.Task;

import static suv.Task.TaskList.tasksList;

/**
 * The Ui class is responsible for handling the user interface interactions.
 * It contains methods for printing messages and interacting with the user.
 */
public class Ui {
    final static String LINE = "____________________________________________________________\n";

    /**
     * Prints a welcome message to greet the user.
     */
    public static void printWelcomeMessage(){
        String name = "Suv";
        System.out.println(LINE +
                " Hello! I'm " + name + "\n" +
                " What can I do for you?\n" +
                LINE
        );
    }

    /**
     * Prints a message confirming the addition of a new task.
     *
     * @param newTask The task that was added.
     * @param size The current size of the task list.
     */
    public static void printAddTodoMessage(Task newTask, int size){
        System.out.println(LINE +
                " Got it. I've added this task:\n" + "  " + newTask +
                "\n Now you have " + Integer.toString(size) +" tasks " + "in the list.\n" + LINE
        );
    }

    /**
     * Prints a message confirming the addition of a new event.
     *
     * @param newTask The event that was added.
     * @param size The current size of the task list.
     */
    public static void printAddEventMessage(Task newTask, int size){
        System.out.println(LINE +
                " Got it. I've added this task:\n" + "  " + newTask +
                "\n Now you have " + Integer.toString(size) +" tasks " + "in the list.\n" + LINE
        );
    }

    /**
     * Prints a message confirming the addition of a new deadline.
     *
     * @param newTask The deadline that was added.
     * @param size The current size of the task list.
     */
    public static void printAddDeadlineMessage(Task newTask, int size){
        System.out.println(LINE +
                " Got it. I've added this task:\n" + "  " + newTask +
                "\n Now you have " + Integer.toString(size) +" tasks " + "in the list.\n" + LINE
        );
    }

    /**
     * Prints a message confirming the marking of a task as done.
     *
     * @param description The description of the task that was marked.
     */
    public static void printMarkMessage(String description){
        System.out.println(LINE + " Nice! I've marked this task as done:\n" + "   [X] " + description + "\n" + LINE);
    }

    /**
     * Prints a message confirming the marking of a task as not done yet.
     *
     * @param description The description of the task that was unmarked.
     */
    public static void printUnmarkMessage(String description){
        System.out.println(LINE + " OK, I've marked this task as not done yet:\n" + "   [ ] " + description+ "\n" + LINE);
    }

    /**
     * Prints a message confirming the deletion of a task.
     *
     * @param currentTask The task that was deleted.
     * @param size The current size of the task list.
     */
    public static void printDeleteTodoMessage(Task currentTask, int size){
        System.out.println(LINE + " Noted. I've removed this task:\n" + " " + currentTask + "\n Now you have " + Integer.toString(size) +" tasks " + "in the list.\n" + LINE);

    }

    /**
     * Prints a farewell message to the user.
     */
    public static void printByeMessage(){
        System.out.println(LINE +" Bye. Hope to see you again soon!\n" + LINE);
    }

    /**
     * Prints the list of tasks.
     */
    public static void printList(){
        System.out.println(LINE + " Here are the tasks in your list:");
        for(int i = 0; i < tasksList.size(); i++){
            int index = i + 1;
            System.out.println(" " + index + "." + tasksList.get(i) );
        }
        System.out.println(LINE);
    }

    public static void printMatchingTasks(String word){
        System.out.println(LINE + " Here are the matching tasks in your list:");
        int matchingTasksIndex = 1;
        for(int i = 0; i < tasksList.size(); i++){
            int index = i + 1;
            if(tasksList.get(i).toString().contains(word)) {
                System.out.println(" " + matchingTasksIndex + "." + tasksList.get(i));
                matchingTasksIndex += 1;
            }
        }
        System.out.println(LINE);
    }
}
