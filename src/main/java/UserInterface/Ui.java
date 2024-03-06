package UserInterface;

import Tasks.Deadline;
import Tasks.Event;
import Tasks.Task;
import Tasks.Todo;

import java.util.ArrayList;

/**
 * The UserInterface.Ui class handles user interface-related operations for the chatbot.
 */
public class Ui {
    private static final String CHATBOT = "Spike";
    public static final String DIVIDER = "_________________________________________________";


    /**
     *  Prints the task list in the order with the Badge, Status
     *  and the Description for each task.
     */
    public void displayList(ArrayList<Task> inputList, boolean matching) {

        System.out.println(DIVIDER);
        if (matching) {
            System.out.println("Here are the matching tasks in your list:");
        } else {
            System.out.println("Here are the tasks in your list:");
        }
        for (int i = 0; i < inputList.size(); i++) {
            Task value = inputList.get(i);
            if (value == null) {
                break;
            }
            char Badge = getBadge(value);
            System.out.println((i + 1) + ".[" + Badge + "]"
                    + "[" + value.getStatusIcon() + "] " + value.description);
        }
        System.out.println(DIVIDER);
    }

    private char getBadge(Task value) {
        char Badge = ' ';
        if (value instanceof Todo) {
            Badge = ((Todo) value).getBadge();
        } else if (value instanceof Deadline) {
            Badge = ((Deadline) value).getBadge();
        } else if (value instanceof Event) {
            Badge = ((Event) value).getBadge();
        }
        return Badge;
    }


    /**
     *  Prints a message stating that the intended task has been unmarked.
     *  It also prints the structure of the updated task.
     */
    public void displayUnmarkMsg(int index, ArrayList<Task> inputList) {
        char Badge = getBadge(inputList.get(index));
        System.out.println(DIVIDER);
        System.out.println("OK, I've marked this task as not done yet:");
        System.out.println((index + 1) + ".[" + Badge + "]" + "[ ] "
                + inputList.get(index).description);
        System.out.println(DIVIDER);
    }

    /**
     *  Prints a message stating that the intended task has been marked.
     *  It also prints the structure of the updated task.
     */
    public void displayMarkMsg(int index, ArrayList<Task> inputList) {
        char Badge = getBadge(inputList.get(index));
        System.out.println(DIVIDER);
        System.out.println("Nice! I've marked this task as done:");
        System.out.println((index + 1) + ".[" + Badge + "]" + "[X] "
                + inputList.get(index).description);
        System.out.println(DIVIDER);
    }

    protected void displayByeMsg() {
        System.out.println(DIVIDER);
        System.out.println("Bye. Hope to see you again soon!");
        System.out.println(DIVIDER);
    }

    protected void displayWelcomeMsg() {
        System.out.println(DIVIDER);
        System.out.println("Hello! I'm " + CHATBOT);
        System.out.println("What can I do for you?\n");
        System.out.println(DIVIDER);
    }


    /**
     *  Prints a message stating that the intended task has been deleted.
     *  It also prints the deleted task.
     */
    public void displayDeleteMsg(Task inputObj, int arrayLength){
        char Badge = getBadge(inputObj);
        System.out.println(DIVIDER);
        System.out.println("I've removed this task:");
        System.out.println(" [" + Badge + "]" + "["+ inputObj.getStatusIcon()
                + "] " + inputObj.description);
        System.out.println("Now you have " + (arrayLength - 1) + " tasks in the list.");
        System.out.println(DIVIDER);
    }

    /**
     *  Prints acknowledgement stating that the intended task has been added to the list.
     *  It also prints the structure of the latest task.
     */
    public void displayAcknowledgement(Task inputObj, int arrayLength) {
        char Badge = getBadge(inputObj);
        System.out.println(DIVIDER);
        System.out.println("Got it. I've added this task:");
        System.out.println(" [" + Badge + "]" + "[ ] " + inputObj.description);
        System.out.println("Now you have " + arrayLength + " tasks in the list.");
        System.out.println(DIVIDER);
    }
}
