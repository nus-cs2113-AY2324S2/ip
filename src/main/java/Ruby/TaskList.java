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
     * Adds a task based on user input.
     * The task can be of type todo, deadline, or event.
     *
     * @param userInput The command and details entered by the user.
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

    /**
     * Adds an Event task to the task list.
     * Parses start and end dates from the user input and creates an event task if the dates are valid.
     * Prints a message if the date format is incorrect.
     *
     * @param inputSplitBySlash User input split by slashes, containing task details and dates.
     */
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

    /**
     * Adds a Deadline task to the task list.
     * Parses the deadline date from the user input and creates a deadline task if the date is valid.
     * Prints a message if the date format is incorrect.
     *
     * @param inputSplitBySlash User input split by slashes, containing task details and the deadline date.
     */
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
    public void markTaskAsCompleted(int n){
        taskList.get(n-1).markTaskAsComplete();
    }

    /**
     * Marks a task as not completed based on its position in the task list.
     * If the task does not exist, prints an error message.
     *
     * @param n The index of the task in the task list (0-based).
     */
    public void markTaskAsIncompleted(int n){
        taskList.get(n-1).markTaskAsIncomplete();
    }

    /**
     * Deletes a task from the list based on its index.
     *
     * @param n The index of the task to delete, 1-based.
     * @throws IndexOutOfBoundsException If the specified index is invalid.
     */
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
     * Finds and displays tasks that match a given keyword.
     *
     * @param userInput The search command and keyword entered by the user.
     */
    public void findTask (String userInput){
        String keyword = userInput.substring(5).toLowerCase();
        System.out.println("    " + "-----RUBY-----");
        System.out.println("    " + "Here are the matching tasks in your list:");
        int count = 1;
        for (Task curTask: taskList){
            if (curTask.name.toLowerCase().contains(keyword)){
                System.out.print("      " + (count) +".");
                curTask.printTask();
                count ++;
            }
        }
        if (count == 1){
            System.out.println("    " + "No task found.");
        }
        System.out.println("    " + "--------------");
    }

    /**
     * Validates that the user input has the necessary details for a task.
     *
     * @param userInput The user's input to validate.
     * @throws MissingDescriptionException If the input does not contain necessary details.
     */
    public static void detailCatcher(String userInput) throws MissingDescriptionException {
        String[] inputBreakdown = userInput.split(" ");
        if (inputBreakdown.length<2){
            throw new MissingDescriptionException("Missing Description.");
        }
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

    public ArrayList<Task> getTaskList() {
        return taskList;
    }

    public void setTaskList(ArrayList<Task> taskList) {
        this.taskList = taskList;
        showTaskList();
    }
}
