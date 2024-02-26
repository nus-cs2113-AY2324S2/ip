import Exceptions.CorruptedFileException;
import Exceptions.DeadlineLackInputsException;
import Exceptions.EventLackInputsException;
import Exceptions.TodoLackInputsException;

/**
 * The Parser class handles parsing user input and task data.
 * It provides methods for processing user input into task information and loading task data from files.
 */
public class Parser {
    /**
     * The user input to be parsed.
     */
    protected String userInput;

    /**
     * Constructs a new Parser object.
     * This constructor initializes the Parser object.
     */
    public Parser () {}

    /**
     * Processes the user input to extract task information.
     *
     * @param userInput The user input representing a task to be processed.
     * @return An array containing task information, such as task type, description, and date/time.
     * @throws TodoLackInputsException    If the user input for a todo task lacks required inputs.
     * @throws DeadlineLackInputsException If the user input for a deadline task lacks required inputs.
     * @throws EventLackInputsException    If the user input for an event task lacks required inputs.
     */
    public static String[] processTaskInformation(String userInput) throws TodoLackInputsException, DeadlineLackInputsException, EventLackInputsException {
        userInput = userInput.toLowerCase();
        String[] wordArray = userInput.split(" ");
        String[] output = new String[4];
        switch (wordArray[0]) {
        case ("todo"):
            output[0] = "todo";
            output[1] = userInput.substring(4).trim();
            if (output[1].trim().isEmpty()) {
                throw new TodoLackInputsException();
            }
            break;

        case ("deadline"):
            output[0] = "deadline";
            int byIndex = userInput.indexOf("/by");
            output[1] = userInput.substring(8, byIndex).trim();
            output[2] = userInput.substring(byIndex + 4).trim();
            if (output[1].trim().isEmpty() || output[2].trim().isEmpty()) {
                throw new DeadlineLackInputsException();
            }
            break;

        case ("event"):
            output[0] = "event";
            int fromIndex = userInput.indexOf("/from");
            output[1] = userInput.substring(5, fromIndex).trim();
            int toIndex = userInput.indexOf("/to");
            output[2] = userInput.substring(fromIndex + 6, toIndex).trim();
            output[3] = userInput.substring(toIndex + 4).trim();
            if (output[1].trim().isEmpty() || output[2].trim().isEmpty() || output[3].trim().isEmpty()) {
                throw new EventLackInputsException();
            }
            break;

        default:
            output[0] = "error";
        }
        return output;
    }

    /**
     * Processes the user input to extract the task ID for marking or deleting a task.
     *
     * @param userInput The user input representing a command to mark or delete a task.
     * @return The ID of the task to be marked or deleted.
     */
    public static int processTaskIdforMarkingAndDeletingTask(String userInput) {
        userInput = userInput.toLowerCase();
        String[] wordArray = userInput.split(" ");
        return Integer.parseInt(wordArray[1]) - 1;
    }

    /**
     * Processes task data loaded from a file.
     *
     * @param data The task data loaded from the file.
     * @return An array containing task information loaded from the file.
     * @throws CorruptedFileException If the loaded task data is corrupted or incomplete.
     */
    public static String[] processTaskLoadingData(String data) throws CorruptedFileException {
        String[] wordArray = data.split(",");
        String[] output = new String[5];
        switch (wordArray[0]) {
        case ("T"):
            output[0] = "todo";
            output[1] = wordArray[1];
            output[2] = wordArray[2];
            if (output[1].trim().isEmpty() || output[2].trim().isEmpty()) {
                throw new CorruptedFileException();
            }
            break;

        case ("D"):
            output[0] = "deadline";
            output[1] = wordArray[1];
            output[2] = wordArray[2];
            output[3] = wordArray[3];
            if (output[1].trim().isEmpty() || output[2].trim().isEmpty() || output[3].trim().isEmpty()) {
                throw new CorruptedFileException();
            }
            break;

        case ("E"):
            output[0] = "event";
            output[1] = wordArray[1];
            output[2] = wordArray[2];
            output[3] = wordArray[3];
            output[4] = wordArray[4];
            if (output[1].trim().isEmpty() || output[2].trim().isEmpty() || output[3].trim().isEmpty() ||
                    output[4].trim().isEmpty()) {
                throw new CorruptedFileException();
            }
            break;

        default:
            output[0] = "error";
            throw new CorruptedFileException();
        }
        return output;
    }

}
