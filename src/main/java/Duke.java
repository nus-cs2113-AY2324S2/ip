import java.util.Scanner;

public class Duke {
    final static String APP_NAME = "mimichat";
    final static int INPUT_LIMIT = 2;

    static Task[] taskList = new Task[100];
    static int numberOfTask = 0;

    static boolean isRunning;

    static Scanner scanner;


    /**
     * Main method to run the program
     *
     * @param args
     */
    public static void main(String[] args) throws MimiException.IncorrectFormat, MimiException.InsufficientParameters {

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
                    throw new MimiException.IncorrectFormat(MimiException.INCORRECT_INSTRUCTION);
                } catch (MimiException.IncorrectFormat e) {
                    System.out.println(e.getMessage());
                }
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

    private static void addToDo(String[] inputs) throws
            MimiException.InsufficientParameters,
            MimiException.IncorrectFormat {

        try {
            if (inputs.length != 2) {
                throw new MimiException.InsufficientParameters(MimiException.INSUFFICIENT_TODO_PARAMETERS);
            }
            if (inputs[1].isBlank()) {
                throw new MimiException.IncorrectFormat(MimiException.INCORRECT_TODO_FORMAT);
            }

            ToDo toDo = new ToDo(inputs[1]);
            appendIntoTaskList(toDo);
            printSuccessMessage(toDo);
        } catch (MimiException.InsufficientParameters | MimiException.IncorrectFormat e) {
            System.out.println(e.getMessage());
        }
    }

    private static void addDeadline(String[] inputs) throws
            MimiException.IncorrectFormat, MimiException.InsufficientParameters {

        try {
            // Throws exception if deadline parameters are incomplete
            if (inputs.length < 2) {
                throw new MimiException.InsufficientParameters(MimiException.INSUFFICIENT_DEADLINE_PARAMETERS);
            }
            String[] splitInputs = inputs[1].split("/by", 2);
            Deadline deadline = getDeadline(splitInputs);
            appendIntoTaskList(deadline);
            printSuccessMessage(deadline);
        } catch (MimiException.InsufficientParameters | MimiException.IncorrectFormat e) {
            System.out.println(e.getMessage());
        }
    }

    private static Deadline getDeadline(String[] inputs) throws
            MimiException.InsufficientParameters,
            MimiException.IncorrectFormat {

        if (inputs.length < 2) {
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

    private static void addEvent(String[] inputs) throws
            MimiException.InsufficientParameters,
            MimiException.IncorrectFormat {
        try {
            if (inputs.length != 2) {
                throw new MimiException.InsufficientParameters(MimiException.INSUFFICIENT_EVENT_PARAMETERS);
            }

            Event event = getEvent(inputs[1]);
            appendIntoTaskList(event);
            printSuccessMessage(event);
        } catch (MimiException.InsufficientParameters | MimiException.IncorrectFormat e) {
            System.out.println(e.getMessage());
        }

    }

    private static Event getEvent(String input) throws
            MimiException.IncorrectFormat,
            MimiException.InsufficientParameters {

        // Further process the deadline input
        try {
            String[] inputs = input.split("/from", 2);
            String[] duration = inputs[1].split("/to", 2);

            // Check if the inputs are complete
            String taskName = inputs[0];
            String startDate = duration[0].strip();
            String endDate = duration[1].strip();

            // Throws an error if parameters is incomplete
            if (taskName.isBlank() || startDate.isBlank() || endDate.isBlank()) {
                throw new MimiException.IncorrectFormat(MimiException.INCORRECT_EVENT_FORMAT);
            }

            return new Event(taskName, startDate, endDate);

        } catch (ArrayIndexOutOfBoundsException e) {
            throw new MimiException.InsufficientParameters(MimiException.INSUFFICIENT_EVENT_PARAMETERS);
        }
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

    public static void markTask(Task[] list, String[] inputs) {

        try {
            int index = checkValidityOfMarks(inputs);
            list[index].markAsDone();
            System.out.println("-------------------------------------------");
            System.out.println("OK, I've marked this task as done");
            System.out.println(formatTask(list[index], index));
            System.out.println("-------------------------------------------");
        } catch (MimiException.TaskNotFound | MimiException.InsufficientParameters | MimiException.IncorrectFormat e) {
            System.out.println(e.getMessage());
        }
    }

    private static int checkValidityOfMarks(String[] inputs) throws MimiException.TaskNotFound,
            MimiException.InsufficientParameters, MimiException.IncorrectFormat {
        if (inputs.length != 2) {
            // Throws an error if parameters is incomplete
            throw new MimiException.InsufficientParameters(MimiException.INSUFFICIENT_MARK_PARAMETERS);
        }

        try {
            int index = Integer.parseInt(inputs[1]) - 1;
            if (index < 0 || index >= numberOfTask) {
                // Throws an error if task is not found
                throw new MimiException.TaskNotFound(MimiException.TASK_NOT_FOUND);
            }
            return index;
        } catch (NumberFormatException e) {
            // Throws an error if the format is incorrect
            throw new MimiException.IncorrectFormat(MimiException.INCORRECT_MARK_FORMAT);
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
        } catch (MimiException.TaskNotFound |
                 MimiException.InsufficientParameters |
                 MimiException.IncorrectFormat e) {
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

}
