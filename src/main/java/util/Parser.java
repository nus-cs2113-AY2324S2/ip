package util;

import tasks.Deadline;
import tasks.Event;
import tasks.Todo;
import java.util.Scanner;

/**
 * The class that handles user input and processes for the commands.
 */
public class Parser {

    /**
     * Takes the first word of any input as the command.
     *
     * @param input The user's line input.
     * @return Command string.
     */
    public static String getCommand(String input) {
        return input.split(" ")[0].toLowerCase();
    }

    /**
     * Obtains the non-command part of the input, which contains both
     * the description and the timings for deadline and event.
     *
     * @param input The user's line input.
     * @return Non-command string.
     */
    public static String getTask(String input) {
        String[] splitInput = input.split(" ", 2);
        return splitInput.length > 1 ? splitInput[1] : "";
    }

    /**
     * Generates a response based on the user's input.
     *
     * @param input The user's line input.
     * @param tasks The array list of tasks that will be modified
     *              based on the command.
     */
    private static void getResponse(String input, TaskList tasks) {
        String command = getCommand(input);
        String task = getTask(input);
        try {
            switch (command) {
                case "list":
                    tasks.listTasks();
                    break;
                case "mark":
                    tasks.markTask(Integer.parseInt(task));
                    break;
                case "unmark":
                    tasks.unmarkTask(Integer.parseInt(task));
                    break;
                case "todo":
                    tasks.addTask(new Todo(task.trim()));
                    break;
                case "deadline":
                    String[] deadlineSplit = task.split("/");
                    tasks.addTask(new Deadline(deadlineSplit[0].trim(),
                            deadlineSplit[1].substring(3).trim()));
                    break;
                case "event":
                    String[] eventSplit = task.split("/");
                    tasks.addTask(new Event(eventSplit[0].trim(),
                            eventSplit[1].substring(5).trim(),
                            eventSplit[2].substring(3).trim()));
                    break;
                case "find":
                    tasks.findTask(task);
                    break;
                case "delete":
                    tasks.deleteTask(Integer.parseInt(task));
                    break;
                default:
                    System.out.println("Your command is invalid, invalid. Try again.");
            }
        } catch (IndexOutOfBoundsException e) {

            Ui.missingSlashMessage();
        } catch (NumberFormatException e) {
            System.out.println("Ensure that you include a number after your delete/find/mark command.");
        }
    }

    /**
     * Takes in the user's input from the command line interface,
     * running it through the response method.
     *
     * @param tasks The array list of tasks that will be modified.
     */
    public static void getInput(TaskList tasks) {
        Scanner in = new Scanner(System.in);
        String line = in.nextLine();
        while (!line.equals("bye")) {
            getResponse(line, tasks);
            line = in.nextLine();
        }
    }
}
