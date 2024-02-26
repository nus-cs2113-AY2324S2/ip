import Exceptions.CorruptedFileException;
import Exceptions.DeadlineLackInputsException;
import Exceptions.EventLackInputsException;
import Exceptions.TodoLackInputsException;
import Tasks.Deadline;
import Tasks.Event;
import Tasks.Task;
import Tasks.Todo;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;

/**
 * The TaskManager class manages tasks and their operations.
 * It provides methods to interact with tasks, such as adding, deleting, and marking tasks as done.
 */
public class TaskManager {
    /**
     * The file path for storing task data.
     */
    public static final String DATA_TXT_FILE_PATH = "./data.txt";

    /**
     * The number of items currently stored in the task manager.
     */
    private static int numItems;

    /**
     * An ArrayList to store tasks.
     */
    ArrayList<Task> taskArrayList = new ArrayList<>();

    /**
     * TaskList object to manage tasks.
     */
    TaskList myTaskList = new TaskList(taskArrayList);

    /**
     * String representation of required inputs for different task types.
     */
    public final String TODO_REQUIRED_INPUTS = "'todo <task>'";
    public final String DEADLINE_REQUIRED_INPUTS = "'Deadline <task> /by <due date>'";
    public final String EVENT_REQUIRED_INPUTS = "'Event <task> /from <start date> /to <end date>'";

    /**
     * Constructs a new TaskManager object.
     * Initializes the number of items to 0.
     */
    public TaskManager() {
        numItems = 0;
    }

    /**
     * Adds task contents to the task list based on the user input.
     * Handles various types of tasks like Todo, Deadline, and Event.
     * Adds task contents to the data.txt file
     *
     * @param userInput The user input containing task information to be processed and added.
     */
    public void addListContents(String userInput) {
        try {
            String[] taskInformation = Parser.processTaskInformation(userInput);
            addByTaskToTaskArray(taskInformation, false);
            System.out.println("Got it. I've added this task:");
            System.out.println(TaskList.get(numItems));
            String taskPluralString = numItems > 1 ? " tasks" : " task";
            System.out.println("Now you have " + (numItems + 1) + taskPluralString + " in the list.");
            String dataToAppend = formatDataToAppend(taskInformation);
            if (numItems == 0) {
                Storage.writeToFile(DATA_TXT_FILE_PATH, dataToAppend);
            }
            else {
                Storage.appendToFile(DATA_TXT_FILE_PATH, dataToAppend);
            }
            numItems += 1;
        } catch (TodoLackInputsException e) {
            lackInputsErrorMessage(userInput, "todo", TODO_REQUIRED_INPUTS);
        } catch (DeadlineLackInputsException e) {
            lackInputsErrorMessage(userInput, "deadline", DEADLINE_REQUIRED_INPUTS);
        } catch (EventLackInputsException e) {
            lackInputsErrorMessage(userInput, "event", EVENT_REQUIRED_INPUTS);
        } catch (IndexOutOfBoundsException e) {
            // update this if /help is added
            if (userInput.contains("deadline")) {
                lackInputsErrorMessage(userInput, "deadline", DEADLINE_REQUIRED_INPUTS);
            } else if (userInput.contains("event")) {
                lackInputsErrorMessage(userInput, "event", EVENT_REQUIRED_INPUTS);
            }
        } catch (IOException e) {
            System.out.println("failed to append data to txt file");
        }

    }

    /**
     * Formats task information into a string to append to the task list data file.
     * This method accepts an array of task information and creates a string
     * representation based on the task type (Todo, Deadline, or Event).
     *
     * @param taskInformation An array containing task information.
     *                        For Todo tasks: taskInformation[0] = "todo", taskInformation[1] = task description.
     *                        For Deadline tasks: taskInformation[0] = "deadline", taskInformation[1] = task description,
     *                        taskInformation[2] = deadline.
     *                        For Event tasks: taskInformation[0] = "event", taskInformation[1] = event description,
     *                        taskInformation[2] = event start date, taskInformation[3] = event end date.
     * @return A string representation of the task formatted for appending to the task list data file.
     *         For example, "T,0,taskDescription" for Todo tasks,
     *         "D,0,taskDescription,deadline" for Deadline tasks,
     *         "E,0,eventDescription,eventDate,eventTime" for Event tasks.
     *         Returns "error" if the task type is not recognized.
     */
    public static String formatDataToAppend(String[] taskInformation) {
        String output;
        switch (taskInformation[0]) {
        case ("todo"):
            output = "T,0," + taskInformation[1];
            break;

        case ("deadline"):
            output = "D,0," + taskInformation[1] + "," + taskInformation[2];
            break;

        case ("event"):
            output = "E,0," + taskInformation[1] + "," + taskInformation[2] + "," + taskInformation[3];
            break;

        default:
            output = "error";
        }
        return output;
    }

    /**
     * Adds a new task to the task list based on the provided task information.
     * This method creates a new task object depending on the task type and adds it to the task list.
     *
     * @param taskInformation An array containing task information.
     *                        For Todo tasks: taskInformation[0] = "todo", taskInformation[1] = task description.
     *                        For Deadline tasks: taskInformation[0] = "deadline", taskInformation[1] = task description,
     *                        taskInformation[2] = deadline.
     *                        For Event tasks: taskInformation[0] = "event", taskInformation[1] = event description,
     *                        taskInformation[2] = event start date, taskInformation[3] = event end date.
     * @param isDone          A boolean value indicating whether the task is already done or not.
     *                        'true' if the task is done, 'false' otherwise.
     */
    private static void addByTaskToTaskArray(String[] taskInformation, boolean isDone) {
        switch (taskInformation[0]) {
        case ("todo"):
            TaskList.add(new Todo(taskInformation[1], isDone));
            break;

        case ("deadline"):
            TaskList.add(new Deadline(taskInformation[1], isDone, taskInformation[2]));
            break;

        case ("event"):
            TaskList.add(new Event(taskInformation[1], isDone, taskInformation[2],
                    taskInformation[3]));
            break;

        case ("error"):
            //should not hit this line
            System.out.println("Please give a proper input for todo/deadline/event");
            break;

        default:
            break;
        }
    }

    /**
     * Prints an error message indicating that the user's task description lacks inputs.
     * This method provides guidance on how to correctly format the task description.
     *
     * @param userInput      The user input that triggered the error.
     * @param errorType      The type of task for which inputs are lacking (e.g., "todo", "deadline", "event").
     * @param requiredInputs The required format for the task description that should be entered.
     *                       For example, "todo <description>", "deadline <description> /by <deadline>", "event <description> /at <date> <time>".
     */
    private static void lackInputsErrorMessage(String userInput, String errorType, String requiredInputs) {
        System.out.println("Your " + errorType + " task description seems to lack inputs. What you entered was " + userInput +
                ". Try typing it as " + requiredInputs);
    }

    /**
     * Displays the contents of the task list.
     * If the list is empty, it prints a message indicating so.
     * Otherwise, it prints the tasks along with their indices in the list.
     */
    public void showListContents() {
        if (numItems == 0) {
            System.out.println("List is empty. Please enter something first.");
        } else {
            System.out.println("Here are the tasks in your list:");
            for (int i = 0; i < numItems; i += 1) {
                System.out.print(i + 1 + ". ");
                System.out.println(TaskList.get(i));
            }
        }
    }

    /**
     * Checks if a given string represents a valid integer.
     *
     * @param number The string to be checked for integer validity.
     * @return true if the string represents a valid integer, false otherwise.
     */
    public static boolean isStringInteger(String number) {
        try {
            Integer.parseInt(number);
        } catch (Exception e) {
            return false;
        }
        return true;
    }

    /**
     * Changes the status of a task based on the user input.
     * If the user input is not valid, the method prints out an error message.
     *
     * @param userInput The user input indicating the task ID and whether to mark or unmark the task.
     */
    public void changeTaskStatus(String userInput) {
        if (!isValidTaskId(userInput, "changeTaskStatus")) {
            return;
        }

        int id = Parser.processTaskIdforMarkingAndDeletingTask(userInput);

        if (userInput.contains("unmark")) {
            try {
                Storage.changeTaskStatusData(DATA_TXT_FILE_PATH, id + 1, 0);
            } catch (FileNotFoundException e) {
                System.out.println("could not unmark task");
                return;
            }
            TaskList.setDone(id, false);
            System.out.println("OK, I've marked this task as not done yet:");
            System.out.println(TaskList.get(id));
        }
        // must contain mark at this point
        else {
            try {
                Storage.changeTaskStatusData(DATA_TXT_FILE_PATH, id + 1, 1);
            } catch (FileNotFoundException e) {
                System.out.println("could not mark task");
                return;
            }
            TaskList.setDone(id, true);
            System.out.println("Nice! I've marked this task as done:");
            System.out.println(TaskList.get(id));
        }
    }

    /**
     * Checks if the user input contains a valid task ID.
     * The method also ensures that the input follows the expected command structure for a specific purpose.
     *
     * @param userInput The user input to be validated, containing task information.
     * @param purpose   A string indicating the purpose of the validation. Valid values are "deleteTask" or "changeTaskStatus".
     * @return true if the user input is valid, false otherwise.
     */
    public boolean isValidTaskId(String userInput, String purpose) {
        userInput = userInput.toLowerCase();
        String[] wordArray = userInput.split(" ");
        String commandStructure;
        if (purpose.equals("changeTaskStatus")) {
            commandStructure = "\"mark x or unmark x\"";
        } else if (purpose.equals("deleteTask")) {
            commandStructure = "\"delete x\"";
        } else {
            System.out.println("not a valid purpose =(");
            return false;
        }

        if (wordArray.length != 2 || !isStringInteger(wordArray[1])) {
            System.out.println("Please give a command in the structure of " + commandStructure +
                    "where x is the task number");
            return false;
        }

        int id = Integer.parseInt(wordArray[1]) - 1;
        if (id >= numItems || id < 0) {
            System.out.println("Please select a task number that exists. =)");
            return false;
        }
        return true;
    }

    /**
     * Deletes a task from the task list based on the provided user input.
     * If the user input is not valid, the method returns without making any changes and prints out an error message.
     *
     * @param userInput The user input indicating the task ID to be deleted.
     */
    public void deleteTask(String userInput) {
        if (!isValidTaskId(userInput, "deleteTask")) {
            return;
        }
        int id = Parser.processTaskIdforMarkingAndDeletingTask(userInput);
        System.out.println("Noted. I've removed this task:");
        System.out.println(TaskList.get(id));
        numItems -= 1;
        String taskPluralString = numItems > 1 ? " tasks" : " task";
        System.out.println("Now you have " + numItems + taskPluralString + " in the list.");
        try {
            Storage.removeLineData(DATA_TXT_FILE_PATH, id + 1);
        } catch (FileNotFoundException e) {
            System.out.println("unable to delete task. Please ensure that you have input the correct command");
        }
        TaskList.delete(id);
    }

    /**
     * Loads tasks from the provided data array list into the task list.
     * Each element in the data array list represents a line of task data.
     *
     * @param dataArrayList An ArrayList containing lines of task data to be loaded.
     *                      Each line represents a task in a specific format.
     *                      For example, "T,0,taskDescription" for Todo tasks,
     *                      "D,0,taskDescription,deadline" for Deadline tasks,
     *                      "E,0,eventDescription,startDate,endDate" for Event tasks.
     *                      The second element indicates whether the task is done (1) or not done (0).
     *                      The format is expected to be consistent across all lines.
     *                      If the file is corrupted, the method clears the file, restarts it,
     *                      resets the task count, and clears the task list.
     */
    public static void loadTasks(ArrayList<String> dataArrayList) {
        for (String line : dataArrayList) {
            try {
                String[] output = Parser.processTaskLoadingData(line);
                boolean isDone = output[1].equals("1");
                String[] filteredOutput = new String[4];
                filteredOutput[0] = output[0];
                System.arraycopy(output, 2, filteredOutput, 1, 3);
                addByTaskToTaskArray(filteredOutput, isDone);
                numItems += 1;
            } catch (CorruptedFileException e) {
                System.out.println("File is corrupted");
                Storage.clearFile(DATA_TXT_FILE_PATH);
                System.out.println("Successfully restarted file");
                numItems = 0;
                TaskList.clearList();
                return;
            }
        }
        System.out.println("File loaded successfully!");
    }

}
