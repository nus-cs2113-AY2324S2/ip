import classes.Task;
import exceptions.*;
import utils.Command.Commands;
import static utils.Command.getCommands;
import utils.Storage;
import utils.Ui;
import utils.constants;
import java.util.ArrayList;
import java.util.Scanner;
import static utils.TaskManager.*;

public class Asuka {

    public static void main(String[] args) {

        // Welcome message
        Ui.printWelcomeMessage();

        //Initialise
        Commands command = null;
        ArrayList<Task> tasks = new ArrayList<>(constants.MAX_TASKS);
        int taskCount = 0;
        Scanner myObj = new Scanner(System.in);
        String currentDir = System.getProperty("user.dir") ;

        //Read file
        taskCount = Storage.readFile(tasks, taskCount, currentDir);

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
            catch (EmptyTaskException | InvalidCommandException | EmptyIndexException | TaskIndexOutOfBoundsException e) {
                Ui.printError(e.getMessage());
            }
        }

        //Write file
        Storage.writeFile(tasks, taskCount, currentDir);
    }
}