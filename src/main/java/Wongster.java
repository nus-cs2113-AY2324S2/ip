import java.util.Scanner;
import java.util.Arrays;
class Task {
    protected String description;
    protected boolean isDone;
    public Task(String description) {
        this.description = description;
        this.isDone = false;
    }
    public String getStatusIcon() {
        return (isDone ? "[X]" : "[ ]");
    }

    public void markList() {
        this.isDone = true;
    }

    public void unmarkList() {
        this.isDone = false;
    }

    public String getDescription() {
        return description;
    }
}


class ToDo extends Task {
    public ToDo(String description) {
        super(description);
    }
    public String toString() {
        return "[T]" + super.getStatusIcon() + " " + super.getDescription();
    }
}

class Event extends Task {
    protected String from;
    protected String to;
    public Event(String description, String from, String to) {
        super(description);
        this.from = from;
        this.to = to;
    }
    public String toString() {
        return "[E]" + super.getStatusIcon() + " " + super.getDescription() + " (from: " + from + " to: " + to + ")";
    }
}

class Deadline extends Task {
    protected String by;
    public Deadline(String description, String by) {
        super(description);
        this.by = by;
    }
    public String toString() {
        return "[D]" + super.getStatusIcon() + " " + super.getDescription() + " (by: " + by + ")";
    }
}

public class Wongster {
    public static void main(String[] args) {
        String name = "Wongster";
        System.out.println("Hello! I'm " + name + "\nWhat can I do for you?\n");
        echo();
    }

    public static void echo() {
        Task[] userList = new Task[100];
        int userListItems = 0;
        Scanner scanner = new Scanner(System.in);
        while (true) {
            String userInput = scanner.nextLine();
            if (userInput.equalsIgnoreCase("bye")) {
                System.out.println("Bye. Hope to see  you again soon!");
                break;
            }
            if (userInput.startsWith("mark")) {
                int index = Integer.parseInt(userInput.substring(5)) - 1;
                userList[index].markList();
                System.out.println("Nice! I've marked this task as done: ");
                System.out.println(userList[index].getStatusIcon() + " " + userList[index].getDescription());
            } else if (userInput.startsWith("unmark")) {
                int index = Integer.parseInt(userInput.substring(7)) - 1;
                userList[index].unmarkList();
                System.out.println("OK, I've marked this task as not done yet: ");
                System.out.println(userList[index].getStatusIcon() + " " + userList[index].getDescription());
            } else if (userInput.equalsIgnoreCase("list")) {
                if (userListItems == 0) {
                    System.out.println("There are no tasks in your list. Please input at least one!");
                } else {
                    System.out.println("Here are tasks in your list: \n");
                    for (int i = 0; i < userListItems; i++) {
                        System.out.println((i + 1) + "." + userList[i]);
                    }
                }
            } else if(userInput.startsWith("todo")) {
                String description = userInput.substring(5).trim();
                userList[userListItems] = new ToDo(description);
                userListItems++;
                System.out.println("Got it. I've added this task:");
                System.out.println(userList[userListItems - 1]);
                System.out.println("Now you have " + userListItems + " tasks in the list.");
            } else if(userInput.startsWith("deadline")) {
                String[] parts = userInput.substring(9).split("/by");
                String description = parts[0].trim();
                String by = parts[1].trim();
                userList[userListItems] = new Deadline(description, by);
                userListItems++;
                System.out.println("Got it. I've added this task:");
                System.out.println(userList[userListItems - 1]);
                System.out.println("Now you have " + userListItems + " tasks in the list.");
            } else if(userInput.startsWith("event")) {
                String[] parts = userInput.substring(6).split("/from|/to");
                String description = parts[0].trim();
                String from = parts[1].trim();
                String to = parts[2].trim();
                userList[userListItems] = new Event(description, from, to);
                userListItems++;
                System.out.println("Got it. I've added this task:");
                System.out.println(userList[userListItems - 1]);
                System.out.println("Now you have " + userListItems + " tasks in the list.");
            } else {
                System.out.println("Invalid Command, please enter a valid command.");
            }
        }
        scanner.close();
    }
}
