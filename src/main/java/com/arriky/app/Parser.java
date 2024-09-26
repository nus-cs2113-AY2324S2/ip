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
        boolean isTakingNextCommand = true;

        switch (arguments[0]) {
        case "bye":
            isTakingNextCommand = executeBye();
            break;
        case "list":
           executeList(arguments, taskList);
           break;
        case "mark":
            executeMark(arguments, taskList);
            break;
        case "unmark":
            executeUnMark(arguments, taskList);
            break;
        case "todo":
            executeAddTodo(arguments, taskList);
            break;
        case "deadline":
            executeAddDeadline(arguments, taskList);
            break;
        case "event":
            executeAddEvent(arguments, taskList);
            break;
        case "delete":
            executeDelete(arguments, taskList);
            break;
        case "find":
            executeFind(arguments, taskList);
            break;

        // if the user's command cannot be interpreted as any valid operation, notify the user
        default:
            throw new ArrikyRuntimeException(ErrorMessage.INVALID_COMMAND);
        }

        return isTakingNextCommand;
    }

    private static Boolean executeBye() {
            UI.endSession();
            return false;
    }

    private static void executeList(String[] arguments, TaskList taskList) throws ArrikyRuntimeException {
        try {
            if (arguments.length != 1) {
                throw new IncorrectArgumentAmountException();
            }
            ArrayList<String> entries = taskList.find("");
            UI.listAllTasks(entries);
        } catch (IncorrectArgumentAmountException e) {
            throw new ArrikyRuntimeException(ErrorMessage.INCORRECT_ARGUMENT_AMOUNT_0);
        }
    }

    private static void executeMark(String[] arguments, TaskList taskList) throws ArrikyRuntimeException {
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
    }

    private static void executeUnMark(String[] arguments, TaskList taskList) throws ArrikyRuntimeException {
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
    }

    private static void executeAddDeadline(String[] arguments, TaskList taskList) throws ArrikyRuntimeException {
        try {
            String[] segments = String.join(" ", arguments).split(" /by ");
            taskList.addDeadline(segments[0].substring(9), segments[1], false);
            UI.printInsertionAcknowledgement(taskList);
        } catch (StringIndexOutOfBoundsException | ArrayIndexOutOfBoundsException e) {
            throw new ArrikyRuntimeException(ErrorMessage.INVALID_DEADLINE_FORMAT);
        }
    }

    private static void executeAddTodo(String[] arguments, TaskList taskList) throws ArrikyRuntimeException {
        String taskName = String.join(" ", arguments).substring(5);
        taskList.addToDo(taskName, false);
        UI.printInsertionAcknowledgement(taskList);
    }

    private static void executeAddEvent(String[] arguments, TaskList taskList) throws ArrikyRuntimeException {
        try {
            String[] segments = String.join(" ", arguments).split(" /");
            taskList.addEvent(segments[0].substring(6), segments[1].substring(5), segments[2].substring(3), false);
            UI.printInsertionAcknowledgement(taskList);
        } catch (StringIndexOutOfBoundsException | ArrayIndexOutOfBoundsException e) {
            throw new ArrikyRuntimeException(ErrorMessage.INVALID_EVENT_FORMAT);
        }
    }

    private static void executeDelete(String[] arguments, TaskList taskList) throws ArrikyRuntimeException {
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
    }

    private static void executeFind(String[] arguments, TaskList taskList) throws ArrikyRuntimeException {
        try {
            if (arguments.length == 1) {
                throw new IncorrectArgumentAmountException();
            }
            String keywords = String.join(" ", arguments).substring(5);
            ArrayList<String> entries = taskList.find(keywords);
            UI.displayFindResults(entries);
        } catch (IncorrectArgumentAmountException e) {
            throw new ArrikyRuntimeException(ErrorMessage.INCORRECT_FIND_ARGUMENT);
        }
    }
}
