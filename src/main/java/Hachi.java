import java.util.Scanner;

/**
 * This program currently starts the chatbot with a greeting,
 * awaits user input for commands relating to a to-do task manager
 * and ends off the program with a goodbye message.
 *
 * @author clarencepohh
 * @version 06/02/2024
 */

public class Hachi {

    /**
     * Prints a greeting to the user in the console
     * with the bot's name, Hachi.
     */

    public static void printGreetingMessage() {
        String logo = "._. ._.  ._____.  ._____.  ._. ._.  ._.\n"
                + "| | | |  | ._. |  |  ___|  | | | |  | |\n"
                + "| |_| |  | |_| |  | |      | |_| |  | |\n"
                + "| ._. |  | ._. |  | |___   |  _  |  | |\n"
                + "|_| |_|  |_| |_|  |_____|  |_| |_|  |_|\n";

        System.out.println("Hey, Hachi Here!\n" + logo + "How can I assist you today?\n");
        spacerInsert("medium", false);
    }

    /**
     * Prints to the console a help message that details the commands
     * the user can use for interacting with the chatbot.
     *
     */

    private static void printHelpMessage() {
        spacerInsert("medium", true);
        System.out.println("You can use the following commands:");
        System.out.println("    'list' to retrieve your current list of tasks,");
        System.out.println("    'mark <#>' to mark task number # as complete,");
        System.out.println("    'unmark <#>' to mark task number # as incomplete,");
        System.out.println("    'todo <task name>' to create a to-do,");
        System.out.println("    'deadline <task name> /by <by date>' to create a task with a deadline,");
        System.out.println("    'event <task name> /from <start> /to <end>' to create an event with a start and end date,");
        System.out.println("    'bye' to stop chatting :('");
        System.out.println("    And if you need to see this again, type 'help'!");
        spacerInsert("medium", true);
    }

    /**
     * Prints to the console a spacer line made of tildes.
     * Function call has option to choose length of the spacer,
     * as well as whether there is a 4-space indent before the spacer.
     *
     * @param length The desired length of the spacer line. Medium is chosen by default.
     * @param hasTab Whether the spacer line has a 4-space indent.
     */

    public static void spacerInsert(String length, boolean hasTab) {
        String spacer;
        if (hasTab) {
            System.out.print("    ");
        }
        switch (length) {
        case "small": // 20 tildes
            spacer = "~~~~~~~~~~~~~~~~~~~~";
            break;
        case "medium": // 40 tildes
        default:
            spacer = "~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~";
            break;
        case "large": // 60 tildes
            spacer = "~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~";
            break;
        }
        System.out.println(spacer);
    }

    /**
     * Retrieves the current list of tasks and prints it to
     * the console for the user to see.
     *
     * @param listOfTasks The Task[] array that contains the list of tasks.
     */

    public static void retrieveList(Task[] listOfTasks) {
        int numTasks = Task.getTotalNumTasks();
        spacerInsert("medium", true);
        System.out.println("    The following are in your list:");
        for (int i = 0; i < numTasks; i++) {
            String taskType = listOfTasks[i].getTaskType();
            String statusIcon = listOfTasks[i].getStatusIcon();
            System.out.print("    " + (i + 1) + ": ");
            System.out.print("[" + taskType + "] ");
            System.out.print("[" + statusIcon + "] ");
            System.out.println(listOfTasks[i].getName());
        }
    }

    /**
     * Given a task's name and the list of tasks, add a new task into the list.
     * Depending on the user's input, can create subclass of tasks: Todos, Deadlines and Events.
     *
     * @param taskType Type of task to be added. (Todo, Event, Deadline, Task, Invalid)
     * @param listOfTasks The Task[] array that the new task will be added to.
     * @param line The line of text given by the user.
     * @param cleanInput The cleaned line of text that will be used to determine the instruction.
     */

    public static void addTask(TaskType taskType, Task[] listOfTasks, String line, String cleanInput) {
        Task toAdd;
        if (taskType == TaskType.TODO) {
            int indexOfTodo = cleanInput.indexOf("TODO") + 5;
            String name = line.substring(indexOfTodo).trim();
            toAdd = new Todo(name);
        } else if (taskType == TaskType.DEADLINE) {
            // parse deadline here
            int indexOfName = cleanInput.indexOf("DEADLINE") + 9;
            int indexOfBy = cleanInput.indexOf("/B") + 3;

            String name = line.substring(indexOfName, indexOfBy - 3).trim();
            String byDate = line.substring(indexOfBy).trim();
            toAdd = new Deadline(name, byDate);
        } else if (taskType == TaskType.EVENT) {
            // parse to and from dates here
            int indexOfName = cleanInput.indexOf("EVENT") + 6;
            int indexOfStart = cleanInput.indexOf("/FROM") + 5;
            int indexOfEnd = cleanInput.indexOf("/TO") + 3;

            String name = line.substring(indexOfName, indexOfStart - 5);
            String fromDate = line.substring(indexOfStart, indexOfEnd - 3).trim();
            String toDate = line.substring(indexOfEnd).trim();
            toAdd = new Event(name, fromDate, toDate);
        } else {
            toAdd = new Task(line);
        }
        int numTasks = Task.getTotalNumTasks();
        listOfTasks[numTasks - 1] = toAdd;
        System.out.println("    Added to list: " + toAdd.getName());
    }

    /**
     * Given a task's index and the list of tasks,
     * mark that task as complete.
     * @param index Index of the task to be marked.
     * @param listOfTasks The Task[] array that contains the list of tasks.
     */

    public static void markTask(int index, Task[] listOfTasks) {
        listOfTasks[index].setCompleteness(true);
        System.out.println("    Sure, I've marked this task as done:");
        String statusIcon = listOfTasks[index].getStatusIcon();
        System.out.print("    " + (index + 1) + ": ");
        System.out.print("[" + statusIcon + "] ");
        System.out.println(listOfTasks[index].getName());
    }

    /**
     * Given a task's index and the list of tasks,
     * mark that task as incomplete.
     *
     * @param index Index of the task to be marked.
     * @param listOfTasks The Task[] array that contains the list of tasks.
     */

    public static void unmarkTask(int index, Task[] listOfTasks) {
        listOfTasks[index].setCompleteness(false);
        System.out.println("    Okay, marking this task as incomplete now:");
        String statusIcon = listOfTasks[index].getStatusIcon();
        System.out.print("    " + (index + 1) + ": ");
        System.out.print("[" + statusIcon + "] ");
        System.out.println(listOfTasks[index].getName());
    }

    /**
     * Prints to the console a goodbye message for the user.
     */

    public static void printGoodbyeMessage() {
        System.out.println("    Goodbye! Hope you have a marvelous day.");
        spacerInsert("medium", true);
    }

    /**
     * The main program that starts the chatbot.
     * Prints to the console for the user to read its messages.
     * Greets the user and awaits user input.
     * <p>
     * Chatbot can:
     * <p>1. retrieve list of tasks with user input "list"
     * <p>2. mark or unmark tasks complete with user input "mark #tasknumber"
     * <p>3. say goodbye to the user with user input "bye" or "goodbye"
     * <p>4. add a to-do to the list of task with "todo <event name>"
     * <p>5. add a deadline to the list of task with "deadline <event name> /by <by date>"
     * <p>6. add an event to the list of task with "event <event name> /from <start date> /to <end date>"
     * <p>7. retrieve a list of chatbot commands with "help"
     *
     * @param args Command line arguments - not used.
     */

    public static void main(String[] args) {
        spacerInsert("medium", false);
        boolean isBye = false;
        Task[] listOfTasks = new Task[100];
        printGreetingMessage();
        printHelpMessage();

        while (!isBye) {
            Scanner in = new Scanner(System.in);
            String line = in.nextLine();
            String cleanedInput = line.toUpperCase().trim();
            int indexOfSpace = cleanedInput.indexOf(" ");
            String firstWord;
            if (indexOfSpace == -1) { // check for single-word inputs
                firstWord = cleanedInput;
            } else {
                firstWord = cleanedInput.substring(0, indexOfSpace);
            }

            if (firstWord.contains("MARK")) {
                int indexOfTask = cleanedInput.indexOf("MARK") + 4; // find index of task number
                int taskNumber = Integer.parseInt(cleanedInput.substring(indexOfTask).trim()); // parse string to int
                if (cleanedInput.contains("UNMARK")) {
                    unmarkTask(taskNumber - 1, listOfTasks);
                } else {
                    markTask(taskNumber - 1, listOfTasks);
                }
            } else {
                switch (firstWord) {
                case "LIST":
                    retrieveList(listOfTasks);
                    break;

                case "TODO":
                case "EVENT":
                case "DEADLINE":
                    TaskType currentTask = TaskType.TASK;
                    if (cleanedInput.startsWith("TODO")) {
                        currentTask = TaskType.TODO;
                    } else if (cleanedInput.startsWith("DEADLINE")) {
                        currentTask = TaskType.DEADLINE;
                    } else {
                        currentTask = TaskType.EVENT;
                    }
                    addTask(currentTask, listOfTasks, line, cleanedInput);
                    break;

                case "BYE":
                case "GOODBYE":
                    isBye = true;
                    printGoodbyeMessage();
                    break;

                case "HELP":
                    printHelpMessage();
                    break;

                default:
                    // handle invalid case
                    System.out.println("Invalid command read."); // for testing
                    break;
                }
            }
        }
    }
}
