import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

/**
 * Represents user input parsing and handling
 * before providing feedback to the user.
 */
public class Handler {

    /**
     * Processes user input and filters for valid command words from enum {@code Command},
     * then creates the relevant {@code Task} object based on details entered.
     *
     * @param tasks The tasks list to be managed, adding the relevant {@code Task} object into the list.
     * @throws CustomException If an error occurs during command processing.
     */
    public static void processInput(ArrayList<Task> tasks) throws CustomException {
        Scanner in = new Scanner(System.in);
        String userInput = in.nextLine();

        while (true) {
            // Convert to uppercase before processing
            String instruction = userInput.toUpperCase().split(" ")[0];

            try {
                Command command = Command.valueOf(instruction);

                switch (command) {
                case BYE:
                    return;

                case MARK:
                    // Fallthrough
                case UNMARK:

                    handleMarkUnmark(userInput, tasks, command);

                    break;

                case LIST:

                    List.handleTasks(tasks);

                    break;

                case TODO:

                    handleToDo(tasks, userInput);

                    break;

                case EVENT:

                    handleEvent(tasks, userInput);

                    break;

                case DEADLINE:

                    handleDeadline(tasks, userInput);

                    break;

                case HELP:

                    Reply.printHelp();

                    break;

                case DELETE:

                    deleteItem(tasks, userInput);

                    break;

                case FIND:

                    List.searchList(tasks, userInput);

                    break;

                default:

                    break; // valueOf results in immediate exception for non-match with enum Command

                }
            } catch (IllegalArgumentException e) {
                Reply.printException(e, Constant.INVALID_COMMAND);
            } catch (CustomException e) {
                Reply.printException(e);
            }

            Persistence.saveTasks(tasks);
            userInput = in.nextLine();
        }
    }


    /**
     * Constructs a new {@code Event} task object based on the user input.
     *
     * @param tasks The tasks list to be managed, adding the relevant {@code Event} object into the list.
     * @param userInput The user input string.
     * @throws CustomException If the {@code Event} details are unspecified or invalid.
     */
    private static void handleEvent(ArrayList<Task> tasks, String userInput) {
        int taskCount = List.getTotal(tasks);
        String eventDetails = userInput.substring(Constant.EVENT_OFFSET).trim();
        if (eventDetails.isEmpty()) {
            throw new CustomException(Constant.UNSPECIFIED_PARAMETER);
        }
        String[] eventParameters = Event.getInterval(eventDetails);
        String eventLabel = eventParameters[0];
        Event event = new Event(eventLabel, eventParameters[1], eventParameters[2]);

        List.addTask(tasks, event);
        Reply.printReply(event, taskCount+1);
    }

    /**
     * Constructs a new {@code Deadline} task object based on the user input.
     *
     * @param tasks The tasks list to be managed, adding the relevant {@code Deadline} object into the list.
     * @param userInput The user input string.
     * @throws CustomException If the {@code Deadline} details are unspecified or invalid.
     */
    private static void handleDeadline(ArrayList<Task> tasks, String userInput) {
        int taskCount = List.getTotal(tasks);
        String deadlineDetails = userInput.substring(Constant.DEADLINE_OFFSET).trim();
        if (deadlineDetails.isEmpty()) {
            throw new CustomException(Constant.UNSPECIFIED_PARAMETER);
        }
        String[] deadlineParameters = Deadline.getDeadline(deadlineDetails);
        String deadlineLabel = deadlineParameters[0];
        Deadline deadline = new Deadline(deadlineLabel, deadlineParameters[1]);

        List.addTask(tasks, deadline);
        Reply.printReply(deadline, taskCount+1);
    }

    /**
     * Constructs a new {@code ToDo} task object based on the user input.
     *
     * @param tasks The tasks list to be managed, adding the relevant {@code ToDo} object into the list.
     * @param userInput The user input string.
     * @throws CustomException If the {@code ToDo} label is unspecified.
     */
    private static void handleToDo(ArrayList<Task> tasks, String userInput) {
        int taskCount = List.getTotal(tasks);
        String label = userInput.substring(Constant.TODO_OFFSET).trim();
        if (label.isEmpty()) {
            throw new CustomException(Constant.UNSPECIFIED_PARAMETER);
        }
        ToDo toDo = new ToDo(label);

        List.addTask(tasks, toDo);
        Reply.printReply(toDo, taskCount+1);
    }


    /**
     * Updates completion of a task, marking or unmarking the specified task based on user input.
     *
     * @param userInput The task index to mark or unmark completed.
     * @param tasks The tasks list to be managed.
     * @param command The command (MARK or UNMARK) to be executed.
     * @throws CustomException If the task index is invalid or unspecified.
     */
    private static void handleMarkUnmark(String userInput, ArrayList<Task> tasks, Command command) throws CustomException {
        String index = userInput.substring(command == Command.MARK ? Constant.MARK_OFFSET : Constant.UNMARK_OFFSET).trim();
        if (index.isEmpty()) {
            throw new CustomException(Constant.UNSPECIFIED_PARAMETER);
        }

        int taskIndex = Integer.parseInt(index) - 1;
        if (taskIndex < 0 || taskIndex >= List.getTotal(tasks)) {
            throw new CustomException(Constant.INVALID_PARAMETER);
        }

        Task markTask = tasks.get(taskIndex);
        markTask.setCompleted(command == Command.MARK); // Use enum for logic

        if (command == Command.MARK) {
            Reply.printReply("Nice! I've marked this task as done:", String.valueOf(markTask));
        } else {
            Reply.printReply("Okay, I've marked this task as not done yet:", String.valueOf(markTask));
        }
    }
    /**
     * Deletes a specified task from the list based on the provided user input.
     *
     * @param tasks The tasks list to be managed.
     * @param userInput The task index to delete from the tasks list.
     * @throws CustomException If the task index is invalid or unspecified.
     */
    public static void deleteItem(ArrayList<Task> tasks, String userInput) throws CustomException {
        String index = userInput.substring(Constant.DELETE_OFFSET).trim();
        if (index.isEmpty()) {
            throw new CustomException(Constant.UNSPECIFIED_PARAMETER);
        }

        try {
            int taskIndex = Integer.parseInt(index) - 1;
            if (taskIndex < 0 || taskIndex >= List.getTotal(tasks)) {
                throw new CustomException(Constant.INVALID_PARAMETER);
            }

            Reply.printReply("Deleted: " + (taskIndex + 1) + ". " + tasks.get(taskIndex));
            List.removeTask(tasks, taskIndex);

        } catch (NumberFormatException e) {
            throw new CustomException(Constant.INVALID_PARAMETER);
        }
    }

    /**
     * Initializes the Jarvas bot by printing a welcome message, loading tasks from storage,
     * and returning the tasks list.
     *
     * @return An ArrayList containing the loaded tasks.
     */
    public static ArrayList<Task> initialiseBot() {
        Reply.printWelcomeMessage();
        ArrayList<Task> tasks = new ArrayList<>(Constant.MAX_TASKS);
        Persistence.loadTasks(tasks);
        return tasks;
    }

    /**
     * Terminates the Jarvas bot by saving tasks to storage, printing a goodbye message,
     * and indicating the filename where tasks are saved.
     *
     * @param tasks The list of tasks to be saved.
     */
    public static void terminateBot(ArrayList<Task> tasks) {
        Persistence.saveTasks(tasks);
        Reply.printGoodbyeMessage();
        Reply.printReply("Saved tasks to: " + Constant.FILE_NAME);
    }
}
