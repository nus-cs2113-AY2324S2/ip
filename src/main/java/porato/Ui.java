package porato;

import porato.tasks.Task;
import porato.tasks.TaskList;

import java.util.ArrayList;

/**
 * Represents the user interface of the bot
 * It contains all the responses of the bot to the user when the user inputs a command
 */
public class Ui {

    /**
     * Prints a line
     */
    public void printLine(){
        System.out.println("    ____________________________________________________________");
    }

    /**
     * Prints out the commands for the chatbot
     */
    public void printCommands(){
        printLine();
        System.out.println("    These are the commands available:");
        System.out.println("    list\n    mark INDEX\n    unmark INDEX\n    todo TASK\n    deadline TASK /by DATE\n    event TASK /from START_DATE /to END_DATE\n    delete INDEX\n    help");
        printLine();
    }

    /**
     * Prints out a greeting to the user
     */
    public void printGreeting(){
        printLine();
        System.out.println("    Hello! I'm PoratoBot");
        System.out.println("    How can I assist you mortal?");
        printLine();
    }

    /**
     * Prints what task has been added
     */
    public void printAddTask(Task newTask, TaskList taskList){
        System.out.println("     Got it. I've added this task:");
        System.out.println("       " + newTask);
        System.out.println("     Now you have " + taskList.getNoOfTasks() + " tasks in the list.");
    }

    /**
     * Prints out the unmarked task
     * @param tasks the array of tasks
     * @param taskNumber the task number
     */
    public void printMarkTaskAsNotDone(ArrayList<Task> tasks, int taskNumber){
        System.out.println("     OK, I've marked this task as not done yet:");
        System.out.println("      " + tasks.get(taskNumber-1));
    }

    /**
     * Prints out the marked task
     * @param tasks The array of tasks
     * @param taskNumber The task number
     */
    public void printMarkTaskAsDone(ArrayList<Task> tasks, int taskNumber){
        System.out.println("     Nice! I've marked this task as done:");
        System.out.println("      " + tasks.get(taskNumber-1));
    }

    /**
     * Prints the removed task
     * @param tasks The array of tasks
     * @param removedTask The removed task
     */
    public void printRemoveTask(ArrayList<Task> tasks, Task removedTask){
        System.out.println("     Fine! I've removed this task:");
        System.out.println("      " + removedTask);
        System.out.println("     Now you have " + tasks.size() + " tasks in the list");
    }

    /**
     * Prints the goodbye message
     */
    public void printGoodbye(){
        printLine();
        System.out.println("     Bye. Hope to see you again soon!");
        printLine();
    }

    /**
     * Prints the duke error message
     * @param e The duke exception error
     */
    public void printPoratoError(PoratoException e){
        System.out.println(e.getMessage());
    }

    /**
     * Prints file not found error
     */
    public void printFileNotFound(){
        System.out.println("File not found");
    }

    /**
     * Prints file cant save error
     */
    public void printFileCantSave(){
        System.out.println("Something went wrong while saving file");
    }



}
