package schmidt.parser;


import schmidt.command.AddCommand;
import schmidt.command.Command;
import schmidt.command.DeleteCommand;
import schmidt.command.ExitCommand;
import schmidt.command.FindCommand;
import schmidt.command.HelpCommand;
import schmidt.command.ListCommand;
import schmidt.command.MarkCommand;
import schmidt.command.UnmarkCommand;
import schmidt.exception.SchmidtException;
import schmidt.storage.Storage;
import schmidt.task.Deadline;
import schmidt.task.Event;
import schmidt.task.Task;
import schmidt.task.Todo;

/**
 * Represents a parser to parse user input into commands.
 */
public class Parser {

    private static final String NEW_LINE = "\n";
    private static final String INVALID_TASK_MESSAGE = "Task is invalid";
    private static final String WHITESPACE = "\\s+";

    /**
     * Parses a task into a line of storage.
     *
     * @param task the task.
     * @return the line of storage.
     * @throws SchmidtException if the task is invalid.
     */
    public static String parseTaskToStorage(Task task) throws SchmidtException {
        String taskLetter;
        String taskStatus;
        String taskDescription;
        String taskTime;

        // find the type of task and parse it accordingly
        if (task instanceof Todo) {
            taskLetter = Todo.LETTER;
            taskStatus = task.getStatus() ? Task.DONE : Task.NOT_DONE;
            taskDescription = task.getDescription();
            return taskLetter + Storage.DELIMITER + taskStatus + Storage.DELIMITER + taskDescription + NEW_LINE;
        } else if (task instanceof Deadline) {
            taskLetter = Deadline.LETTER;
            taskStatus = task.getStatus() ? Task.DONE : Task.NOT_DONE;
            taskDescription = task.getDescription();
            taskTime = ((Deadline) task).getBy();
            return taskLetter + Storage.DELIMITER + taskStatus + Storage.DELIMITER + taskDescription + Storage.DELIMITER + taskTime + NEW_LINE;
        } else if (task instanceof Event) {
            taskLetter = Event.LETTER;
            taskStatus = task.getStatus() ? Task.DONE : Task.NOT_DONE;
            taskDescription = task.getDescription();
            taskTime = ((Event) task).getFrom() + Storage.DELIMITER + ((Event) task).getTo();
            return taskLetter + Storage.DELIMITER + taskStatus + Storage.DELIMITER + taskDescription + Storage.DELIMITER + Storage.DELIMITER + taskTime + NEW_LINE;
        } else {
            throw new SchmidtException(INVALID_TASK_MESSAGE);
        }
    }

    /**
     * Parses a line of storage into a task.
     *
     * @param line the line of storage.
     * @return the task.
     * @throws SchmidtException if the line is invalid.
     */
    public static Task parseStorageToTask(String line) throws SchmidtException {

        String[] tokens = line.split(Storage.REGEX_DELIMITER);

        switch (tokens[Storage.TASK_TYPE_INDEX]) {
        case Todo.LETTER:
            Todo todo = new Todo(tokens[Storage.DESCRIPTION_INDEX]);
            if (tokens[Storage.IS_DONE_INDEX].equals(Task.DONE)) {
                todo.markAsDone();
            }
            return todo;
        case Deadline.LETTER:
            Deadline deadline = new Deadline(tokens[Storage.DESCRIPTION_INDEX], tokens[Storage.BY_INDEX]);
            if (tokens[Storage.IS_DONE_INDEX].equals(Task.DONE)) {
                deadline.markAsDone();
            }
            return deadline;
        case Event.LETTER:
            Event event = new Event(tokens[Storage.DESCRIPTION_INDEX], tokens[Storage.FROM_INDEX], tokens[Storage.TO_INDEX]);
            if (tokens[Storage.IS_DONE_INDEX].equals(Task.DONE)) {
                event.markAsDone();
            }
            return event;
        default:
            throw new SchmidtException(Storage.CORRUPTED_STORAGE_MESSAGE);
        }
    }

    /**
     * Parses the user input into a command.
     *
     * @param command the user input.
     * @return the command to be executed.
     * @throws SchmidtException if the command is invalid.
     */
    public static Command parseCommand(String command) throws SchmidtException {
        String[] trimmedAndSplitCommand = command.trim().split(WHITESPACE, 2);
        String caseInsensitiveCommandType = trimmedAndSplitCommand[Command.TYPE_INDEX].toLowerCase();
        String commandArguments = trimmedAndSplitCommand.length > 1 ? trimmedAndSplitCommand[1].trim() : "";
        switch (caseInsensitiveCommandType) {
        case ListCommand.COMMAND:
            return new ListCommand();
        case MarkCommand.COMMAND:
            int markIndex = parseIndexCommand(commandArguments, MarkCommand.COMMAND);
            return new MarkCommand(markIndex);
        case UnmarkCommand.COMMAND:
            int unmarkIndex = parseIndexCommand(commandArguments, UnmarkCommand.COMMAND);
            return new UnmarkCommand(unmarkIndex);
        case ExitCommand.COMMAND:
            return new ExitCommand();
        case DeleteCommand.COMMAND:
            int deleteIndex = parseIndexCommand(commandArguments, DeleteCommand.COMMAND);
            return new DeleteCommand(deleteIndex);
        case AddCommand.TODO_COMMAND:
            Todo todo = parseTodoCommand(commandArguments);
            return new AddCommand(todo);
        case AddCommand.DEADLINE_COMMAND:
            Deadline deadline = parseDeadlineCommand(commandArguments);
            return new AddCommand(deadline);
        case AddCommand.EVENT_COMMAND:
            Event event = parseEventCommand(commandArguments);
            return new AddCommand(event);
        case FindCommand.COMMAND:
            return new FindCommand(commandArguments);
        default:
            return new HelpCommand();
        }
    }

    /**
     * Splits the user input to get the description of the todo task.
     *
     * @param description the description of the todo task.
     * @return the todo task.
     * @throws SchmidtException if the command is invalid.
     */
    private static Todo parseTodoCommand(String description) throws SchmidtException {
        try {
            if (description.isEmpty()) {
                throw new SchmidtException(Todo.INCORRECT_FORMAT_MESSAGE);
            }

            return new Todo(description);
        } catch (Exception e) {
            throw new SchmidtException(Todo.INCORRECT_FORMAT_MESSAGE);
        }
    }

    /**
     * Splits the user input to get the description and by of the deadline task.
     *
     * @param commandArguments the user input.
     * @return the deadline task.
     * @throws SchmidtException if the command is invalid.
     */
    private static Deadline parseDeadlineCommand(String commandArguments) throws SchmidtException {
        try {
            commandArguments = " " + commandArguments + " ";
            String[] tokenizedDeadlineCommand = commandArguments.split(Deadline.REGEX_BY_DELIMITER);

            if (tokenizedDeadlineCommand.length != 2) {
                throw new SchmidtException(Deadline.INCORRECT_FORMAT_MESSAGE);
            }

            String description = tokenizedDeadlineCommand[Deadline.DESCRIPTION_INDEX].trim();
            String by = tokenizedDeadlineCommand[Deadline.BY_INDEX].trim();

            boolean isDescriptionEmpty = description.isEmpty();
            boolean isByEmpty = by.isEmpty();

            if (isDescriptionEmpty || isByEmpty) {
                throw new SchmidtException(Deadline.INCORRECT_FORMAT_MESSAGE);
            }

            return new Deadline(description, by);
        } catch (Exception e) {
            throw new SchmidtException(Deadline.INCORRECT_FORMAT_MESSAGE);
        }
    }

    /**
     * Splits the user input to get the description, from, and to of the event task.
     *
     * @param commandArguments the user input.
     * @return the event task.
     * @throws SchmidtException if the command is invalid.
     */
    private static Event parseEventCommand(String commandArguments) throws SchmidtException {
        try {
            String[] tokenizedEventCommand = commandArguments.split(Event.REGEX_FROM_DELIMITER + "|" + Event.REGEX_TO_DELIMITER);

            if (tokenizedEventCommand.length != 3) {
                throw new SchmidtException(Event.INCORRECT_FORMAT_MESSAGE);
            }

            String description = tokenizedEventCommand[Event.DESCRIPTION_INDEX].trim();
            String from = tokenizedEventCommand[Event.FROM_INDEX].trim();
            String to = tokenizedEventCommand[Event.TO_INDEX].trim();

            boolean isDescriptionEmpty = description.isEmpty();
            boolean isFromEmpty = from.isEmpty();
            boolean isToEmpty = to.isEmpty();

            if (isDescriptionEmpty || isFromEmpty || isToEmpty) {
                throw new SchmidtException(Event.INCORRECT_FORMAT_MESSAGE);
            }

            return new Event(description, from, to);
        } catch (Exception e) {
            throw new SchmidtException(Event.INCORRECT_FORMAT_MESSAGE);
        }
    }

    /**
     * Splits the user input to get the index of the inputted command.
     *
     * @param index the index of the command.
     * @return the index of the command as an integer.
     * @throws SchmidtException if the command is invalid.
     */
    private static int parseIndexCommand(String index, String commandType) throws SchmidtException {
        try {
            return Integer.parseInt(index) - 1;
        } catch (Exception e) {
            switch (commandType) {
            case MarkCommand.COMMAND:
                throw new SchmidtException(MarkCommand.INCORRECT_FORMAT_MESSAGE);
            case UnmarkCommand.COMMAND:
                throw new SchmidtException(UnmarkCommand.INCORRECT_FORMAT_MESSAGE);
            case DeleteCommand.COMMAND:
                throw new SchmidtException(DeleteCommand.INCORRECT_FORMAT_MESSAGE);
            }
        }
        return -1;
    }
}
