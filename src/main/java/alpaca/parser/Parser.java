package alpaca.parser;

import java.util.Scanner;

import alpaca.ui.Ui;
import alpaca.logic.LogicManager;
import alpaca.exceptions.EmptyTaskDescriptionException;
import alpaca.exceptions.InvalidCommandException;
public class Parser {
    private Scanner scanner;

    public Parser() {
        this.scanner = new Scanner(System.in);
    }

    public void listenForInput(LogicManager logicManager){
        while (scanner.hasNextLine()) {
            try {
                String receivedMessage = scanner.nextLine().trim();
                String[] commandParts = receivedMessage.split(" ", 2);
                String command = commandParts[0];
                String details = commandParts.length > 1 ? commandParts[1] : "";

                // Decode the details based on the command type
                switch (command) {
                case "todo":
                    if (details.trim().isEmpty()) {
                        throw new EmptyTaskDescriptionException("The description of a todo cannot be empty.");
                    }
                    logicManager.addTodo(details);
                    break;
                case "deadline":
                    String[] deadlineParts = details.split(" /by ", 2);
                    if (deadlineParts.length != 2) {
                        throw new EmptyTaskDescriptionException("The description of a todo cannot be empty.");
                    }
                    logicManager.addDeadline(deadlineParts[0], deadlineParts[1]);
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
                    logicManager.addEvent(eventParts[0], timeParts[0], timeParts[1]);
                    break;
                default:
                    // For commands that do not require special decoding, pass them directly
                    logicManager.executeCommand(command, details);
                    break;
                }
            } catch (InvalidCommandException e) {
                Ui.printErrorMessage(e.toString());
            } catch (EmptyTaskDescriptionException e){
                Ui.printErrorMessage(e.getMessage());
            }
        }
    }
}
