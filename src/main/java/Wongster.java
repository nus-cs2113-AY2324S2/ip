import java.util.Scanner;
import java.util.ArrayList;
import java.io.IOException;

public class Wongster {
    public static void main(String[] args) {
        String name = "Wongster";
        System.out.println("Hello! I'm " + name + "\nWhat can I do for you?\n");
        echo();
    }

    public static void echo() {
        ArrayList<Task> userList = new ArrayList<>();
        int userListItems = 0;
        Scanner scanner = new Scanner(System.in);

        try {
            Storage.loadTasks(userList);
        } catch (IOException e) {
            System.out.println("ERROR OCCURRED.");
        }

        while (true) {
            String userInput = scanner.nextLine();

            if (userInput.equalsIgnoreCase("bye")) {
                System.out.println("Bye. Hope to see  you again soon!");
                try {
                    Storage.saveTasks(userList, userList.size());
                } catch (IOException e) {
                    System.out.println("ERROR SAVING FILE.");
                }
                break;
            }
            if (userInput.startsWith("mark")) {
                int index = Integer.parseInt(userInput.substring(5)) - 1;
                userList.get(index).markList();
                System.out.println("Nice! I've marked this task as done: ");
                System.out.println(userList.get(index).getStatusIcon() + " " + userList.get(index).getDescription());
            } else if (userInput.startsWith("unmark")) {
                int index = Integer.parseInt(userInput.substring(7)) - 1;
                userList.get(index).unmarkList();
                System.out.println("OK, I've marked this task as not done yet: ");
                System.out.println(userList.get(index).getStatusIcon() + " " + userList.get(index).getDescription());
            } else if (userInput.equalsIgnoreCase("list")) {
                if (userListItems == 0) {
                    System.out.println("There are no tasks in your list. Please input at least one!");
                } else {
                    System.out.println("Here are tasks in your list: \n");
                    for (int i = 0; i < userListItems; i++) {
                        System.out.println((i + 1) + "." + userList.get(i));
                    }
                }
            } else if(userInput.startsWith("todo")) {
                if(userInput.trim().length() > 4) {
                    String description = userInput.substring(5).trim();
                    userList.add(new ToDo(description));
                    userListItems++;
                    System.out.println("Got it. I've added this task:");
                    System.out.println(userList.get(userListItems - 1));
                    System.out.println("Now you have " + userListItems + " tasks in the list.");
                } else {
                    System.out.println("Please input a description for this todo task!");
                }
            } else if(userInput.startsWith("deadline")) {
                if(userInput.trim().length() > 8) {
                    String[] parts = userInput.substring(9).split("/by");
                    String description = parts[0].trim();
                    String by = parts[1].trim();
                    userList.add(new Deadline(description, by));
                    userListItems++;
                    System.out.println("Got it. I've added this task:");
                    System.out.println(userList.get(userListItems - 1));
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
                    userList.add(new Event(description, from, to));
                    userListItems++;
                    System.out.println("Got it. I've added this task:");
                    System.out.println(userList.get(userListItems - 1));
                    System.out.println("Now you have " + userListItems + " tasks in the list.");
                } else {
                    System.out.println("Please input a description for this event task!");
                }
            } else if(userInput.startsWith("delete")) {
                int n = Integer.parseInt(userInput.split(" ")[1]);
                Task currentTask = userList.get(n - 1);
                ArrayList<Task> newTasks = new ArrayList<>();
                for(int i = 0; i < userListItems; i++){
                    if(i != n - 1) {
                        newTasks.add(userList.get(i));
                    }
                }
                userListItems--;
                userList = newTasks;
                System.out.println("Noted. I've removed this task:\n" + " " + currentTask +
                        "\nNow you have " + userListItems +" tasks " + "in the list.\n");
            } else {
                System.out.println("Please input a a proper task!");
                System.out.println("Help: Tasks start with todo, deadline or event.");
            }
        }
        scanner.close();
    }
}

