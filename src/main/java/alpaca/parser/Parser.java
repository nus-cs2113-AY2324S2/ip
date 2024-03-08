package alpaca.parser;

import alpaca.commands.*;
import alpaca.storage.Storage;
import alpaca.ui.Ui;
import alpaca.exceptions.EmptyTaskDescriptionException;
import alpaca.exceptions.InvalidCommandException;
import alpaca.exceptions.InvalidTimeException;
import alpaca.exceptions.AlpacaException;
import alpaca.tasks.*;

/**
 * Parses user input to execute corresponding commands.
 */
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;

public class Parser {

    private final TaskList tasks;

    /**
     * Initializes a parser with the given task list.
     *
     * @param tasks The task list to be manipulated based on user commands.
     */
    public Parser(TaskList tasks) {
        this.tasks = tasks;
    }

    /**
     * Parses and executes a command based on the user input.
     *
     * Splits the input into a command and its details, handling various task types and actions.
     * Errors during parsing lead to appropriate error messages.
     *
     * @param receivedMessage The complete user input string.
     */
    public void parseCommand(String receivedMessage) {
        try {
            String[] commandParts = receivedMessage.split(" ", 2);
            String command = commandParts[0];
            String details = commandParts.length > 1 ? commandParts[1] : "";

            // Decode the details based on the command type
            switch (command) {
            case "todo":
                parseTodoCommand(details);
                break;
            case "deadline":
                parseDeadlineCommand(details);
                break;
            case "event":
                parseEventCommand(details);
                break;
            default:
                // For commands that do not require special decoding, pass them directly
                executeCommand(command, details);
                break;
            }
        } catch (AlpacaException e) {
            Ui.printErrorMessage(e.toString());
        }
        Storage.startFileWriter(tasks.saveTask());
    }

    /**
     * Parses and executes a 'todo' command by creating a new Todo task.
     *
     * Extracts the task description from the command details. If the description is empty,
     * throws an EmptyTaskDescriptionException. Otherwise, creates a new Todo task and adds it to the task list.
     *
     * @param details The command details containing the description of the todo task.
     * @throws EmptyTaskDescriptionException if the todo description is empty.
     */
    private void parseTodoCommand(String details) throws EmptyTaskDescriptionException {
        if (details.trim().isEmpty()) {
            throw new EmptyTaskDescriptionException("The description of a todo cannot be empty.");
        }
        AddCommand addTodoCommand = new AddCommand(new Todo(details), tasks);
        addTodoCommand.execute();
    }

    /**
     * Parses and executes an 'event' command with detailed date and time.
     *
     * Validates the input and creates an event task, considering start and end times.
     * Throws exceptions for invalid input.
     *
     * @param details The command details including event description and timing.
     * @throws AlpacaException if the event details are invalid.
     */
    private void parseEventCommand(String details) throws AlpacaException {
        String[] eventParts = details.split(" /from ", 2);
        if (eventParts.length < 2 || eventParts[0].trim().isEmpty()) {
            throw new EmptyTaskDescriptionException("The title of an event cannot be empty.");
        }

        String[] timeParts = eventParts[1].split(" /to ", 2);
        if (timeParts.length != 2) {
            throw new EmptyTaskDescriptionException("Both start time and end time of an event must be provided.");
        }

        String[] startDateTimeParts = timeParts[0].split(" ", 2);
        String[] endDateTimeParts = timeParts[1].split(" ", 2);

        AddCommand addEventCommand;

        if (startDateTimeParts.length == 2 && endDateTimeParts.length == 2) {
            LocalDate startDay = LocalDate.parse(startDateTimeParts[0]);
            LocalTime startTime = LocalTime.parse(startDateTimeParts[1], DateTimeFormatter.ofPattern("HH:mm"));
            LocalDate endDay = LocalDate.parse(endDateTimeParts[0]);
            LocalTime endTime = LocalTime.parse(endDateTimeParts[1], DateTimeFormatter.ofPattern("HH:mm"));
            addEventCommand = new AddCommand(new Event(eventParts[0], startDay, startTime, endDay, endTime), tasks);
        }
        else if (startDateTimeParts.length == 1 && endDateTimeParts.length == 1) {
            LocalDate startDay = LocalDate.parse(startDateTimeParts[0]);
            LocalDate endDay = LocalDate.parse(endDateTimeParts[0]);
            addEventCommand = new AddCommand(new Event(eventParts[0], startDay, endDay), tasks);
        } else {
            throw new InvalidTimeException();
        }
        addEventCommand.execute();
    }

    /**
     * Parses and executes a 'deadline' command with a specific due date and time.
     *
     * Validates the input and creates a deadline task, considering the due date and time.
     * Throws exceptions for invalid input.
     *
     * @param details The command details including deadline description and due timing.
     * @throws AlpacaException if the deadline details are invalid.
     */
    private void parseDeadlineCommand(String details) throws AlpacaException {
        String[] deadlineParts = details.split(" /by ", 2);
        String[] timeParts = deadlineParts[1].split(" ");
        if (deadlineParts.length < 2 || deadlineParts[0].trim().isEmpty()) {
            throw new EmptyTaskDescriptionException("The description of a deadline cannot be empty.");
        }
        AddCommand addDeadlineCommand;
        if (timeParts.length == 2) {
            LocalDate date = LocalDate.parse(timeParts[0].strip());
            LocalTime minute = LocalTime.parse(timeParts[1].strip());
            if (!LocalDateTime.of(date, minute).isAfter(LocalDateTime.now())) {
                throw new InvalidTimeException();
            }
            addDeadlineCommand = new AddCommand(new Deadline(deadlineParts[0], date, minute), tasks);
        } else {
            LocalDate date = LocalDate.parse(timeParts[0].strip());
            if (!date.isAfter(LocalDate.now())) {
                throw new InvalidTimeException();
            }
            addDeadlineCommand = new AddCommand(new Deadline(deadlineParts[0], date), tasks);
        }
        addDeadlineCommand.execute();
    }

    /**
     * Executes basic commands identified by the first word in the user input.
     *
     * Supports operations like listing tasks, marking/unmarking tasks, deleting tasks, and finding tasks.
     * Throws InvalidCommandException for unrecognized commands.
     *
     * @param command The first word of the user input, identifying the command type.
     * @param details The rest of the user input, providing additional command details.
     * @throws InvalidCommandException if the command is unrecognized.
     */
    public void executeCommand (String command, String details) throws InvalidCommandException{
        switch (command) {
        case "list":
            ListCommand listCommand = new ListCommand(tasks);
            listCommand.execute();
            break;
        case "mark":
        case "unmark":
            int index = Integer.parseInt(details) - 1;
            boolean isMark = command.equals("mark");
            MarkUnmarkCommand markUnmarkCommand = new MarkUnmarkCommand(index, isMark, tasks);
            markUnmarkCommand.execute();
            break;
        case "delete":
            index = Integer.parseInt(details) - 1;
            DeleteCommand deleteCommand = new DeleteCommand(index, tasks);
            deleteCommand.execute();
            break;
        case "bye":
            ExitCommand exitCommand = new ExitCommand();
            exitCommand.execute();
            break;
        case "find":
            FindCommand findCommand = new FindCommand(details, tasks);
            findCommand.execute();
            break;
        default:
            throw new InvalidCommandException();
        }
    }

}
