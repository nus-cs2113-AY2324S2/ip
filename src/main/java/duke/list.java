package duke;

import java.io.IOException;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;

import static duke.print.printMessage;
import static duke.TaskList.*;

public class list {
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

        Parser parser = new Parser(new TaskList(list));

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
                parser.executeCommand(command, argument);
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
            storage.saveTasks(parser.getList());
        } catch (IOException e) {
            String errorMessage = "Failed to save existing tasks to database!!\n"
                    + "GO!! GO!!";
            printMessage(errorMessage);
        }
    }
}
