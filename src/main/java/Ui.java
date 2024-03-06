import Tasks.Deadline;
import Tasks.Event;
import Tasks.Task;
import Tasks.Todo;

import java.util.ArrayList;

public class Ui {
    private static final String CHATBOT = "Spike";
    public static final String DIVIDER = "_________________________________________________";

    protected void displayList(ArrayList<Task> inputList, boolean matching) {
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

    protected char getBadge(Task value) {
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

    protected void displayUnmarkMsg(int index, ArrayList<Task> inputList) {
        char Badge = getBadge(inputList.get(index));
        System.out.println(DIVIDER);
        System.out.println("OK, I've marked this task as not done yet:");
        System.out.println((index + 1) + ".[" + Badge + "]" + "[ ] "
                + inputList.get(index).description);
        System.out.println(DIVIDER);
    }

    protected void displayMarkMsg(int index, ArrayList<Task> inputList) {
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

    protected void displayDeleteMsg(Task inputObj, int arrayLength) {
        char Badge = getBadge(inputObj);
        System.out.println(DIVIDER);
        System.out.println("I've removed this task:");
        System.out.println(" [" + Badge + "]" + "[" + inputObj.getStatusIcon() + "] " + inputObj.description);
        System.out.println("Now you have " + (arrayLength - 1) + " tasks in the list.");
        System.out.println(DIVIDER);
    }

    protected void displayAcknowledgement(Task inputObj, int arrayLength) {
        char Badge = getBadge(inputObj);
        System.out.println(DIVIDER);
        System.out.println("Got it. I've added this task:");
        System.out.println(" [" + Badge + "]" + "[ ] " + inputObj.description);
        System.out.println("Now you have " + arrayLength + " tasks in the list.");
        System.out.println(DIVIDER);
    }
}
