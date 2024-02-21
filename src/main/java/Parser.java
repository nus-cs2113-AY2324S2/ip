import java.util.List;

public class Parser {

    public static final String ARE_YOU_MUTE = "... are you mute?";
    public static final String GOODBYE = "Goodbye... It may be a mere few seconds for you but an eternity for me.";
    public static final String BRO_SAY_SOMETHING_THAT_I_CAN_UNDERSTAND = "Bro, say something that I can understand.";
    public static final String INVALID_TASK_INDEX = "Invalid task index";
    public static final String GOOD_JOB = "Nice job! I've marked this task as done :D";
    public static final String SPECIFY_INDEX = "Please specify the task index";
    public static final String MARKED_AS_NOT_DONE = "OK, I've marked this task as not done, but stop being lazy!";

    public static void parseUserInput(String userInput, List<Task> taskList) {
        if (userInput.isEmpty()) {
            Ui.printMessage(ARE_YOU_MUTE);
            return;
        }
        if (userInput.equalsIgnoreCase(DavinciBot.BYE)) {
            Ui.printMessage(GOODBYE);
        } else if (userInput.equalsIgnoreCase(DavinciBot.LIST)) {
            Ui.displayTaskList(taskList);
        } else if (userInput.toLowerCase().startsWith(DavinciBot.MARK)) {
            completeTask(userInput, taskList);
        } else if (userInput.toLowerCase().startsWith(DavinciBot.UNMARK)) {
            unmarkTask(userInput, taskList);
        } else {
            Ui.printMessage(BRO_SAY_SOMETHING_THAT_I_CAN_UNDERSTAND);
        }
    }

    private static void completeTask(String userInput, List<Task> taskList) {
        try {
            String[] parts = userInput.split(" ", DavinciBot.SPLIT_INTO_TWO_PARTS);
            checkIfTaskCanBeCompleted(parts, taskList);
        } catch (DavinciException e) {
            Ui.printMessage("Error: " + e.getMessage());
        } catch (NumberFormatException e) {
            Ui.printMessage("Error: " + INVALID_TASK_INDEX + " format.");
        }
    }

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

    private static void unmarkTask(String userInput, List<Task> taskList) {
        try {
            String[] parts = userInput.split(" ", DavinciBot.SPLIT_INTO_TWO_PARTS);
            checkIfTaskCanBeUnmarked(parts, taskList);
        } catch (DavinciException e) {
            Ui.printMessage("Error: " + e.getMessage());
        } catch (NumberFormatException e) {
            Ui.printMessage("Error: " + INVALID_TASK_INDEX + " format.");
        }
    }

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
