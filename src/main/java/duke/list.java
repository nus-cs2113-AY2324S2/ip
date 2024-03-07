package duke;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Arrays;
import java.util.List;
import java.util.ArrayList;
import java.util.Scanner;

import static duke.print.printMessage;
import static duke.command.*;

public class list {
    public static final String[] validCommands =
            {"list", "mark", "unmark", "todo", "deadline", "event", "bye", "delete"};

    public static void executeCommand(List<Task> taskList, String command, String argument)
            throws MissingParamsException, DukeException.EndListException,
            DukeException.InvalidCommandException, DukeException.InvalidIntegerException,
            DukeException.IntegerOutOfBoundsException {
        if (command.equalsIgnoreCase("bye")) {
            throw new DukeException.EndListException();
        }

        if (Arrays.stream(validCommands).noneMatch(command::equals)){
            throw new DukeException.InvalidCommandException();
        }

        switch (command) {
        case "list":
            printList(taskList);
            break;
        case "mark":
            markTask(taskList, argument, true);
            break;
        case "unmark":
            markTask(taskList, argument, false);
            break;
        case "todo":
            addToDo(taskList, argument);
            break;
        case "deadline":
            addDeadline(taskList, argument);
            break;
        case "event":
            addEvent(taskList, argument);
            break;
        case "delete":
            deleteTask(taskList, argument);
            break;
        }
    }

    /**
     * Creates a list that users can add tasks to, read, and mark tasks as done or undone.
     */
    public static void startList(){
        Storage storage = new Storage("src/main/java/db/tasks.txt");
        List<Task> taskList;

        try {
            taskList = storage.loadTasks();
        } catch (DukeException.DatabaseLoadException e) { // DatabaseLoadException is a terminal error
            e.printErrorMessage();
            throw new RuntimeException();
        }

        String line;
        Scanner in = new Scanner(System.in);

        while(true){
            line = in.nextLine();

            String[] tokens = line.split(" ", 2);

            String command = tokens[0].toLowerCase().trim();
            String argument = "";

            // If input contains more than one word, assign remaining words to argument
            if (tokens.length > 1) {
                argument = tokens[1].trim();
            }

            try {
                executeCommand(taskList, command, argument);
            } catch (DukeException.EndListException e) {
                break;
            } catch (DukeException.InvalidCommandException e) {
                e.printErrorMessage();
            } catch (MissingParamsException e) {
                String errorMessage = "The following parameters are missing:\n"
                        + e + "\n"
                        + "YOU GOTTA SAVE ME AND MY HEART!!";
                printMessage(errorMessage);
            } catch (DukeException.InvalidIntegerException e) {
                e.printErrorMessage();
            } catch (DukeException.IntegerOutOfBoundsException e) {
                e.printErrorMessage();
            }
        }

        try {
            storage.saveTasks(taskList);
        } catch (IOException e) {
            String errorMessage = "Failed to save existing tasks to database!!\n"
                    + "GO!! GO!!";
            printMessage(errorMessage);
        }
    }
}
