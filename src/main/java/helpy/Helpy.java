package helpy;

import helpy.exceptions.IllegalDescriptionException;
import helpy.task.Deadline;
import helpy.task.Event;
import helpy.task.Task;
import helpy.task.Todo;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;
import java.util.ArrayList;

public class Helpy {
    public static ArrayList<Task> taskList = new ArrayList<>();
    public static final String HORIZONTAL_LINE = "______________________\n";
    public static final String name =
            "░▒█░▒█░▒█▀▀▀░▒█░░░░▒█▀▀█░▒█░░▒█\n" +
            "░▒█▀▀█░▒█▀▀▀░▒█░░░░▒█▄▄█░▒▀▄▄▄▀\n" +
            "░▒█░▒█░▒█▄▄▄░▒█▄▄█░▒█░░░░░░▒█░░\n";

    public static void processData(String taskInfo) {
        String[] details = taskInfo.split(" \\| ");
        if (details[0].equals("T")) {
            try {
                Todo newTodo = new Todo("todo " + details[2]);
                if (details[1].equals("1")) {
                    newTodo.setDone(true);
                }
                taskList.add(newTodo);
            } catch (IllegalDescriptionException ignored) {}
        } else if (details[0].equals("E")) {
            try {
                Event newEvent = new Event("event " + details[2]);
                if (details[1].equals("1")) {
                    newEvent.setDone(true);
                }
                taskList.add(newEvent);
            } catch (ArrayIndexOutOfBoundsException ignored) {}
        } else if (details[0].equals("D")) {
            try {
                Deadline newDeadline = new Deadline("deadline " + details[2]);
                if (details[1].equals("1")) {
                    newDeadline.setDone(true);
                }
                taskList.add(newDeadline);
            } catch (ArrayIndexOutOfBoundsException ignored) {}
        }
    }

    public static void loadTasks(String filePath) throws FileNotFoundException {
        File savedTasks = new File(filePath);
        Scanner scanner = new Scanner(savedTasks);
        while (scanner.hasNext()) {
            String taskInfo = scanner.nextLine();
            processData(taskInfo);
        }
    }

    public static void greetUser(boolean isNewUser) {
        String message = isNewUser ? "Greetings, I am\n" + name + "\nHow can I help you?\n" :
                "Good to see you again! I am\n" + name + "\nHow can I help you?";
        printMessage(message);
    }

    public static void listTasks(ArrayList<Task> taskList) {
        int label = 1;

        System.out.print(HORIZONTAL_LINE);
        System.out.println("These are the tasks in your list:");
        for (Task task : taskList) {
            System.out.print(label + ".");
            System.out.println(task);
            label++;
        }
        System.out.println(HORIZONTAL_LINE);
    }

    public static void printMessage(String message) {
        System.out.print(HORIZONTAL_LINE);
        System.out.println(message);
        System.out.println(HORIZONTAL_LINE);
    }

    public static void markTask(Task task, String commandStartsWith) {

        System.out.print(HORIZONTAL_LINE);

        if (commandStartsWith.equals("mark")) {
            task.setDone(true);
            System.out.println("Good job! I've marked this task as done:");
        } else {
            task.setDone(false);
            System.out.println("Ok, this task has been marked as not done yet:");
        }

        System.out.println("\t" + task);
        System.out.println(HORIZONTAL_LINE);
    }

    public static void addMessage(ArrayList<Task> taskList) {
        int numOfTasks = taskList.toArray().length;
        Task theTask = taskList.get(numOfTasks - 1);
        System.out.print(HORIZONTAL_LINE);
        System.out.println("Ok I just added: ");
        System.out.println("\t" + theTask);

        if (numOfTasks == 1) {
            System.out.println("There is 1 task in the list.");
        } else {
            System.out.println("There are " + numOfTasks
                    + " tasks in the list.");
        }
        System.out.println(HORIZONTAL_LINE);
    }

    public static void processCommand(String command, ArrayList<Task> taskList) {

        if (command.startsWith("mark") || command.startsWith("unmark")) {
            String taskNum;
            String commandStartsWith;
            if (command.startsWith("mark")) {
                taskNum = command.replace("mark", "");
                commandStartsWith = "mark";
            } else {
                taskNum = command.replace("unmark", "");
                commandStartsWith = "unmark";
            }
            taskNum = taskNum.trim();
            try {
                int taskIndex = Integer.parseInt(taskNum) - 1;
                markTask(taskList.get(taskIndex), commandStartsWith);
            } catch (NumberFormatException e) {
                printMessage("Task number provided is invalid! " +
                        "Did you enter wrongly? You typed: " + command);
            } catch (IndexOutOfBoundsException e) {
                printMessage("Task number doesn't exist! " +
                        "Did you enter wrongly? You typed: " + command);
            }
            return;
        }

        if (command.startsWith("todo")) {
            try {
                Todo newTodo = new Todo(command);
                taskList.add(newTodo);
            } catch (IllegalDescriptionException e) {
                printMessage("Your todo description is empty!");
                return;
            }
        } else if (command.startsWith("deadline")) {
            try {
                Deadline newDeadline = new Deadline(command);
                taskList.add(newDeadline);
            } catch (ArrayIndexOutOfBoundsException e) {
                printMessage("Invalid format for deadline! Make sure it follows: " +
                        "deadline <description> /by <date>");
                return;
            }
        } else if (command.startsWith("event")) {
            try {
                Event newEvent = new Event(command);
                taskList.add(newEvent);
            } catch (ArrayIndexOutOfBoundsException e) {
                printMessage("Invalid format for event! Make sure it's in this format: " +
                        "event <description> /from <start date> /to <end date>");
                return;
            }
        } else {
            printMessage("I don't understand the command \"" + command
                    + "\". Can you check that you typed correctly?");
            return;
        }
        addMessage(taskList);
    }

    public static void main(String[] args) {
        boolean isNewUser = false;
        String filePath = "data/helpy.txt";
        try {
            loadTasks(filePath);
        } catch (FileNotFoundException e) {
            isNewUser = true;
        }
        greetUser(isNewUser);

        Scanner in = new Scanner(System.in);
        String command = "";

        while (!command.equals("bye")) {
            command = in.nextLine();
            switch (command) {
            case "bye":
                printMessage("Goodbye, see you next time!");
                break;
            case "list":
                listTasks(taskList);
                break;
            default:
                processCommand(command, taskList);
                break;
            }
        }
    }
}
