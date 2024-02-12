import java.lang.reflect.Array;
import java.util.Scanner;

public class Duke {
    final static String APP_NAME = "mimichat";
    final static int INPUT_LIMIT = 2;

    static Task[] taskList = new Task[100];
    static int numberOfTask = 0;

    static boolean isRunning;

    static Scanner scanner;


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
                markTask(taskList, Integer.parseInt(inputs[1]) - 1);
                break;
            case "unmark":
                unmarkTask(taskList, Integer.parseInt(inputs[1]) - 1);
                break;
            case "todo":
                try {
                    addToDo(inputs);
                } catch (IndexOutOfBoundsException e) {
                    System.out.println(MimiException.INSUFFICIENT_INPUT_FOR_TODO);
                }
                break;
            case "deadline":
                try {
                    addDeadline(inputs);
                } catch (MimiException.InsufficientParameters | MimiException.IncorrectFormat e) {
                    System.out.println(e);
                }
                break;
            case "event":
                addEvent(inputs[1]);
                break;
            default:
                // Adds task by default and prints that a task has been added
                addTask(inputs[1]);
                break;
            }
        }
    }

    // METHODS TO GET INPUT
    private static String[] getInput() {
        String input = scanner.nextLine();
        return input.split(" ", INPUT_LIMIT);
    }

    // METHODS TO CREATE/RETRIEVE OF TASKS
    private static void addTask(String input) {
        Task newTask = new Task(input);
        appendIntoTaskList(newTask);
        printDescription("added: " + input);
    }

    private static void addToDo(String[] inputs) throws IndexOutOfBoundsException {
        if (inputs.length != 2) {
            throw new IndexOutOfBoundsException();
        }
        ToDo toDo = new ToDo(inputs[1]);
        appendIntoTaskList(toDo);
        printSuccessMessage(toDo);
    }

    private static void addDeadline(String[] inputs) throws
            MimiException.IncorrectFormat, MimiException.InsufficientParameters {

        if (inputs.length < 2) {
            // Throws an error if parameters is incomplete
            throw new MimiException.InsufficientParameters(MimiException.INSUFFICIENT_DEADLINE_PARAMETERS);
        }

        String[] splittedInputs = inputs[1].split("/by", 2);
        Deadline deadline = getDeadline(splittedInputs);
        appendIntoTaskList(deadline);
        printSuccessMessage(deadline);
    }

    private static Deadline getDeadline(String[] inputs) throws MimiException.InsufficientParameters,
            MimiException.IncorrectFormat {

        if (inputs.length < 2){
            // Throws an error if /by is missing
            throw new MimiException.IncorrectFormat(MimiException.INCORRECT_DEADLINE_FORMAT);
        }

        for (String s : inputs) {
            // Throws an error if parameters is incomplete
            if (s == null || s.isEmpty()) {
                throw new MimiException.InsufficientParameters(MimiException.INSUFFICIENT_DEADLINE_PARAMETERS);
            }
        }

        String taskName = inputs[0];
        String dueDate = inputs[1].strip();
        return new Deadline(taskName, dueDate);

    }

    private static void addEvent(String input) {

        // Further process the deadline input
        String[] inputs = input.split("/from", 2);
        String taskName = inputs[0];
        String[] duration = inputs[1].split("/to", 2);
        String startDate = duration[0].strip();
        String endDate = duration[1].strip();

        // Add into task list and print success message
        Event event = new Event(taskName, startDate, endDate);
        appendIntoTaskList(event);
        printSuccessMessage(event);

    }

    private static void appendIntoTaskList(Task newTask) {
        taskList[numberOfTask] = newTask;
        numberOfTask++;
    }

    public static void listTasks(Task[] list, int numberOfTasks) {
        System.out.println("-------------------------------------------");
        System.out.println("Here are the tasks in your list:");
        for (int i = 0; i < numberOfTasks; i++) {
            System.out.println(formatTask(list[i], i));
        }
        System.out.println("-------------------------------------------");
    }

    public static void markTask(Task[] list, int index) {
        list[index].markAsDone();
        System.out.println("-------------------------------------------");
        System.out.println("OK, I've marked this task as done");
        System.out.println(formatTask(list[index], index));
        System.out.println("-------------------------------------------");
    }

    public static void unmarkTask(Task[] list, int index) {
        list[index].markAsUndone();
        System.out.println("-------------------------------------------");
        System.out.println("OK, I've marked this task as not done yet");
        System.out.println(formatTask(list[index], index));
        System.out.println("-------------------------------------------");
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

}
