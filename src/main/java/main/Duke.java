package main;

import java.util.Scanner;
import java.util.ArrayList;
import tasks.Task;
import tasks.Todo;
import tasks.Deadline;
import tasks.Event;


public class Duke {

    public static boolean removeElement(ArrayList<Task> list, String[] splitLine, int counter) {
        try {
            Task t = list.get(Integer.parseInt(splitLine[1]) - 1);
            list.remove(Integer.parseInt(splitLine[1]) - 1);
            System.out.println("____________________________________________________________");
            System.out.println("Noted. I've removed this task:");
            System.out.println(t);
            System.out.println("Now you have " + (counter - 1) + " tasks in the list.");

        } catch(IndexOutOfBoundsException e) {
            System.out.println("Invalid index, please try again!");
            return false;
        }
        return true;

    }

        // Function to mark or unmark tasks
    public static void userMarkOrUnmark(String command, String line, ArrayList<Task> list, int index, Task t) {

        // User enters Mark, proceed to check if index is valid. If valid, then mark the task number
        if (command.equals("mark")) {
            try {
                index = Integer.parseInt(line.substring(5));
            } catch (NumberFormatException e) {
                System.out.println("Task number is not a valid number");
                return;
            }
            t = list.get(index - 1);
            t.isDone =  true;
            System.out.println("Nice! I've marked this task as done:");
        }

        // User enters unmark, proceed to check if index is valid. If valid, then unmark the task number
        else {
            try {
                index = Integer.parseInt(line.substring(7));
            } catch (NumberFormatException e) {
                System.out.println("Task number is not a valid number");
                return;
            }
            t = list.get(index - 1);
            t.isDone =  false;
            System.out.println("OK, I've marked this task as not done yet:");
        }

        System.out.println(t);
        System.out.println("____________________________________________________________");
    }

    // Function to print the list of tasks
    public static void userList(ArrayList<Task> list) {
            for (Task task : list) {
                if (task == null) {
                    break;
                }
                System.out.println(list.indexOf(task) + 1 + ". " + task);
            }
            System.out.println("____________________________________________________________");
    }

    // Function to say bye
    public static void userBye() {
        System.out.println("Bye human. Come back soon !");
    }

    // Function to print an unknown command from user
    public static void userWrongCommand() {
        System.out.println("No suitable command found. Please try again!");
    }

    // Function to check the minimum arguments supplied for each task
    public static boolean checkMinimumArguments(String[] splitLine, int number) {
        try {
            if (splitLine.length < number) {
                throw new DukeException("Invalid Syntax! Please try again!");
            }
        } catch (DukeException e) {
            System.out.println("Minimally " + number + " arguments, please try again!");
            return true;
        }
        return false;
    }

    // Start of user input
    public static void userInput() throws DukeException {
        Scanner scanner = new Scanner(System.in);
        String line;
        ArrayList<Task> list = new ArrayList<>();
        int counter = 0;
        int index = 0;
        Task t = null;

        // Start of user input
        while (true) {
            line = scanner.nextLine().toLowerCase(); // Takes in user input
            String[] splitLine = line.split("\\s+"); // split by whitespaces
            String command = splitLine[0]; //obtain the main command from user, which is the first command

            switch (command) {
                // User wants to exit
                case "bye":
                    userBye();
                    break;

                // User wants to display the list of tasks
                case "list":
                    if (splitLine.length != 1) {
                        userWrongCommand();
                        break;
                    }
                    userList(list);
                    continue;

                // User wants to mark or unmark tasks
                case "mark":
                case "unmark":
                    userMarkOrUnmark(splitLine[0], line, list, index, t);
                    continue;

                // User wants a todo task
                case "todo":

                    //Check if there is a minimum argument of 2
                    if (checkMinimumArguments(splitLine, 2)) {
                        continue;
                    }
                    try {
                        t = new Todo(line, counter + 1);
                    } catch (RuntimeException e) {
                        System.out.println("Invalid Syntax, please try again!");
                        continue;
                    }
                    //list.add(counter, t);
                    list.add(t);
                    counter += 1;
                    System.out.println("Now you have " + counter + " tasks in the list.");
                    continue;

                // User wants a deadline task
                case "deadline":
                    if (checkMinimumArguments(splitLine, 4)) {
                        continue;
                    }
                   try {
                        t = new Deadline(line, counter + 1);
                    } catch (DukeException e) {
                        System.out.println("Invalid Syntax, please try again!");
                        continue;
                    }
                    //list.add(counter, t);
                    list.add(t);
                    counter += 1;
                    System.out.println("Now you have " + counter + " tasks in the list.");
                    continue;

                // User wants an event task
                case "event":
                    if (checkMinimumArguments(splitLine, 8)) {
                        continue;
                    }
                    try {
                        t = new Event(line, counter + 1);
                    } catch (RuntimeException e) {
                        System.out.println("Invalid Syntax, please try again!");
                        continue;
                    }
                    //list.add(counter, t);
                    list.add(t);
                    counter += 1;
                    System.out.println("Now you have " + counter + " tasks in the list.");
                    continue;

                case "delete":
                    if (removeElement(list, splitLine, counter)) {
                        counter -= 1;
                    }

                    continue;

                default:
                    System.out.println("No suitable command found. Please try again!");
                    continue;
            }
            scanner.close(); // Close scanner after usage
            return;
        }
    }

    public static void main(String[] args) throws DukeException {

        System.out.println("____________________________________________________________");
        System.out.println("Hello! I'm Bob");
        System.out.println("What can I do for you?");
        System.out.println("____________________________________________________________");

        userInput();
    }
}