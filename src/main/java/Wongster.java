import java.util.Scanner;

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
                if(userInput.trim().length() > 4) {
                    String description = userInput.substring(5).trim();
                    userList[userListItems] = new ToDo(description);
                    userListItems++;
                    System.out.println("Got it. I've added this task:");
                    System.out.println(userList[userListItems - 1]);
                    System.out.println("Now you have " + userListItems + " tasks in the list.");
                } else {
                    System.out.println("Please input a description for this todo task!");
                }
            } else if(userInput.startsWith("deadline")) {
                if(userInput.trim().length() > 8) {
                    String[] parts = userInput.substring(9).split("/by");
                    String description = parts[0].trim();
                    String by = parts[1].trim();
                    userList[userListItems] = new Deadline(description, by);
                    userListItems++;
                    System.out.println("Got it. I've added this task:");
                    System.out.println(userList[userListItems - 1]);
                    System.out.println("Now you have " + userListItems + " tasks in the list.");
                } else {
                    System.out.println("Please input a description for this deadline task!");
                }
            } else if(userInput.startsWith("event")) {
                if(userInput.trim().length() > 5) {
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
                    System.out.println("Please input a description for this event task!");
                }
            } else if(userInput.startsWith("delete")) {
                int n = Integer.parseInt(userInput.split(" ")[1]);
                Task currentTask = userList[n - 1];
                Task[] newTasks = new Task[100];
                int j = 0;
                for(int i = 0; i < userListItems; i++){
                    if(i != n - 1) {
                        newTasks[j++] = userList[i];
                    }
                }
                userListItems--;
                userList = newTasks;
                System.out.println("Noted. I've removed this task:\n" + " " + currentTask +
                        "\nNow you have " + Integer.toString((userListItems)) +" tasks " + "in the list.\n");
            } else {
                System.out.println("Please input a a proper task!");
                System.out.println("Help: Tasks start with todo, deadline or event.");
            }
        }
        scanner.close();
    }
}
