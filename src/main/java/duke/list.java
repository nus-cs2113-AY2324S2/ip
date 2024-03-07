package duke;

import java.io.IOException;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;

import static duke.print.printMessage;
import static duke.TaskList.*;

public class list {
    public static final String[] validCommands =
            {"list", "mark", "unmark", "todo", "deadline", "event", "bye", "delete"};

    public static void executeCommand(TaskList taskList, String command, String argument)
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
            taskList.printList();
            break;
        case "mark":
            taskList.markTask(argument, true);
            break;
        case "unmark":
            taskList.markTask(argument, false);
            break;
        case "todo":
            taskList.addToDo(argument);
            break;
        case "deadline":
            taskList.addDeadline(argument);
            break;
        case "event":
            taskList.addEvent(argument);
            break;
        case "delete":
            taskList.deleteTask(argument);
            break;
        }
    }

    /**
     * Creates a list that users can add tasks to, read, and mark tasks as done or undone.
     */
    public static void startList(){
        Storage storage = new Storage("src/main/java/db/tasks.txt");
        List<Task> list;

        try {
            list = storage.loadTasks();
        } catch (DukeException.DatabaseLoadException e) { // DatabaseLoadException is a terminal error
            e.printErrorMessage();
            throw new RuntimeException();
        }

        TaskList taskList = new TaskList(list);

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
            storage.saveTasks(taskList.getTaskList());
        } catch (IOException e) {
            String errorMessage = "Failed to save existing tasks to database!!\n"
                    + "GO!! GO!!";
            printMessage(errorMessage);
        }
    }
}
