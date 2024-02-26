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
import static utils.constants.BREAKLINE;

public class Asuka {
    public static void main(String[] args) throws InvalidCommandException, EmptyTaskException, TaskIndexOutOfBoundsException, EmptyIndexException {

        // Welcome message
        printWelcomeMessage();

        //Initialise
        Commands command = null;
        int taskCount = 0;


        ArrayList<Task> tasks = new ArrayList<>();
        Scanner myObj = new Scanner(System.in);

        String currentDir = System.getProperty("user.dir") ;

        //Read file
        taskCount = readFile(tasks, taskCount, currentDir);

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

        //Write file
        writeFile(tasks, taskCount, currentDir);
    }

    private static void writeFile(ArrayList<Task> tasks, int taskCount, String currentDir) {
        //local path of data file
        File f = new File(currentDir + "/local-asuka.txt");

        try {
            java.io.FileWriter writer = new java.io.FileWriter(f);
            for (int i = 0; i < taskCount; i++) {
                Task task = tasks.get(i);
                writer.write(task.getTaskType() + "|" + task.getIsDone() + "|" + task.getDescription() + "|"
                        + task.getTime() + "\n");
            }
            writer.close();
        } catch (java.io.IOException e) {
            System.out.println("An error occurred.");
        }
    }

    private static int readFile(ArrayList<Task> tasks, int taskCount, String currentDir) {
        //local path of data file
        File f = new File(currentDir + "/local-asuka.txt" );

        try {
            Scanner s = new Scanner(f);
            if (s.hasNext()) {
                System.out.println("Here are the tasks in your list:");
            } else {
                System.out.println("No task in your list.");
            }
            while (s.hasNext()) {
                //System.out.print(taskCount + 1 + ". " + s.nextLine());
                String[] inputs = s.nextLine().split("\\|", 4);
                if (inputs[0].equalsIgnoreCase("todo")) {
                    tasks.add(taskCount, new Todo(inputs[2]));
                } else if (inputs[0].equalsIgnoreCase("deadline")) {
                    tasks.add(taskCount, new Deadline(inputs[2], inputs[3]));
                } else if (inputs[0].equalsIgnoreCase("event")){
                    tasks.add(taskCount, new Event(inputs[2], (inputs[3])));
                } else {
                    System.out.println("Invalid task type. File might be corrupted.");
                    break;
                }
                if (inputs[1].equalsIgnoreCase("true")) {
                    tasks.get(taskCount).markAsDone();
                }
                System.out.print(taskCount + 1 + ". ");
                tasks.get(taskCount).printTask();
                taskCount++;
            }
            System.out.println(constants.BREAKLINE);
        } catch (FileNotFoundException e) {
            System.out.println("File not found. \nCreating new file... \nFile created.");
            try {
                f.createNewFile();
            } catch (java.io.IOException ex) {
                System.out.println("An error occurred.");
            }
            System.out.println(BREAKLINE);
        }
        return taskCount;
    }

    private static void printWelcomeMessage() {
        System.out.println(constants.BREAKLINE);
        System.out.println("Hello! I'm Asuka\nWhat can I do for you?");
        System.out.println(constants.BREAKLINE);
    }
}
