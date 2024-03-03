package Xavier;

import Exceptions.InvalidInputException;
import Exceptions.NoInputException;
import Tasks.Task;

import java.util.ArrayList;
import java.util.Scanner;

public class Ui {
    public static final String LINE = "_________________________________________________________________";

    public boolean isExit = false;
    static Scanner input = new Scanner(System.in);
    public void printWelcomeMessage() {
        System.out.println(LINE);
        System.out.println("Hello! I'm " + "Xavier");
        System.out.println("What can I do for you?");
        System.out.println(LINE);
    }

    public void showLoadingError() {
        System.out.println("Unable to load file");
    }

    public void showLine() {
        System.out.println(LINE);
    }

    public void readCommand(Parser parser, TaskList taskList, Ui ui, Storage storage, String filePath) {
        String command = input.nextLine();
        String[] stringArray = command.split(" ");
        showLine();
        try {
            parser.parse(command, taskList, ui, storage, filePath);
        } catch (NoInputException e) {
            System.out.println("OOPS!!! The description of a " + stringArray[0] + " cannot be empty.");
        } catch (InvalidInputException e) {
            if (stringArray[0].equals("deadline")) {
                System.out.println("OOPS!!! " + stringArray[0] + " must contains /by");
            } else if (stringArray[0].equals("event")) {
                System.out.println("OOPS!!! " + stringArray[0] + " must contains /from and /to");
            } else {
                printErrorMessage();
            }
        }
        showLine();
    }

    public void printAddTaskMessage(TaskList taskList) {
        System.out.println("Got it. I've added this task:");
        System.out.println(taskList.getLastTask());
        System.out.println("Now you have " + taskList.getSize() + " tasks in the list.");
    }
    public void printDeleteTaskMessage(TaskList taskList, int index) {
        System.out.println("Noted. I've removed this task:");
        System.out.println(taskList.getTask(index));
        System.out.println("Now you have " + (taskList.getSize()-1) + " tasks in the list.");
    }

    public void printMarkTaskMessage(TaskList taskList, int index) {
        System.out.println("Nice! I've marked this task as done:");
        System.out.println(taskList.getTask(index));
    }

    public void printUnmarkTaskMessage(TaskList taskList, int index) {
        System.out.println("OK, I've marked this task as not yet done:");
        System.out.println(taskList.getTask(index));
    }

    private void printErrorMessage() {
        System.out.println(" OOPS!!! I'm sorry, but I don't know what that means :-(");
    }

    public void exit() {
        System.out.println("Bye. Hope to see you again soon!");
        input.close();
        isExit = true;
    }

    public void printSearchResult(ArrayList<Task> itemList) {
        TaskList taskList = new TaskList(itemList);
        System.out.println("Here are the matching tasks in your list:");
        taskList.printList();
    }

    public void list(TaskList taskList) {
        System.out.println("Here are the tasks in your list:");
        taskList.printList();
    }
}
