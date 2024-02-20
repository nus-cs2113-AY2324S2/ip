import classes.Task;
import exceptions.*;
import utils.Command.Commands;
import static utils.Command.getCommands;
import utils.constants;

import java.util.ArrayList;
import java.util.Scanner;
import static utils.TaskManager.*;

public class Asuka {

    public static void main(String[] args) throws InvalidCommandException, EmptyTaskException, TaskIndexOutOfBoundsException, EmptyIndexException {
        // Welcome message
        printWelcomeMessage();

        //Initialise
        Commands command = null;
        int taskCount = 0;
        //Task[] tasks = new Task[constants.MAX_TASKS];
        ArrayList<Task> tasks = new ArrayList<Task>();
        Scanner myObj = new Scanner(System.in);

        //Loop through different commands, until "bye" command
        while (command != Commands.bye) {

            // Read input
            String input = myObj.nextLine();
            String[] inputs = input.split(" ");

            try {
                // Determine command
                command = getCommands(input, inputs);

                // Execute command
                switch (command) {
                case mark:
                    mark(inputs, taskCount, tasks);
                    break;
                case unmark:
                    unmark(inputs, taskCount, tasks);
                    break;
                case list:
                    list(taskCount, tasks);
                    break;
                case add:
                    taskCount = add(input, tasks, taskCount);
                    break;
                case delete:
                    taskCount = delete(inputs, taskCount, tasks);
                    break;
                case bye:
                    bye();
                    myObj.close();
                    myObj = null;
                    break;
                case null, default:
                    break;
                }
            }
            catch (EmptyTaskException | InvalidCommandException | TaskIndexOutOfBoundsException | EmptyIndexException ignored) {}
        }
    }
    private static void printWelcomeMessage() {
        System.out.println(constants.BREAKLINE);
        System.out.println("Hello! I'm Asuka\nWhat can I do for you?");
        System.out.println(constants.BREAKLINE);
    }
}
