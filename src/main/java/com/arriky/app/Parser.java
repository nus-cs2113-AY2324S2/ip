package com.arriky.app;

import com.arriky.exception.ArrikyRuntimeException;
import com.arriky.exception.ErrorMessage;
import com.arriky.exception.IncorrectArgumentAmountException;
import com.arriky.task.TaskList;

import java.util.ArrayList;

/**
 * A class to inteprete user's input and execute the parsed commands.
 * @author Songyue Wang
 * @version 1.0
 */
public class Parser {

    /**
     * Parse and execute user's command.
     * @param rawCommand Single line the user typed in CLI.
     * @param taskList Instance of the tasklist used in the main programme.
     * @return <code>false</code> when the user has commanded to exit the app, <code>true</code> when the next command is expected to be received from the user.
     * @throws ArrikyRuntimeException If any runtime exceptino in parsing or executing the command is thrown.
     */
    public static boolean parseCommand(String rawCommand, TaskList taskList) throws ArrikyRuntimeException {
        String[] arguments = rawCommand.split(" ");

        switch (arguments[0]) {
        case "bye":
            try {
                if (arguments.length != 1) {
                    throw new IncorrectArgumentAmountException();
                }
                UI.endSession();
                return false;
            } catch (IncorrectArgumentAmountException e) {
                throw new ArrikyRuntimeException(ErrorMessage.INCORRECT_ARGUMENT_AMOUNT_0);
            }

        case "list":
            try {
                if (arguments.length != 1) {
                    throw new IncorrectArgumentAmountException();
                }
                ArrayList<String> entries = taskList.find("");
                UI.listAllTasks(entries);
            } catch (IncorrectArgumentAmountException e) {
                throw new ArrikyRuntimeException(ErrorMessage.INCORRECT_ARGUMENT_AMOUNT_0);
            }
            break;

        case "mark":
            try {
                if (arguments.length != 2) {
                    throw new IncorrectArgumentAmountException();
                }
                taskList.markDone(Integer.parseInt(arguments[1]) - 1);
                UI.printMarkDoneAcknowledgement(taskList,Integer.parseInt(arguments[1]) - 1);
            } catch (NumberFormatException e) {
                throw new ArrikyRuntimeException(ErrorMessage.INVALID_ID);
            } catch (IndexOutOfBoundsException e) {
                throw new ArrikyRuntimeException(ErrorMessage.ID_NOT_EXIST);
            } catch (IncorrectArgumentAmountException e) {
                throw new ArrikyRuntimeException(ErrorMessage.INCORRECT_ARGUMENT_AMOUNT_1);
            }
            break;

        case "unmark":
            try {
                if (arguments.length != 2) {
                    throw new IncorrectArgumentAmountException();
                }
                taskList.unmarkDone(Integer.parseInt(arguments[1]) - 1);
                UI.printUnmarkDoneAcknowledgement(taskList,Integer.parseInt(arguments[1]) - 1);
            } catch (NumberFormatException e) {
                throw new ArrikyRuntimeException(ErrorMessage.INVALID_ID);
            } catch (IndexOutOfBoundsException e) {
                throw new ArrikyRuntimeException(ErrorMessage.ID_NOT_EXIST);
            } catch (IncorrectArgumentAmountException e) {
                throw new ArrikyRuntimeException(ErrorMessage.INCORRECT_ARGUMENT_AMOUNT_1);
            }
            break;

        case "todo":
            String taskName = rawCommand.substring(5);
            taskList.addToDo(taskName, false);
            UI.printInsertionAcknowledgement(taskList);
            break;

        case "deadline":
            try {
                String[] segments = rawCommand.split(" /by ");
                taskList.addDeadline(segments[0].substring(9), segments[1], false);
                UI.printInsertionAcknowledgement(taskList);
            } catch (StringIndexOutOfBoundsException | ArrayIndexOutOfBoundsException e) {
                throw new ArrikyRuntimeException(ErrorMessage.INVALID_DEADLINE_FORMAT);
            }
            break;

        case "event":
            try {
                String[] segments = rawCommand.split(" /");
                taskList.addEvent(segments[0].substring(6), segments[1].substring(5), segments[2].substring(3), false);
                UI.printInsertionAcknowledgement(taskList);
            } catch (StringIndexOutOfBoundsException | ArrayIndexOutOfBoundsException e) {
                throw new ArrikyRuntimeException(ErrorMessage.INVALID_EVENT_FORMAT);
            }
            break;

        case "delete":
            try {
                if (arguments.length != 2) {
                    throw new IncorrectArgumentAmountException();
                }
                String summary = taskList.getSummaryByIndex(Integer.parseInt(arguments[1]) - 1);
                taskList.delete(Integer.parseInt(arguments[1]) - 1);
                UI.printDeletionAcknowledgement(taskList, summary);
            } catch (NumberFormatException e) {
                throw new ArrikyRuntimeException(ErrorMessage.INVALID_ID);
            } catch (IndexOutOfBoundsException e) {
                throw new ArrikyRuntimeException(ErrorMessage.ID_NOT_EXIST);
            } catch (IncorrectArgumentAmountException e) {
                throw new ArrikyRuntimeException(ErrorMessage.INCORRECT_ARGUMENT_AMOUNT_1);
            }
            break;

        case "find":
            try {
                if (arguments.length == 1) {
                    throw new IncorrectArgumentAmountException();
                }
                String keywords = rawCommand.substring(5);
                ArrayList<String> entries = taskList.find(keywords);
                UI.displayFindResults(entries);
            } catch (IncorrectArgumentAmountException e) {
                throw new ArrikyRuntimeException(ErrorMessage.INCORRECT_FIND_ARGUMENT);
            }
            break;

        // if the user's command cannot be interpreted as any valid operation, notify the user
        default:
            throw new ArrikyRuntimeException(ErrorMessage.INVALID_COMMAND);
        }

        return true;
    }
}
