package mimi.helper;

import mimi.classes.Deadline;
import mimi.classes.Event;
import mimi.classes.ToDo;
import mimi.exceptions.MimiException;

import static mimi.helper.Storage.DEADLINE_DELIMITER;

/**
 * Parser class is responsible for handling the parsing of both the user input and file input.
 * It contains methods to parse the user input and file input into the respective task objects.
 *
 * @version 0.2
 * @since 0.2
 */
public class Parser {
    public final static String EVENT_FROM_DELIMITER = "/from";
    public final static String EVENT_TO_DELIMITER = "/to";
    public Parser(){

    }

    /**
     * Use this method when processing a ToDo from a user input.
     *
     * @param inputs   An array containing the task name in the format {taskName}.
     *                 The taskName is a String representing the name of the task.
     * @return         A ToDo object
     * @throws MimiException.InsufficientParameters if the task name is missing
     * @throws MimiException.IncorrectFormat if the task name is empty
     */
    public static ToDo processsToDoFromInput(String[] inputs) throws
            MimiException.InsufficientParameters, MimiException.IncorrectFormat {
        if (inputs.length != 2) {
            throw new MimiException.InsufficientParameters(MimiException.INSUFFICIENT_TODO_PARAMETERS_MSG);
        }

        if (inputs[1].isBlank()) {
            throw new MimiException.IncorrectFormat(MimiException.INCORRECT_TODO_FORMAT_MSG);
        }

        return new ToDo(inputs[1]);
    }

    /**
     * Use this method when processing a ToDo from a storage input.
     *
     * @param taskName   A String representing the name of the task.
     * @param taskStatus A String (true/false) representing the status of the task.
     * @return           A ToDo object
     * @throws MimiException.FileCorrupted if the file is corrupted
     */
    public static ToDo processToDoFromStorage(String taskName, String taskStatus) throws MimiException.FileCorrupted {
            ToDo toDo = new ToDo(taskName);

            if (taskStatus.equals("true")) {
                // mark as done
                toDo.markAsDone();
            } else if (taskStatus.equals("false")) {
                toDo.markAsUndone();
            } else {
                throw new MimiException.FileCorrupted(MimiException.FILE_CORRUPTED_MSG);
            }

            return toDo;
    }

    /**
     * Use this method when processing an Event from a user input.
     *
     * @param inputs   An array containing the task name, start date, end date
     *                 Stored in the format {taskName, startDate, endDate}.
     *                 The taskName is a String representing the name of the task.
     *                 The startDate is a String representing the starting date of the task.
     *                 The endDate is a String representing the ending date of the task.
     * @return         An Event object
     * @throws MimiException.InsufficientParameters if the task name or date is missing
     * @throws MimiException.IncorrectFormat if the task name or date is empty
     */
    public static Event processEventFromInput(String[] inputs) throws
            MimiException.InsufficientParameters, MimiException.IncorrectFormat {
        if (inputs.length != 2) {
            throw new MimiException.InsufficientParameters(MimiException.INSUFFICIENT_EVENT_PARAMETERS_MSG);
        }
        String eventDetails = inputs[1];
        return processEvent(eventDetails);
    }

    /**
     * Use this method when processing an Event from a storage input.
     *
     * @param input   An array containing the task type, task status, task name, start date, end date
     *                Stored in the format {taskType, taskStatus, taskName, startDate, endDate}.
     *                The taskType is a String representing the type of the task.
     *                The taskStatus is a String (true/false) representing the status of the task.
     *                The taskName is a String representing the name of the task.
     *                The startDate is a String representing the starting date of the task.
     *                The endDate is a String representing the ending date of the task.
     * @return        An Event object
     * @throws MimiException.InsufficientParameters if the task name or date is missing
     * @throws MimiException.IncorrectFormat if the task name or date is empty
     */
    public static Event processEventFromStorage(String[] input) throws
            MimiException.InsufficientParameters, MimiException.IncorrectFormat {
        try {
            String taskStatus = input[1];
            String taskName = input[2];
            String startDate = input[3];
            String endDate = input[4];


            if (taskStatus.isBlank() || taskName.isBlank() || startDate.isBlank() || endDate.isBlank()) {
                throw new MimiException.IncorrectFormat(MimiException.INCORRECT_EVENT_FORMAT_MSG);
            }
            return new Event(taskName, startDate, endDate);

        } catch (ArrayIndexOutOfBoundsException e) {
            throw new MimiException.InsufficientParameters(MimiException.INSUFFICIENT_EVENT_PARAMETERS_MSG);
        }
    }

    /**
     * A private method used when processing an Event from a userInput.
     *
     * @param givenInput    A String in the format "taskName /from startDate /to endDate"
     * @return              A Deadline object
     * @throws MimiException.InsufficientParameters if the task name or date is missing
     * @throws MimiException.IncorrectFormat if the task name or date is empty
     */
    private static Event processEvent(String givenInput) throws
            MimiException.IncorrectFormat,
            MimiException.InsufficientParameters {

        // Further process the deadline input
        try {
            String[] inputs = givenInput.split(EVENT_FROM_DELIMITER, 2);
            String[] duration = inputs[1].split(EVENT_TO_DELIMITER, 2);

            // Check if the inputs are complete
            String taskName = inputs[0];
            String startDate = duration[0].strip();
            String endDate = duration[1].strip();

            // Throws an error if parameters is incomplete
            if (taskName.isBlank() || startDate.isBlank() || endDate.isBlank()) {
                throw new MimiException.IncorrectFormat(MimiException.INCORRECT_EVENT_FORMAT_MSG);
            }

            return new Event(taskName, startDate, endDate);

        } catch (ArrayIndexOutOfBoundsException e) {
            throw new MimiException.InsufficientParameters(MimiException.INSUFFICIENT_EVENT_PARAMETERS_MSG);
        }
    }

    /**
     * Use this method when processing a Deadline from a user input.
     *
     * @param inputs   An array containing the two items.
     *                 The first is the taskType
     *                 The second is the taskName and the deadline combined
     *                 e.g. {"deadline", "return book /by 2020-12-12"}

     * @return         A Deadline object
     * @throws MimiException.InsufficientParameters if the task name or date is missing
     * @throws MimiException.IncorrectFormat if the task name or date is empty
     */
    public static Deadline processDeadlineFromInput(String[] inputs) throws
            MimiException.InsufficientParameters, MimiException.IncorrectFormat {
        if (inputs.length < 2) {
            throw new MimiException.InsufficientParameters(MimiException.INSUFFICIENT_DEADLINE_PARAMETERS_MSG);
        }

        String[] splitInputs = inputs[1].split(DEADLINE_DELIMITER, 2);
        return processDeadline(splitInputs);
    }

    /**
     * Use this method when processing a Deadline from a storage input.
     *
     * @param inputs   An array containing the task type, task status, task name, and deadline
     *                 Stored in the format {taskType, taskStatus, taskName, deadline}.
     *                 The taskType is a String representing the type of the task.
     *                 The taskStatus is a String (true/false) representing the status of the task.
     *                 The taskName is a String representing the name of the task.
     *                 The deadline is a String representing the deadline of the task.
     * @return         A Deadline object
     * @throws MimiException.InsufficientParameters if the task name or date is missing
     * @throws MimiException.IncorrectFormat if the task name or date is empty
     */
    public static Deadline processDeadlineFromStorage(String[] inputs) throws
            MimiException.InsufficientParameters, MimiException.IncorrectFormat {
        if (inputs.length < 4) {
            throw new MimiException.InsufficientParameters(MimiException.INSUFFICIENT_DEADLINE_PARAMETERS_MSG);
        }
        String taskName = inputs[2];
        String until = inputs[3];
        String[] deadlineParameter = {taskName, until};
        return processDeadline(deadlineParameter);
    }

    /**
     * A private method used when processing a Deadline from a userInput/fileInput.
     *
     * @param inputs   An array containing the taskName and the deadline
     *                 Stored in the format {taskName, deadline}.
     *                 The taskName is a String representing the name of the task.
     *                 The deadline is a String representing the deadline of the task.
     * @return         A Deadline object
     * @throws MimiException.InsufficientParameters if the task name or date is missing
     * @throws MimiException.IncorrectFormat if the task name or date is empty
     */

    private static Deadline processDeadline(String[] inputs) throws
            MimiException.InsufficientParameters,
            MimiException.IncorrectFormat {

        if (inputs.length < 2) {
            // Throws an error if /by is missing
            throw new MimiException.IncorrectFormat(MimiException.INCORRECT_DEADLINE_FORMAT_MSG);
        }

        for (String s : inputs) {
            // Throws an error if parameters is incomplete
            if (s == null || s.isEmpty()) {
                throw new MimiException.InsufficientParameters(MimiException.INSUFFICIENT_DEADLINE_PARAMETERS_MSG);
            }
        }

        if (inputs[1].strip().isBlank()){
            throw new MimiException.InsufficientParameters(MimiException.INSUFFICIENT_DEADLINE_PARAMETERS_MSG);
        }

        String taskName = inputs[0];
        String dueDate = inputs[1].strip();

        return new Deadline(taskName, dueDate);

    }
}
