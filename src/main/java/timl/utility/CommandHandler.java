package timl.utility;

import timl.exceptions.EmptyException;
import timl.exceptions.TimException;
import timl.task.TaskManager;

/**
 * Handles the user commands
 */
public class CommandHandler {

    /**
     * Takes in response of the user and extracts the command and message to be executed by the Taskmanager
     *
     * @param response text that the user has input into the TimL
     */
    public static void respondToCommand(String response){
        String command = TextParser.extractCommand(response);
        String message = TextParser.extractMessage(response);
        int taskIndex;
        switch (command) {
            case "list":
                TaskManager.printList();
                break;
            case "mark":
                try {
                    taskIndex = Integer.parseInt(message) - 1;
                    TaskManager.mark(taskIndex);
                } catch (NumberFormatException | TimException e){
                    Printer.printInvalidNumber();
                }
                break;
            case "unmark":
                try {
                    taskIndex = Integer.parseInt(message) - 1;
                    TaskManager.unMark(taskIndex);
                } catch (NumberFormatException | TimException e){
                    Printer.printInvalidNumber();
                }
                break;
            case "todo":
                try {
                    TaskManager.addToDo(message);
                } catch (ArrayIndexOutOfBoundsException e){
                    Printer.printTaskOverflow();
                } catch (EmptyException e){
                    Printer.printEmptyTodoCommand();
                }
                break;
            case "deadline":
                try {
                    TaskManager.addDeadline(message);
                } catch (ArrayIndexOutOfBoundsException e){
                    Printer.printTaskOverflow();
                } catch (EmptyException | IndexOutOfBoundsException e) {
                    Printer.printInvalidDeadline();
                }
                break;
            case "event":
                try {
                    TaskManager.addEvent(message);
                } catch (ArrayIndexOutOfBoundsException e) {
                    Printer.printTaskOverflow();
                } catch (EmptyException | IndexOutOfBoundsException e) {
                    Printer.printInvalidEvent();
                }
                break;
            case "delete":
                try {
                    taskIndex = Integer.parseInt(message) - 1;
                    TaskManager.delete(taskIndex);
                } catch (NumberFormatException | TimException e){
                    Printer.printInvalidDelete();
                }
                break;
            case "find":
                TaskManager.find(message);
                break;
            default:
                Printer.printDefaultError();
                break;
        }
    }
}
