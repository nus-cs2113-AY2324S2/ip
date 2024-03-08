package lovie.ui;

import lovie.task.Task;
import lovie.task.TaskList;
import java.util.ArrayList;

public class Ui {
    /**
     * Constructor for Ui.
     */
    public Ui() {}
    private static final String LOGO = "██╗░░░░░░█████╗░██╗░░░██╗██╗███████╗ \n" +
            "██║░░░░░██╔══██╗██║░░░██║██║██╔════╝ \n" +
            "██║░░░░░██║░░██║╚██╗░██╔╝██║█████╗░░ \n" +
            "██║░░░░░██║░░██║░╚████╔╝░██║██╔══╝░░ \n" +
            "███████╗╚█████╔╝░░╚██╔╝░░██║███████╗ \n" +
            "╚══════╝░╚════╝░░░░╚═╝░░░╚═╝╚══════╝ \n";

    /**
     * Prints the line.
     *
     * @param line The line to be printed.
     */
    public void print(String line) {
        System.out.println("~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~");
        System.out.println(line);
        System.out.println("~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~");
    }

    /**
     * Prints the introduction.
     */
    public void introductionPrinter() {
        print(LOGO + "Hey hey! My name is Lovie! How can I help you today?");
    }

    /**
     * Prints the goodbye message.
     */
    public void goodbyePrinter() {
        print("Thanks for using me! See you next time ♡〜٩( ˃▿˂ )۶〜♡");
    }

    /**
     * Prints the error message if there is no valid task number.
     *
     * @param input The user's input.
     */
    public void noValidNumberPrinter(String input) {
        print("Sorry, there is no record of a task number " + input.split(" ")[1] +
                "\n Can I help you with anything else?");
    }

    /**
     * Prints the successful unmarked task message.
     *
     * @param selectedTask The task the user wanted unmarked.
     */
    public void unmarkTaskPrinter(Task selectedTask) {
        print("Okay, no worries. I've unmarked this task for you: \n" + "[" + selectedTask.getTaskIcon() + "] [" +
                selectedTask.getStatusIcon() + "] " + selectedTask.getDescription() +
                selectedTask.getTimespan() + "\n" + "What else can I do for you today?");
    }
  
    
    /**
     * Prints the error message if user does not include key word for find command.
     */
    public void noValidFindPrinter() {
        print("Oopsies. Please make sure to include a keyword while using the find command!");
    }

    /**
     * Prints the successful deleted task message.
     *
     * @param selectedTask The task the user wanted deleted.
     * @param tasksList The list of tasks.
     */
    public void deleteTaskPrinter(Task selectedTask, ArrayList<Task> tasksList) {
        print("Okay, no worries. I've deleted this task for you: \n" + "[" + selectedTask.getTaskIcon() + "] [" +
                selectedTask.getStatusIcon() + "] " + selectedTask.getDescription() +
                selectedTask.getTimespan() + "\n" + "You now have " + tasksList.size() + " tasks in the list.\n" +
                "What else can I do for you today?");
    }

    /**
     * Prints the invalid command message.
     */
    public void invalidCommandPrinter() {
        print("Sorry, I can't understand what you are saying.\n" +
                "Please try again! Your command options are as follows: \n\n" +
                "(1) To add a todo: todo **description** \n(2) To add an event: event **description** /from **start**" +
                "/to **end**\n(3) To add a deadline: deadline **description** /by **end**\n(4) To list all tasks: list\n"
                + "(5) To mark a task as done: mark **task number**\n(6) To unmark a task as not done: unmark " +
                "**task number**\n(7) To delete a task: delete **task number**\n" +
                "(7) To find tasks with a keyword: find **keyword**\n(8) To leave our session: bye"
        );
    }

    /**
     * Prints the successful added task message.
     *
     * @param newTask The task the user wanted added.
     */
    public void addTaskPrinter(Task newTask) {
        print("How cutesie! Of course I'll add that task for you.\nAdded: [" + newTask.getTaskIcon() + "] [ ] " + newTask.getDescription() +
                newTask.getTimespan());
    }

    /**
     * Prints the successful marked task message.
     *
     * @param selectedTask The task the user wanted marked.
     */
    public void markTaskPrinter(Task selectedTask) {
        print("Woo hoo! Good job cutie. Way to go. I've marked this task as done: \n" + "[" + selectedTask.getTaskIcon() + "] " +
                "[" + selectedTask.getStatusIcon() + "] " + selectedTask.getDescription() +
                selectedTask.getTimespan() + "\n" + "What else can I do for you today?");
    }

    /**
     * Prints the empty list message.
     */
    public void emptyListPrinter() {
        print("Your list is empty right now. \nTry adding a task using one of the following " +
                "command formats: \n\n" + "(1) todo **description** \n" + "(2) event **description** " +
                "/from **start** /to **end**\n" + "(3) deadline **description** /by **end**");
    }

    /**
     * Prints the list of tasks.
     *
     * @param tasksList The list of tasks.
     */
    public void listPrinter(ArrayList<Task> tasksList) {
        int counter = 0;
        String output = new String();
        while (counter < tasksList.size()) {
            String listNumber = Integer.toString(counter + 1);
            Task selected = tasksList.get(counter);
            output += listNumber + ". [" + selected.getTaskIcon() + "] [" + selected.getStatusIcon() + "] " +
                    selected.getDescription() + selected.getTimespan();
            if (counter != tasksList.size() - 1) {
                output += "\n";
            }
            counter += 1;
        }
        print(output);
    }

    public void findPrinter(TaskList miniList) {
        if (miniList.getSize() == 0) {
            print("Aw, I'm sorry. There were no tasks that contained that keyword.\nTry searching for another.");
        } else {
            int counter = 0;
            String output = new String();
            while (counter < miniList.getSize()) {
                String listNumber = Integer.toString(counter + 1);
                Task selected = miniList.get(counter);
                output += listNumber + ". [" + selected.getTaskIcon() + "] [" + selected.getStatusIcon() + "] " +
                        selected.getDescription() + selected.getTimespan();
                if (counter != miniList.getSize() - 1) {
                    output += "\n";
                }
                counter += 1;
            }
            print("Here are your matching tasks!\n" + output);
        }
    }
}
