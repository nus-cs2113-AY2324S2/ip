package hachi;

import hachi.data.HachiException;
import hachi.data.task.Deadline;
import hachi.data.task.Event;
import hachi.data.task.Task;
import hachi.data.task.TaskType;
import hachi.data.task.Todo;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
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

    private static ArrayList<Task> tasksArrayList = new ArrayList<>();
    static String filePath = "hachidata/hachidata.txt";
    protected static File f = new File(filePath);
    protected static File folder = new File("hachidata");

    public static void initializeData() throws IOException {
        if (!folder.exists()) {
            folder.mkdir();
        }
    }

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
        System.out.println("\t'list' to retrieve your current list of tasks,");
        System.out.println("\t'mark <#>' to mark task number # as complete,");
        System.out.println("\t'unmark <#>' to mark task number # as incomplete,");
        System.out.println("\t'todo <task name>' to create a to-do,");
        System.out.println("\t'deadline <task name> /by <by date>' to create a task with a deadline,");
        System.out.println("\t'event <task name> /from <start> /to <end>' to create an event " +
                "with a start and end date,");
        System.out.println("\t'bye' to stop chatting :('");
        System.out.println("\tAnd if you need to see this again, type 'help'!");
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
            System.out.print("\t");
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
     * @throws HachiException If the current list is empty.
     */

    public static void retrieveList() throws HachiException{
        int numTasks = Task.getTotalNumTasks();

        HachiException.checkEmptyList(numTasks);
        spacerInsert("medium", true);

        System.out.println("\tThe following are in your list:");
        tasksArrayList.forEach(task -> {
            String taskType = task.getTaskType();
            String statusIcon = task.getStatusIcon();
            int currentIndex = tasksArrayList.indexOf(task);
            System.out.print("\t" + (currentIndex + 1) + ": ");
            System.out.print("[" + taskType + "] ");
            System.out.print("[" + statusIcon + "] ");
            System.out.println(task.getName());
        });
    }

    /**
     * Given a task's name and the list of tasks, add a new task into the list.
     * Depending on the user's input, can create subclass of tasks: Todos, Deadlines and Events.
     *
     * @param taskType Type of task to be added. (Todo, Event, Deadline)
     * @param line The line of text given by the user.
     * @param cleanInput The cleaned line of text that will be used to determine the instruction.
     */

    public static void addTask(TaskType taskType, String line, String cleanInput) throws HachiException {
        Task toAdd;
        HachiException.checkValidDescription(line);

        if (taskType == TaskType.TODO) {
            int indexOfTodo = cleanInput.indexOf("TODO") + 5;
            String name = line.substring(indexOfTodo).trim();
            toAdd = new Todo(name);
        } else if (taskType == TaskType.DEADLINE) {
            // parse deadline here
            int indexOfName = cleanInput.indexOf("DEADLINE") + 9;
            int indexOfBy = cleanInput.indexOf("/BY") + 3;
            HachiException.checkDeadlineByDate(indexOfBy);

            String name = line.substring(indexOfName, indexOfBy - 3).trim();
            String byDate = line.substring(indexOfBy).trim();
            toAdd = new Deadline(name, byDate);
        } else {
            // parse to and from dates here
            int indexOfName = cleanInput.indexOf("EVENT") + 6;
            int indexOfStart = cleanInput.indexOf("/FROM") + 5;
            int indexOfEnd = cleanInput.indexOf("/TO") + 3;
            HachiException.checkEventDates(indexOfStart, indexOfEnd);

            String name = line.substring(indexOfName, indexOfStart - 5);
            String fromDate = line.substring(indexOfStart, indexOfEnd - 3).trim();
            String toDate = line.substring(indexOfEnd).trim();
            toAdd = new Event(name, fromDate, toDate);
        }

        tasksArrayList.add(toAdd);
        System.out.println("\tAdded to list: " + toAdd.getName());
    }

    /**
     * Function that cleans the user input for mark or unmark requests
     * and completes the function call as required.
     *
     * @param cleanedInput Cleaned input string from user.
     */

    public static void markOrUnmarkHandler(String cleanedInput) throws HachiException{
        int numTasks = Task.getTotalNumTasks();
        HachiException.checkEmptyList(numTasks);
        int taskNumber = getMarkTaskNumber(cleanedInput);
        HachiException.checkOutOfBounds(taskNumber);
        markOrUnmarkTask(taskNumber - 1, !cleanedInput.contains("UNMARK"));
    }

    /**
     * Given a task's index and the list of tasks,
     * mark that task as complete or incomplete.
     *
     * @param index Index of the task to be marked.
     * @param isMark True if task is to be marked as complete, false otherwise
     */

    public static void markOrUnmarkTask(int index, boolean isMark) {
        tasksArrayList.get(index).setCompleteness(isMark);
        System.out.println("\tSure, I've done as you requested:");

        Task currentTask = tasksArrayList.get(index);
        String taskType = currentTask.getTaskType();
        String statusIcon = currentTask.getStatusIcon();
        System.out.print("\t" + (index + 1) + ": ");
        System.out.print("[" + taskType + "] ");
        System.out.print("[" + statusIcon + "] ");
        System.out.println(currentTask.getName());
    }

    private static void deleteTask(String cleanedInput) throws HachiException {
        int numTasks = Task.getTotalNumTasks();
        HachiException.checkEmptyList(numTasks);
        int taskNumber = getDeleteTaskNumber(cleanedInput);
        HachiException.checkOutOfBounds(taskNumber);

        Task taskToDelete = tasksArrayList.get(taskNumber - 1);
        String taskType = taskToDelete.getTaskType();
        String statusIcon = taskToDelete.getStatusIcon();
        Task.decrementTotalNumTasks();

        System.out.println("\tSure, I've done as you requested.");
        System.out.print("\t[" + taskType + "] ");
        System.out.print("[" + statusIcon + "] " + taskToDelete.getName());
        System.out.println(" has been deleted from your list.");

        tasksArrayList.remove(taskNumber - 1);
    }

    private static int getDeleteTaskNumber(String cleanedInput) throws HachiException {
        int indexOfTaskNum = cleanedInput.indexOf("DELETE") + 6; // find index of task number
        int taskNumber = 0;

        try {
            taskNumber = Integer.parseInt(cleanedInput.substring(indexOfTaskNum).trim()); // parse string to int
        } catch (NumberFormatException e){
            HachiException.checkOutOfBounds(indexOfTaskNum);
        }

        return taskNumber;
    }

    private static int getMarkTaskNumber(String cleanedInput) throws HachiException {
        int indexOfTaskNum = cleanedInput.indexOf("MARK") + 4; // find index of task number
        int taskNumber = 0;

        try {
            taskNumber = Integer.parseInt(cleanedInput.substring(indexOfTaskNum).trim()); // parse string to int
        } catch (NumberFormatException e){
            HachiException.checkOutOfBounds(indexOfTaskNum);
        }

        return taskNumber;
    }

    private static String getFirstWordOfInput(int indexOfSpace, String cleanedInput) {
        String firstWord;
        if (indexOfSpace == -1) { // check for single-word inputs
            firstWord = cleanedInput;
        } else {
            firstWord = cleanedInput.substring(0, indexOfSpace);
        }
        return firstWord;
    }

    /**
     * Prints to the console a goodbye message for the user.
     */

    public static void printGoodbyeMessage() {
        System.out.println("\tGoodbye! Hope you have a marvelous day.");
    }

    private static void loadFile(ArrayList<Task> taskArrayList) throws FileNotFoundException, HachiException {
        File taskFile = new File(filePath);
        Scanner s = new Scanner(taskFile);

        while (s.hasNext()) {
            String[] lines = s.nextLine().split(" \\| ");
            Task toAdd;

            switch (lines[0]){
            case "T":
                toAdd = new Todo(lines[2]);
                break;

            case "D":
                toAdd = new Deadline(lines[2], lines[3]);
                break;

            case "E":
                toAdd = new Event(lines[2], lines[3], lines[4]);
                break;

            default:
                throw new HachiException(HachiException.CORRUPTED_SAVE_MESSAGE);
            }

            if (lines[1].equals("X")) {
                toAdd.setCompleteness(true);
            }
            taskArrayList.add(toAdd);
        }
    }

    private static void save(ArrayList<Task> taskArrayList) throws IOException {
        try {
            FileWriter fw = new FileWriter(filePath, false);
            fw.write("");
            fw.close(); // to clear text file
        } catch (IOException e) {
            System.out.println("\n\tCreating new task list save...");
        }

        FileWriter fw = new FileWriter(filePath);
        for (Task task : taskArrayList) {
            if (task != null) {
                fw.write(task.getSaveFormat() + "\n");
            } else break;
        }
        fw.close();
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

    public static void main(String[] args) throws IOException {
        Scanner in = new Scanner(System.in);
        boolean isBye = false;
        boolean isUpdated = false;
        spacerInsert("medium", false);
        printGreetingMessage();
        printHelpMessage();
        initializeData();

        try {
            loadFile(tasksArrayList);
        } catch (FileNotFoundException e) {
            System.out.println("Error finding save file. Creating empty task list.");
        } catch (HachiException e) {
            System.out.println("Save file is corrupted. Creating empty task list.");
        }

        while (!isBye) {
            try {
                isUpdated = false;
                String line = in.nextLine();
                String cleanedInput = line.toUpperCase().trim();
                String firstWord;
                int indexOfSpace = cleanedInput.indexOf(" ");

                firstWord = getFirstWordOfInput(indexOfSpace, cleanedInput);

                switch (firstWord) {
                case "MARK":
                case "UNMARK":
                    markOrUnmarkHandler(cleanedInput);
                    isUpdated = true;
                    break;

                case "LIST":
                    retrieveList();
                    break;

                case "DELETE":
                    deleteTask(cleanedInput);
                    isUpdated = true;
                    break;

                case "TODO":
                case "EVENT":
                case "DEADLINE":
                    TaskType currentTask;

                    if (cleanedInput.startsWith("EVENT")) {
                        currentTask = TaskType.EVENT;
                    } else if (cleanedInput.startsWith("DEADLINE")) {
                        currentTask = TaskType.DEADLINE;
                    } else {
                        currentTask = TaskType.TODO;
                    }

                    addTask(currentTask, line, cleanedInput);
                    isUpdated = true;
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
                    HachiException.invalidInput();
                    break;
                }
            } catch (HachiException e) {
                System.out.println(e.getMessage());
            } finally {
                if (isUpdated) { // save if there is a file update
                    try {
                        save(tasksArrayList);
                    } catch (IOException e) {
                        System.out.println("There was an error saving tasks.");
                    }
                }

                spacerInsert("medium", true);
            }
        }
    }
}
