import java.util.List;
import java.util.stream.Collectors;

/**
 * Parses user input and performs actions based on pre-set commands.
 */
public class Parser {
    public static final String ARE_YOU_MUTE = "... are you mute?";
    public static final String GOODBYE = "Goodbye... It may be a mere few seconds for you but an eternity for me.";
    public static final String BRO_SAY_SOMETHING_THAT_I_CAN_UNDERSTAND = "Bro, say something that I can understand.";
    public static final String INVALID_TASK_INDEX = "Invalid task index";
    public static final String GOOD_JOB = "Nice job! I've marked this task as done :D";
    public static final String SPECIFY_INDEX = "Please specify the task index";
    public static final String MARKED_AS_NOT_DONE = "OK, I've marked this task as not done, but stop being lazy!";
    public static final String BYE = "bye";
    public static final String LIST = "list";
    public static final String MARK = "mark";
    public static final String UNMARK = "unmark";
    public static final String FIND = "find";
    public static final int SPLIT_INTO_TWO_PARTS = 2;
    public static final String AIN_T_NO_DAMN_TASK_THAT_CONTAINS_THIS_KEYWORD_BUDDY = "Ain't no damn task that " +
            "contains this keyword buddy.";
    public static final String HELLO_ITS_ME_WHAT_ARE_YOU_TRYING_TO_SEARCH_FOR_BUDDY = "Hello, its me." +
            " What are you trying to search for buddy?";

    /**
     * Parses user input and performs actions based on pre-set commands.
     *
     * @param userInput The user input to be parsed.
     * @param taskList  The list of tasks to be changed based on the command.
     */
    public static void parseUserInput(String userInput, List<Task> taskList) {
        if (userInput.isEmpty()) {
            Ui.printMessage(ARE_YOU_MUTE);
            return;
        }
        if (userInput.equalsIgnoreCase(BYE)) {
            Ui.printMessage(GOODBYE);
        } else if (userInput.equalsIgnoreCase(LIST)) {
            Ui.displayTaskList(taskList);
        } else if (userInput.toLowerCase().startsWith(MARK)) {
            completeTask(userInput, taskList);
        } else if (userInput.toLowerCase().startsWith(UNMARK)) {
            unmarkTask(userInput, taskList);
        } else if (userInput.toLowerCase().startsWith(FIND)) {
            findTask(userInput, taskList);
        } else {
            Ui.printMessage(BRO_SAY_SOMETHING_THAT_I_CAN_UNDERSTAND);
        }
    }

    /**
     * Finds tasks in the task list that match the given keyword and displays them.
     *
     * @param userInput The user input containing the "find" keyword.
     * @param taskList  The list of tasks to search for matching tasks.
     */
    private static void findTask(String userInput, List<Task> taskList) {
        String keyword = userInput.substring(FIND.length()).trim().toLowerCase();

        if (!keyword.isEmpty()) {
            List<Task> matchingTasks = taskList.stream()
                    .filter(task -> task.getDescription().toLowerCase().contains(keyword))
                    .collect(Collectors.toList());

            if (!matchingTasks.isEmpty()) {
                Ui.displayFindTask(matchingTasks);
            } else {
                Ui.printMessage(AIN_T_NO_DAMN_TASK_THAT_CONTAINS_THIS_KEYWORD_BUDDY);
            }
        } else {
            Ui.printMessage(HELLO_ITS_ME_WHAT_ARE_YOU_TRYING_TO_SEARCH_FOR_BUDDY);
        }
    }

    /**
     * Marks a task as completed.
     *
     * @param userInput The user input containing the "mark" command and task index.
     * @param taskList  The list of tasks to update based on the completion status.
     */
    private static void completeTask(String userInput, List<Task> taskList) {
        try {
            String[] parts = userInput.split(" ", SPLIT_INTO_TWO_PARTS);
            checkIfTaskCanBeCompleted(parts, taskList);
        } catch (DavinciException e) {
            Ui.printMessage("Error: " + e.getMessage());
        } catch (NumberFormatException e) {
            Ui.printMessage("Error: " + INVALID_TASK_INDEX + " format.");
        }
    }

    /**
     * Checks if a task can be marked as completed and updates the task list accordingly.
     *
     * @param parts    The split parts of the user input containing the task index.
     * @param taskList The list of tasks to update based on the completion status.
     * @throws DavinciException If the task index is invalid.
     */
    private static void checkIfTaskCanBeCompleted(String[] parts, List<Task> taskList) throws DavinciException {
        if (parts.length > 1) {
            int taskIndex = Integer.parseInt(parts[1]) - 1;
            if (taskIndex >= 0 && taskIndex < taskList.size()) {
                taskList.get(taskIndex).markAsDone();
                Ui.printMessage(GOOD_JOB);
                Ui.displayTaskList(taskList);
                DavinciBot.writeFile();
            } else {
                throw new DavinciException(INVALID_TASK_INDEX + ".");
            }
        } else {
            throw new DavinciException(SPECIFY_INDEX + " to complete.");
        }
    }

    /**
     * Unmarks a completed task.
     *
     * @param userInput The user input containing the "unmark" command and task index.
     * @param taskList  The list of tasks to update based on the completion status.
     */
    private static void unmarkTask(String userInput, List<Task> taskList) {
        try {
            String[] parts = userInput.split(" ", SPLIT_INTO_TWO_PARTS);
            checkIfTaskCanBeUnmarked(parts, taskList);
        } catch (DavinciException e) {
            Ui.printMessage("Error: " + e.getMessage());
        } catch (NumberFormatException e) {
            Ui.printMessage("Error: " + INVALID_TASK_INDEX + " format.");
        }
    }

    /**
     * Checks if a completed task can be unmarked and updates the task list accordingly.
     *
     * @param parts    The split parts of the user input containing the task index.
     * @param taskList The list of tasks to update based on the completion status.
     * @throws DavinciException If the task index is invalid.
     */
    private static void checkIfTaskCanBeUnmarked(String[] parts, List<Task> taskList) throws DavinciException {
        if (parts.length > 1) {
            int taskIndex = Integer.parseInt(parts[1]) - 1;
            if (taskIndex >= 0 && taskIndex < taskList.size()) {
                taskList.get(taskIndex).markAsNotDone();
                Ui.printMessage(MARKED_AS_NOT_DONE);
                Ui.displayTaskList(taskList);
                DavinciBot.writeFile();
            } else {
                throw new DavinciException(INVALID_TASK_INDEX + ".");
            }
        } else {
            throw new DavinciException(SPECIFY_INDEX + " to unmark.");
        }
    }

}
