import task.*;

import java.util.Scanner;
import java.util.ArrayList;

public class Duke {
    public static void printLine(){
        System.out.println("____________________________________________________________");
    }
    public static void main(String[] args) {
        //Display initial greeting message
        printLine();
        System.out.println("Hello! I'm Colin");
        System.out.println("What can I do for you?");
        printLine();

        //Create Scanner object to read user input
        Scanner scanner = new Scanner(System.in);
        String userInput;
        ArrayList<Task> tasks = new ArrayList<>(); // Array to store tasks
        int taskIndex;

        //Continue reading user input, detecting any keywords and executing the respective commands
        while (true) {
            userInput = scanner.nextLine();

            printLine();
            try {
                if (userInput.equalsIgnoreCase("bye")) {
                    //Exit bot
                    System.out.println("Bye. Hope to see you again soon!");
                    printLine();
                    break;

                } else if (userInput.equalsIgnoreCase("list")) {
                    //Lists out all the tasks added, else print out no tasks added
                    if (tasks.isEmpty()) {
                        System.out.println("No tasks added yet.");
                    } else {
                        System.out.println("Here are the tasks in your list:");
                        for (int i = 0; i < tasks.size(); i++) {
                            System.out.println(" " + (i + 1) + "." + tasks.get(i));
                        }
                    }

                } else if (userInput.startsWith("mark")) {
                    //Feature to mark tasks as done
                    taskIndex = DukeException.getTaskIndex(userInput.split(" ")[1], tasks);
                    tasks.get(taskIndex).markAsDone();
                    System.out.println("Nice! I've marked this task as done:");
                    System.out.println("  " + tasks.get(taskIndex));

                } else if (userInput.startsWith("unmark")) {
                    //Feature to unmark tasks as not done
                    taskIndex = DukeException.getTaskIndex(userInput.split(" ")[1], tasks);
                    tasks.get(taskIndex).markAsNotDone();
                    System.out.println("OK, I've marked this task as not done yet:");
                    System.out.println("  " + tasks.get(taskIndex));

                } else if (userInput.startsWith("todo")) {
                    //Feature to track tasks without any date/time attached to it
                    DukeException.checkDescription(userInput);
                    String description = userInput.substring(5).trim();
                    tasks.add(new ToDo(description));

                    System.out.println("Got it. I've added this task:");
                    System.out.println("  " + tasks.get(tasks.size() - 1));
                    System.out.println("Now you have " + tasks.size() + " tasks in the list.");

                } else if (userInput.startsWith("deadline")) {
                    //Feature to track tasks that need to be done before a specific date/time
                    DukeException.checkDescription(userInput);
                    String[] parts = userInput.substring(9).split("/by ");
                    if (parts.length != 2) {
                        System.out.println("OOPS!!! Please enter a valid deadline format.");
                        throw new DukeException();
                    }
                    String description = parts[0].trim();
                    String by = parts[1].trim();
                    tasks.add(new Deadline(description, by));
                    System.out.println("Got it. I've added this task:");
                    System.out.println("  " + tasks.get(tasks.size() - 1));
                    System.out.println("Now you have " + tasks.size() + " tasks in the list.");

                } else if (userInput.startsWith("event")) {
                    //Feature to track tasks that start at a specific date/time and ends at a specific date/time
                    DukeException.checkDescription(userInput);
                    String[] parts = userInput.substring(6).split("/from |/to ");
                    if (parts.length != 3) {
                        System.out.println("OOPS!!! Please enter a valid event format.");
                        throw new DukeException();
                    }
                    String description = parts[0].trim();
                    String from = parts[1].trim();
                    String to = parts[2].trim();
                    tasks.add(new Event(description, from, to));
                    System.out.println("Got it. I've added this task:");
                    System.out.println("  " + tasks.get(tasks.size() - 1));
                    System.out.println("Now you have " + tasks.size() + " tasks in the list.");

                } else if (userInput.startsWith("delete")) {
                    taskIndex = DukeException.getTaskIndex(userInput.split(" ")[1], tasks);
                    Task removedTask = tasks.remove(taskIndex);
                    System.out.println("Noted. I've removed this task:");
                    System.out.println("  " + removedTask);
                    System.out.println("Now you have " + tasks.size() + " tasks in the list.");

                } else {
                    //Handles the case if invalid input
                    System.out.println("OOPS!!! I'm sorry, but I don't know what that means :(");
                    throw new DukeException();
                }

            } catch (DukeException ignored) {

            } catch (IndexOutOfBoundsException e) {
                System.out.println("OOPS!!! Invalid input");
            }

            printLine();
        }
        // Close the Scanner
        scanner.close();
    }
}
