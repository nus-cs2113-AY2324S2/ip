package mimi.helper;

import mimi.classes.Deadline;
import mimi.classes.Event;
import mimi.classes.ToDo;
import mimi.exceptions.MimiException;

import static mimi.helper.Storage.DEADLINE_DELIMITER;

public class Parser {

    public Parser(){

    }

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

    public static Event processEventFromInput(String[] inputs) throws
            MimiException.InsufficientParameters, MimiException.IncorrectFormat {
        if (inputs.length != 2) {
            throw new MimiException.InsufficientParameters(MimiException.INSUFFICIENT_EVENT_PARAMETERS_MSG);
        }
        String eventDetails = inputs[1];
        return processEvent(eventDetails);
    }

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

    private static Event processEvent(String input) throws
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
                throw new MimiException.IncorrectFormat(MimiException.INCORRECT_EVENT_FORMAT_MSG);
            }

            return new Event(taskName, startDate, endDate);

        } catch (ArrayIndexOutOfBoundsException e) {
            throw new MimiException.InsufficientParameters(MimiException.INSUFFICIENT_EVENT_PARAMETERS_MSG);
        }
    }


    public static Deadline processDeadlineFromInput(String[] inputs) throws
            MimiException.InsufficientParameters, MimiException.IncorrectFormat {
        if (inputs.length < 2) {
            throw new MimiException.InsufficientParameters(MimiException.INSUFFICIENT_DEADLINE_PARAMETERS_MSG);
        }

        String[] splitInputs = inputs[1].split(DEADLINE_DELIMITER, 2);
        return processDeadline(splitInputs);
    }

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
