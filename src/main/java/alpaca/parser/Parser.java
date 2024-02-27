package alpaca.parser;

import alpaca.commands.*;
import alpaca.storage.Storage;
import alpaca.ui.Ui;
import alpaca.exceptions.EmptyTaskDescriptionException;
import alpaca.exceptions.InvalidCommandException;
import alpaca.tasks.*;

public class Parser {

    private final TaskList tasks;

    public Parser(TaskList tasks) {
        this.tasks = tasks;
    }

    public void parseCommand(String receivedMessage) {
        try {
            String[] commandParts = receivedMessage.split(" ", 2);
            String command = commandParts[0];
            String details = commandParts.length > 1 ? commandParts[1] : "";

            // Decode the details based on the command type
            switch (command) {
            case "todo":
                if (details.trim().isEmpty()) {
                    throw new EmptyTaskDescriptionException("The description of a todo cannot be empty.");
                }
                AddCommand addTodoCommand = new AddCommand(new Todo(details), tasks);
                addTodoCommand.execute();
                break;
            case "deadline":
                String[] deadlineParts = details.split(" /by ", 2);
                if (deadlineParts.length != 2) {
                    throw new EmptyTaskDescriptionException("The description of a todo cannot be empty.");
                }
                AddCommand addDeadlineCommand = new AddCommand(new Deadline(deadlineParts[0], deadlineParts[1]), tasks);
                addDeadlineCommand.execute();
                break;
            case "event":
                String[] eventParts = details.split(" /from ", 2);
                if (eventParts.length != 2) {
                    throw new EmptyTaskDescriptionException("The title, start time, or end time of an event cannot be empty.");
                }
                String[] timeParts = eventParts[1].split(" /to ", 2);
                if (timeParts.length != 2) {
                    throw new EmptyTaskDescriptionException("The title, start time, or end time of an event cannot be empty.");
                }
                AddCommand addEventCommand = new AddCommand(new Event(eventParts[0], timeParts[0], timeParts[1]), tasks);
                addEventCommand.execute();
                break;
            default:
                // For commands that do not require special decoding, pass them directly
                executeCommand(command, details);
                break;
            }
        } catch (InvalidCommandException e) {
            Ui.printErrorMessage(e.toString());
        } catch (EmptyTaskDescriptionException e){
            Ui.printErrorMessage(e.getMessage());
        }
        Storage.startFileWriter(tasks.saveTask());
    }

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
        default:
            throw new InvalidCommandException();
        }
    }

}
