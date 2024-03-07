package lovie.ui;

import lovie.task.Task;

import java.util.ArrayList;

public class Ui {

    public Ui() {}
    String LOGO = "██╗░░░░░░█████╗░██╗░░░██╗██╗███████╗ \n" +
            "██║░░░░░██╔══██╗██║░░░██║██║██╔════╝ \n" +
            "██║░░░░░██║░░██║╚██╗░██╔╝██║█████╗░░ \n" +
            "██║░░░░░██║░░██║░╚████╔╝░██║██╔══╝░░ \n" +
            "███████╗╚█████╔╝░░╚██╔╝░░██║███████╗ \n" +
            "╚══════╝░╚════╝░░░░╚═╝░░░╚═╝╚══════╝ \n";

    public static void print(String line) {
        System.out.println("~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~");
        System.out.println(line);
        System.out.println("~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~");
    }
    public void introductionPrinter() {
        print(LOGO + "Hey hey! My name is Lovie! How can I help you today?");
    }

    public void goodbyePrinter() {
        print("Thanks for using me! See you next time ♡〜٩( ˃▿˂ )۶〜♡");
    }

    public void noValidNumberPrinter(String input) {
        print("Sorry, there is no record of a task number " + input.split(" ")[1] +
                "\n Can I help you with anything else?");
    }

    public void unmarkTaskPrinter(Task selectedTask) {
        print("Okay, no worries. I've unmarked this task for you: \n" + "[" + selectedTask.getTaskIcon() + "] [" +
                selectedTask.getStatusIcon() + "] " + selectedTask.getDescription() +
                selectedTask.getTimespan() + "\n" + "What else can I do for you today?");
    }

    public void deleteTaskPrinter(Task selectedTask, ArrayList<Task> tasksList) {
        print("Okay, no worries. I've deleted this task for you: \n" + "[" + selectedTask.getTaskIcon() + "] [" +
                selectedTask.getStatusIcon() + "] " + selectedTask.getDescription() +
                selectedTask.getTimespan() + "\n" + "You now have " + tasksList.size() + " tasks in the list.\n" +
                "What else can I do for you today?");
    }

    public void invalidCommandPrinter() {
        print("Sorry, I can't understand what you are saying.\n" +
                "Please try again! Your command options are as follows: \n\n" +
                "(1) To add a todo: todo **description** \n(2) To add an event: event **description** /from **start**" +
                "/to **end**\n(3) To add a deadline: deadline **description** /by **end**\n(4) To list all tasks: list\n"
                + "(5) To mark a task as done: mark **task number**\n(6) To unmark a task as not done: unmark " +
                "**task number**\n(7) To delete a task: delete **task number**\n(8) To leave our session: bye"
        );
    }

    public void addTaskPrinter(Task newTask) {
        print("How cutesie! Of course I'll add that task for you.\nAdded: [" + newTask.getTaskIcon() + "] [ ] " + newTask.getDescription() +
                newTask.getTimespan());
    }

    public void markTaskPrinter(Task selectedTask) {
        print("Woo hoo! Good job cutie. Way to go. I've marked this task as done: \n" + "[" + selectedTask.getTaskIcon() + "] " +
                "[" + selectedTask.getStatusIcon() + "] " + selectedTask.getDescription() +
                selectedTask.getTimespan() + "\n" + "What else can I do for you today?");
    }

    public void emptyListPrinter() {
        print("Your list is empty right now. \nTry adding a task using one of the following " +
                "command formats: \n\n" + "(1) todo **description** \n" + "(2) event **description** " +
                "/from **start** /to **end**\n" + "(3) deadline **description** /by **end**");
    }

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
}
