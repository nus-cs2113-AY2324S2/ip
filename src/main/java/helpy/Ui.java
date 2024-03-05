package helpy;

import helpy.task.Task;
import helpy.task.TaskList;

import java.util.Scanner;

public class Ui {
    public static final String HORIZONTAL_LINE = "______________________\n";
    public static final String name =
            "░▒█░▒█░▒█▀▀▀░▒█░░░░▒█▀▀█░▒█░░▒█\n" +
            "░▒█▀▀█░▒█▀▀▀░▒█░░░░▒█▄▄█░▒▀▄▄▄▀\n" +
            "░▒█░▒█░▒█▄▄▄░▒█▄▄█░▒█░░░░░░▒█░░\n";

    public void printMessage(String message) {
        System.out.print(HORIZONTAL_LINE);
        System.out.println(message);
        System.out.println(HORIZONTAL_LINE);
    }
    public void greetUser() {
        printMessage("Greetings, I am\n" + name + "\nHow can I help you?\n");
    }

    public String readCommand() {
        Scanner input = new Scanner(System.in);
        return input.nextLine();
    }

    public void showTaskList(TaskList taskList) {
        int label = 1;

        System.out.print(HORIZONTAL_LINE);
        System.out.println("These are the tasks in your list:");
        for (Task task : taskList.getTasks()) {
            System.out.print(label + ".");
            System.out.println(task);
            label++;
        }
        System.out.println(HORIZONTAL_LINE);
    }

    public void showMarkMessage(Task task) {
        if (task.isDone()) {
            printMessage("Good job! I've marked this task as done:\n\t" + task);
        } else {
            printMessage("Ok, this task has been marked as not done yet:\n\t" + task);
        }
    }

    public void showAddMessage(Task addedTask, int numOfTasks) {
        String taskGrammar = (numOfTasks > 1) ? " are " + numOfTasks + " tasks " : " is 1 task ";
        printMessage("Ok I just added:\n\t" + addedTask + "\nThere" + taskGrammar + "in the list.");
    }

    public void showDeleteMessage(Task removedTask, int numOfTasks) {
        String remainingTasks = "";
        switch (numOfTasks) {
        case 0:
            remainingTasks = "There are no more tasks left in the list.";
            break;
        case 1:
            remainingTasks = "There is 1 task left in the list.";
            break;
        default:
            remainingTasks = "There are " + numOfTasks + " tasks left in the list.";
        }
        printMessage("Got it, I've removed this task from the list:\n" +
                "\t" + removedTask + "\n" + remainingTasks);
    }

    public void sayGoodbye() {
        printMessage("Goodbye, see you next time!");
    }

    public void showUnknownCommandErr(String fullCommand) {
        printMessage("I don't understand the command \"" + fullCommand
                + "\". Can you check that you typed correctly?");
    }

    public void showIllegalDescriptionErr() {
        printMessage("Hey your task description is empty!");
    }

    public void showIOExceptionErr() {
        printMessage("Error when attempting to write task to file");
    }

    public void showEventDescErr() {
        printMessage("Invalid format for event! Make sure it's in this format: " +
                "event <description> /from <start date> /to <end date>");
    }

    public void showDeadlineDescErr() {
        printMessage("Invalid format for deadline! Make sure it follows: " +
                "deadline <description> /by <date>");
    }

    public void showInvalidTaskNumErr() {
        printMessage("Task number provided is invalid!");
    }

    public void showAbsentTaskNumErr() {
        printMessage("Task number doesn't exist!");
    }
}
