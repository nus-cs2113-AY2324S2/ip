import java.util.Scanner;
public class Mona {

    // These are the prefix strings to define the various data types of a command parameter.
    private static final String DEADLINE_PREFIX = "/by";
    private static final String FROM_DATE_PREFIX = "/from";
    private static final String TO_DATE_PREFIX = "/to";


    /* We use a String array (of 4 elements) to store details of a command.
     * The constants given below are the indexes for the different data elements of a command.
     * For example, the task type is stored as the 0th element in the String array.
     */
    private static final int INDEX_TASK_TYPE = 0;
    private static final int INDEX_DESCRIPTION = 1;
    private static final int INDEX_DEADLINE = 2;
    private static final int INDEX_FROM_DATE = 2;
    private static final int INDEX_TO_DATE = 3;


    public static void extractTaskTypeFromString(String[] details, String line) {
        details[INDEX_TASK_TYPE] = line.split(" ")[0].trim();
    }
    public static void extractDetailsFromTodoString(String[] details, String line) {
        int descriptionIndex = line.indexOf(" ");

        details[INDEX_DESCRIPTION] = line.substring(descriptionIndex).trim();
    }
    public static void extractDetailsFromDeadlineString(String[] details, String line) {
        int descriptionIndex = line.indexOf(" ");
        int deadlineIndex = line.indexOf(DEADLINE_PREFIX);

        details[INDEX_DESCRIPTION] = line.substring(descriptionIndex, deadlineIndex).trim();
        details[INDEX_DEADLINE] = line.substring(deadlineIndex + DEADLINE_PREFIX.length()).trim();
    }
    public static void extractDetailsFromEventString(String[] details, String line) {
        int descriptionIndex = line.indexOf(" ");
        int fromIndex = line.indexOf(FROM_DATE_PREFIX);
        int toIndex = line.indexOf(TO_DATE_PREFIX);

        details[INDEX_DESCRIPTION] = line.substring(descriptionIndex, fromIndex).trim();
        details[INDEX_FROM_DATE] = line.substring(fromIndex + FROM_DATE_PREFIX.length(), toIndex).trim();
        details[INDEX_TO_DATE] = line.substring(toIndex + TO_DATE_PREFIX.length()).trim();
    }

    public static void addTask(String description, Task[] tasks) {
        String[] commandDetails = new String[4];

        extractTaskTypeFromString(commandDetails, description);
        String taskType = commandDetails[INDEX_TASK_TYPE];
        int index = Task.noOfTasks;

        switch(taskType) {
        case("todo"):
            extractDetailsFromTodoString(commandDetails, description);
            tasks[index] = new Todo(commandDetails[INDEX_DESCRIPTION]);
            break;
        case("deadline"):
            extractDetailsFromDeadlineString(commandDetails, description);
            tasks[index] = new Deadline(commandDetails[INDEX_DESCRIPTION], commandDetails[INDEX_DEADLINE]);
            break;
        case("event"):
            extractDetailsFromEventString(commandDetails, description);
            tasks[index] = new Event(commandDetails[INDEX_DESCRIPTION], commandDetails[INDEX_FROM_DATE],
                    commandDetails[INDEX_TO_DATE]);
            break;
        }
        printHorizontalLine();
        System.out.println("Got it. I've added this task: " + System.lineSeparator() + tasks[index]);
        printHorizontalLine();
    }
    public static void printUnmarkStatement(int index, Task[] tasks) {
        printHorizontalLine();

        System.out.println("OK,  I've marked this as not done yet:");
        System.out.println(tasks[index]);

        printHorizontalLine();
    }
    public static void printMarkStatement(int index, Task[] tasks) {
        printHorizontalLine();

        System.out.println("Nice! I've marked this as done:");
        System.out.println(tasks[index]);

        printHorizontalLine();
    }
    public static void printList(Task[] tasks) {
        printHorizontalLine();

        int index = 1;
        for (Task task: tasks) {
            if (task != null) {
                System.out.println(Integer.toString(index) + "." + task);
                index += 1;
            }
        }

        printHorizontalLine();
    }
    public static void printHorizontalLine() {
        for (int i = 0; i < 59; i++) {
            System.out.print("_");
        }
        System.out.println("_");
    }
    public static void greet() {
        printHorizontalLine();

        System.out.println("Hello! I'm Mona");
        System.out.println("What can I do for you?");

        printHorizontalLine();
    }
    public static void exit() {
        printHorizontalLine();

        System.out.println("Bye. Hope to see you again soon!");

        printHorizontalLine();
    }
    public static void main(String[] args) {
        String logo = "___  ___                  \n"
                + "|  \\/  |                  \n"
                + "| .  . | ___  _ __   __ _ \n"
                + "| |\\/| |/ _ \\| '_ \\ / _` |\n"
                + "| |  | | (_) | | | | (_| |\n"
                + "\\_|  |_/\\___/|_| |_|\\__,_|\n";
        System.out.println("Hello from\n" + logo);

        greet();

        Task[] tasks = new Task[100];        //initialize an array of Tasks, to act as a list

        Scanner in = new Scanner(System.in);
        String line = in.nextLine();         // read in 1st command from user

        while (true) {
            switch(line){
            case ("list"):
                printList(tasks);
                break;
            case ("bye"):
                exit();
                return;
            default:
                if (line.startsWith("mark")) {
                    int i = Integer.parseInt(line.substring(5));
                    tasks[i - 1].markAsDone();
                    printMarkStatement(i - 1, tasks);
                }
                else if (line.startsWith("unmark")) {
                    int i = Integer.parseInt(line.substring(7));
                    tasks[i - 1].markAsNotDone();
                    printUnmarkStatement(i - 1, tasks);
                } else {
                    addTask(line, tasks); // if command cannot be extracted, add text to list
                }
            }
            line = in.nextLine();
        }
    }
}
