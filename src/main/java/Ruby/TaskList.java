package Ruby;

import java.time.DateTimeException;
import java.time.LocalDate;
import java.util.ArrayList;
import Exception.*;
import Task.*;


/**
 * Manages a list of tasks for the chatbot.
 * Supports adding tasks of different types (todo, deadline, event),
 * marking tasks as done or undone, and displaying all tasks.
 */
public class TaskList {
    private ArrayList<Task> taskList;


    /**
     * Adds a task to the task list based on user input.
     * The task can be of types: todo, deadline, event, or a general task.
     * Parses the user input to determine the task type and details.
     *
     * @param userInput The full command entered by the user to add a task.
     */
    public void addTask(String userInput){
        try {
            detailCatcher(userInput);
        }catch (MissingDescriptionException e){
            print("Ruby requires more details about your task.");
            return;
        }

        String[] inputSplitBySlash = userInput.split(" /");

        switch (userInput.split(" ")[0].toLowerCase()){
        case "todo":
            taskList.add(new Todo(userInput.substring(5), false));
            printTaskAddMessage();
            break;
        case "deadline":
            addDeadline(inputSplitBySlash);
            break;
        case "event":
            addEvent(inputSplitBySlash);
            break;
        default:
            break;
        }
    }

    private void addEvent(String[] inputSplitBySlash) {
        String name = inputSplitBySlash[0].substring(6);
        String fromString = inputSplitBySlash[1].substring(5);
        String toString = inputSplitBySlash[2].substring(3);
        try {
            LocalDate from = LocalDate.parse(fromString);
            LocalDate to = LocalDate.parse(toString);
            taskList.add(new Event(name,false, from, to));
            printTaskAddMessage();
        }catch (DateTimeException e){
            print("Sorry, the date you should input as format: yyyy-mm-dd.");
        }
    }

    private void addDeadline(String[] inputSplitBySlash) {
        String name = inputSplitBySlash[0].substring(9);
        String byString = inputSplitBySlash[1].substring(3);
        try {
            LocalDate by = LocalDate.parse(byString);
            taskList.add(new Deadline(name, false,by));
            printTaskAddMessage();
        }catch (DateTimeException e){
            print("Sorry, the date you should input as format: yyyy-mm-dd.");
        }
    }

    /**
     * Displays a message confirming the addition of a task.
     * Also shows the current number of tasks in the list.
     */
    private void printTaskAddMessage() {
        System.out.println("    " + "-----RUBY-----");
        System.out.println("    Got it. I've added this task:");
        System.out.print("      ");
        taskList.get(taskList.size()-1).printTask();
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
        taskList.get(n-1).markTaskAsComplete();
    }

    /**
     * Marks a task as not completed based on its position in the task list.
     * If the task does not exist, prints an error message.
     *
     * @param n The index of the task in the task list (0-based).
     */
    public void unmarkTask (int n){
        taskList.get(n-1).markTaskAsIncomplete();
    }

    public void deleteTask (int n) throws IndexOutOfBoundsException{
        if ((n > taskList.size())|(n <= 0)){
            throw new IndexOutOfBoundsException();
        }
        System.out.println("    " + "-----RUBY-----");
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
        System.out.println("    " + "-----RUBY-----");
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
        System.out.println("    " + "---REMINDER---");
        System.out.println("    " + thingToPrint);
        System.out.println("    " + "--------------");
    }

    public static void detailCatcher(String userInput) throws MissingDescriptionException {
        String[] inputBreakdown = userInput.split(" ");
        if (inputBreakdown.length<2){
            throw new MissingDescriptionException();
        }
    }

    public ArrayList<Task> getTaskList() {
        return taskList;
    }

    public void setTaskList(ArrayList<Task> taskList) {
        this.taskList = taskList;
        showTaskList();
    }
}
