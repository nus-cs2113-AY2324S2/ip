package Ruby;

import java.util.ArrayList;
import java.util.Arrays;
import Exception.*;
import Task.*;


/**
 * Manages a list of tasks for the chatbot.
 * Supports adding tasks of different types (todo, deadline, event),
 * marking tasks as done or undone, and displaying all tasks.
 */
public class TaskList {
//    public final Task[] taskList= new Task[100]; // Array to store tasks
    public final ArrayList<Task> taskList =new ArrayList<>();
    public int taskNo = 0; // Counter for the number of tasks

    /**
     * Adds a task to the task list based on user input.
     * The task can be of types: todo, deadline, event, or a general task.
     * Parses the user input to determine the task type and details.
     *
     * @param userInput The full command entered by the user to add a task.
     */
    public void addTask(String userInput) throws StringIndexOutOfBoundsException, ArrayIndexOutOfBoundsException{
        try {
            keywordCatcher(userInput);
        }catch (InvalidKeywordException e){
            print("Sorry sir. I am not intelligent enough to know what that means.");
            return;
        }catch (MissingDescriptionException e){
            print("Sorry sir. Please give me more detail to your task.");
            return;
        }
        String[] inputSplitBySlash = userInput.split(" /");
        switch (userInput.split(" ")[0]){
        case "todo":
            taskList.add(new Todo(userInput.substring(5)));
            break;
        case "deadline":
            String name = inputSplitBySlash[0].substring(9);
            String by = inputSplitBySlash[1].substring(3);
            taskList.add(new Deadline(name, by));
            break;
        case "event":
            name = inputSplitBySlash[0].substring(6);
            String from = inputSplitBySlash[1].substring(5);
            String to = inputSplitBySlash[2].substring(3);
            taskList.add(new Event(name,from, to));
            break;
        default:
            break;
        }
        taskAddMessage();
    }

    /**
     * Displays a message confirming the addition of a task.
     * Also shows the current number of tasks in the list.
     */
    private void taskAddMessage() {
        System.out.println("    " + "--------------");
        System.out.println("    Got it. I've added this task:");
        System.out.print("      ");
        taskList.get(taskList.size()-1).printTask();
//        taskNo++;
        System.out.println("    Now you have " + (taskList.size()) + " tasks in the list.");
        System.out.println("    " + "--------------");
    }

    /**
     * Marks a task as completed based on its position in the task list.
     * If the task does not exist, prints an error message.
     *
     * @param n The index of the task in the task list (0-based).
     */
    public void markTask (int n){
        taskList.get(n-1).markedTask();
    }

    /**
     * Marks a task as not completed based on its position in the task list.
     * If the task does not exist, prints an error message.
     *
     * @param n The index of the task in the task list (0-based).
     */
    public void unmarkTask (int n){
        taskList.get(n-1).unmarkedTask();
    }

    public void deleteTask (int n) throws IndexOutOfBoundsException{
        if ((n > taskList.size())|(n <= 0)){
            throw new IndexOutOfBoundsException();
        }
        System.out.println("    " + "--------------");
        System.out.println("    Got it. I've removed this task:");
        System.out.print("      ");
        taskList.get(n-1).printTask();
        taskList.remove(n-1);
        System.out.println("    Now you have " + (taskList.size()) + " tasks in the list.");
        System.out.println("    " + "--------------");
    }

    /**
     * Prints all tasks in the task list.
     * Displays a numbered list of tasks along with their completion status and details.
     */
    public void showTaskList() {
        System.out.println("    " + "--------------");
        System.out.println("    " + "Here are the tasks in your list:");
        for (int i=0; i < taskList.size(); i++){
            System.out.print("    " + (i+1) +".");
            taskList.get(i).printTask();
        }
        System.out.println("    " + "--------------");
    }

    /**
     * Prints a formatted message to the console.
     *
     * @param thingToPrint The message to be printed.
     */
    private static void print(String thingToPrint){
        System.out.println("    " + "--------------");
        System.out.println("    " + thingToPrint);
        System.out.println("    " + "--------------");
    }

    public static void keywordCatcher(String userInput) throws InvalidKeywordException, MissingDescriptionException {
        String[] taskTypeList = {"todo", "deadline", "event"};
        String[] inputBreakdown = userInput.split(" ");
        if (!Arrays.asList(taskTypeList).contains(inputBreakdown[0])){
            throw new InvalidKeywordException();
        }
        if (inputBreakdown.length<2){
            throw new MissingDescriptionException();
        }
    }
}
