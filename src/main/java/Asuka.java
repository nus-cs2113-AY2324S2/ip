import classes.Deadline;
import classes.Event;
import classes.Task;
import classes.Todo;
import exceptions.*;
import utils.Command.Commands;
import static utils.Command.getCommands;
import utils.constants;

import java.util.ArrayList;
import java.util.Scanner;
import static utils.TaskManager.*;
import java.io.File;
import java.io.FileNotFoundException;

public class Asuka {
    public static void main(String[] args) throws InvalidCommandException, EmptyTaskException, TaskIndexOutOfBoundsException, EmptyIndexException {

        // Welcome message
        printWelcomeMessage();

        //Initialise
        Commands command = null;
        int taskCount = 0;
        ArrayList<Task> tasks = new ArrayList<>();
        Scanner myObj = new Scanner(System.in);

        //Read file
        readFile(tasks, taskCount);

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

    private static void readFile(ArrayList<Task> tasks, int taskCount) {
        //local path of data file
        File f = new File("src/main/data/local-asuka.txt");

        try {
            Scanner s = new Scanner(f);
            if (s.hasNext()) {
                System.out.println("Here are the tasks in your list:");
            } else {
                System.out.println("No task in your list.");
            }
            while (s.hasNext()) {
                for (int i = 0; i < constants.MAX_TASKS; i++) {
                    String[] inputs = s.nextLine().split(" ");
                    if (inputs[0].equalsIgnoreCase("todo")) {
                        tasks.add(i, new Todo(inputs[1]));
                    } else if (inputs[0].equalsIgnoreCase("deadline")) {
                        inputs = inputs[1].split("/", 2);
                        tasks.add(i, new Deadline(inputs[0] + "(" + inputs[1] + ")"));
                    } else if (inputs[0].equalsIgnoreCase("event")){
                        inputs = inputs[1].split("/", 3);
                        tasks.add(i, new Event(inputs[0] + "(" + inputs[1] + inputs[2] + ")"));
                    } else {
                        System.out.println("Invalid task type. File might be corrupted.");
                        break;
                    }
                    taskCount++;
                    System.out.print(i + 1 + ". ");
                    tasks.get(i).printTask();
                }
            }
            System.out.println(constants.BREAKLINE);
        } catch (FileNotFoundException e) {
            System.out.println("File not found.");
        }
    }

    private static void printWelcomeMessage() {
        System.out.println(constants.BREAKLINE);
        System.out.println("Hello! I'm Asuka\nWhat can I do for you?");
        System.out.println(constants.BREAKLINE);
    }
}
