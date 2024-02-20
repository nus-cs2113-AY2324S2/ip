import classes.Deadline;
import classes.Event;
import classes.Task;
import classes.ToDo;
import exceptions.MimiException;
import exceptions.MimiException.TaskNotFound;
import exceptions.MimiException.InsufficientParameters;
import exceptions.MimiException.IncorrectFormat;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;

public class Duke {
    final static String APP_NAME = "mimichat";
    final static int INPUT_LIMIT = 2;
    final static String FILE_PATH = "data/mimi.logs";

    static Task[] taskList = new Task[100];
    static int numberOfTask = 0;

    static boolean isRunning;

    static Scanner scanner;


    // METHODS TO GET INPUT
    private static String[] getInput() {
        String input = scanner.nextLine();
        return input.split(" ", INPUT_LIMIT);
    }

    private static void addToDo(String[] inputs) {

        try {
            if (inputs.length != 2) {
                throw new InsufficientParameters(MimiException.INSUFFICIENT_TODO_PARAMETERS);
            }
            if (inputs[1].isBlank()) {
                throw new IncorrectFormat(MimiException.INCORRECT_TODO_FORMAT);
            }

            ToDo toDo = new ToDo(inputs[1]);
            appendIntoTaskList(toDo);
            printSuccessMessage(toDo);
        } catch (InsufficientParameters | IncorrectFormat e) {
            System.out.println(e.getMessage());
        }
    }

    private static void addDeadline(String[] inputs) {

        try {
            // Throws exception if deadline parameters are incomplete
            if (inputs.length < 2) {
                throw new InsufficientParameters(MimiException.INSUFFICIENT_DEADLINE_PARAMETERS);
            }

            String[] splitInputs = inputs[1].split("/by", 2);
            Deadline deadline = Deadline.processDeadline(splitInputs);
            appendIntoTaskList(deadline);
            printSuccessMessage(deadline);
        } catch (InsufficientParameters | IncorrectFormat e) {
            System.out.println(e.getMessage());
        }
    }

    private static void addEvent(String[] inputs) {
        try {
            if (inputs.length != 2) {
                throw new InsufficientParameters(MimiException.INSUFFICIENT_EVENT_PARAMETERS);
            }

            Event event = Event.processEvent(inputs[1]);
            appendIntoTaskList(event);
            printSuccessMessage(event);
        } catch (InsufficientParameters | IncorrectFormat e) {
            System.out.println(e.getMessage());
        }

    }

    private static void appendIntoTaskList(Task newTask) {
        taskList[numberOfTask] = newTask;
        numberOfTask++;
        saveFile(taskList, numberOfTask, FILE_PATH);
    }

    public static void listTasks(Task[] list, int numberOfTasks) {
        System.out.println("-------------------------------------------");
        System.out.println("Here are the tasks in your list:");
        for (int i = 0; i < numberOfTasks; i++) {
            System.out.println(formatTask(list[i], i));
        }
        System.out.println("-------------------------------------------");
    }

    public static void markTask(Task[] list, String[] inputs) {

        try {
            int index = checkValidityOfMarks(inputs);
            list[index].markAsDone();
            System.out.println("-------------------------------------------");
            System.out.println("OK, I've marked this task as done");
            System.out.println(formatTask(list[index], index));
            System.out.println("-------------------------------------------");
        } catch (TaskNotFound | InsufficientParameters | IncorrectFormat e) {
            System.out.println(e.getMessage());
        }
    }

    private static int checkValidityOfMarks(String[] inputs) throws TaskNotFound,
            InsufficientParameters, IncorrectFormat {
        if (inputs.length != 2) {
            // Throws an error if parameters is incomplete
            throw new InsufficientParameters(MimiException.INSUFFICIENT_MARK_PARAMETERS);
        }

        try {
            int index = Integer.parseInt(inputs[1]) - 1;
            if (index < 0 || index >= numberOfTask) {
                // Throws an error if task is not found
                throw new TaskNotFound(MimiException.TASK_NOT_FOUND);
            }
            return index;
        } catch (NumberFormatException e) {
            // Throws an error if the format is incorrect
            throw new IncorrectFormat(MimiException.INCORRECT_MARK_FORMAT);
        }
    }

    public static void unmarkTask(Task[] list, String[] inputs) {
        try {
            int index = checkValidityOfMarks(inputs);
            list[index].markAsUndone();
            System.out.println("-------------------------------------------");
            System.out.println("OK, I've marked this task as not done yet");
            System.out.println(formatTask(list[index], index));
            System.out.println("-------------------------------------------");
        } catch (TaskNotFound |
                 InsufficientParameters |
                 IncorrectFormat e) {
            System.out.println(e.getMessage());
        }

    }

    // METHOD REGARDING PRINT FORMATTING
    public static void printDescription(String input) {
        System.out.println("-------------------------------------------");
        System.out.println(input);
        System.out.println("-------------------------------------------");
    }

    private static void printSuccessMessage(Task task) {
        System.out.println("-------------------------------------------");
        System.out.println("Got it. I've added this task:");
        System.out.println("\t" + formatTask(task));
        System.out.println("Now you have " + Integer.toString(numberOfTask) + " in the list");
        System.out.println("-------------------------------------------");
    }

    public static String formatTask(Task task) {
        return "\t" + task;
    }

    public static String formatTask(Task task, int index) {
        String indexNumber = Integer.toString(index + 1);
        return indexNumber + ". " + task;
    }

    // STARTUP AND SHUTDOWN SEQUENCES
    public static void startupSequence() {
        System.out.println("-------------------------------------------");
        System.out.println("Hello! I'm " + APP_NAME);
        System.out.println("What can I do for you?");
        System.out.println("-------------------------------------------");
        isRunning = true;
        scanner = new Scanner(System.in);
    }

    private static void shutdownSequence() {
        printDescription("Bye. Hope to see you again soon!");
        isRunning = false;
        scanner.close();
    }

    private static void saveFile(Task[] taskList, int numberOfTask, String filePath){
        try {
            File file = new File(filePath);
            FileWriter writer = new FileWriter(file);
            for (int i = 0; i < numberOfTask; i++) {
                writer.write(taskList[i].toFileString() + "\n");
            }
            writer.close();
        } catch (IOException e) {
            System.out.println(e.getMessage());
        }
    }


    public static void main(String[] args) {

        // Initial welcome message
        startupSequence();

        while (isRunning) {
            String[] inputs = getInput();
            switch (inputs[0]) {
            case "bye":
                shutdownSequence();
                break;
            case "list":
                listTasks(taskList, numberOfTask);
                break;
            case "mark":
                markTask(taskList, inputs);
                break;
            case "unmark":
                unmarkTask(taskList, inputs);
                break;
            case "todo":
                addToDo(inputs);
                break;
            case "deadline":
                addDeadline(inputs);
                break;
            case "event":
                addEvent(inputs);
                break;
            default:
                // raise invalid instruction
                try {
                    throw new IncorrectFormat(MimiException.INCORRECT_INSTRUCTION);
                } catch (IncorrectFormat e) {
                    System.out.println(e.getMessage());
                }
                break;
            }
        }
    }


}
