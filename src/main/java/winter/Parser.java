package winter;

import winter.checkedexceptions.*;
import winter.commands.*;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

import static winter.checkedexceptions.Exceptions.*;


public class Parser {
    public static Command parse(String fullCommand) {
        switch (fullCommand) {
        // Cases include farewell and list commands
        case "bye":
        case "Bye":
        case "BYE":
            return new ExitCommand();
        case "list":
        case "List":
        case "LIST":
            return new ListCommand();

        }
        try {
            String[] commandWordArray = fullCommand.split(" ");
            String commandWord = commandWordArray[0];
            int indexFirstSpace = fullCommand.indexOf(" ");
            String commandArgs = fullCommand.substring(indexFirstSpace+1);
            switch (commandWord) {
            case TodoCommand.COMMAND_WORD:
                return prepareTodo(commandWordArray, commandArgs);
            case DeadlineCommand.COMMAND_WORD:
                return prepareDeadline(commandWordArray,commandArgs);
            case EventCommand.COMMAND_WORD:
                return prepareEvent(commandWordArray,commandArgs);
            case DeleteCommand.COMMAND_WORD:
                return prepareDelete(commandWordArray);
            // Cases for marking tasks
            case MarkCommand.COMMAND_WORD:
                return prepareMark(commandArgs);

            case UnmarkCommand.COMMAND_WORD:
                return prepareUnmark(commandArgs);
            case FindCommand.COMMAND_WORD:
                return prepareFind(commandWordArray,commandArgs);
            case "":
                handleEmptyString();
                return new HelpCommand();
            default:
                handleInvalidCommand();
                return new HelpCommand();
            }
        } catch (InvalidTodoException e){
            UI.handleCheckedExceptions(INVALIDTODO);
        } catch (InvalidDeadlineException e) {
            UI.handleCheckedExceptions(INVALIDDEADLINE);
        } catch (InvalidEventException e) {
            UI.handleCheckedExceptions(INVALIDEVENT);
        } catch (EmptyCommandException e) {
            UI.handleCheckedExceptions(EMPTYCOMMAND);
        } catch (InvalidDeleteException e) {
            UI.handleCheckedExceptions(INVALIDDELETE);
        } catch (InvalidCommandException e) {
            UI.handleCheckedExceptions(INVALIDCOMMAND);
        } catch (InvalidFindException e) {
            UI.handleCheckedExceptions(INVALIDFIND);
        }
        return new HelpCommand();
    }

    private static Command prepareFind(String[] commandWordArray, String commandArgs) throws InvalidFindException {
        boolean isValidFind = verifyFind(commandWordArray);
        if (isValidFind) {
            return new FindCommand(commandArgs);
        }
        return new HelpCommand();
    }

    private static boolean verifyFind(String[] commandWordArray) throws InvalidFindException {
        boolean isValidFind = true;
        if (commandWordArray.length < 2) {
            throw new InvalidFindException();
        }
        return isValidFind;
    }

    private static Command prepareUnmark(String commandArgs) {
        int taskUnmarkNum = Integer.parseInt(commandArgs);
        return new UnmarkCommand(taskUnmarkNum);
    }

    private static Command prepareMark(String commandArgs) {
        int taskMarkNum = Integer.parseInt(commandArgs);
        return new MarkCommand(taskMarkNum);
    }

    private static Command prepareDelete(String[] commandWordArray) throws InvalidDeleteException{
        boolean isValidDelete = verifyDelete(commandWordArray);
        int taskDeleteNum = Integer.parseInt(commandWordArray[1]);
        if (isValidDelete) {
            return new DeleteCommand(taskDeleteNum);
        }
        return new HelpCommand();
    }

    private static Command prepareTodo(String[] commandWordArray, String commandArgs) throws InvalidTodoException {
        boolean isValidTodo = verifyTodo(commandWordArray);
        if (isValidTodo) {
            return new TodoCommand(commandArgs);
        }
        return new HelpCommand();
    }

    private static Command prepareDeadline(String[] commandWordArray, String commandArgs) throws InvalidDeadlineException {
        boolean isValidDeadline = verifyDeadline(commandWordArray);
        if (isValidDeadline) {
            int byKeywordIndex = commandArgs.indexOf("/by ");
            int deadlineIndex = 1;
            String deadline = (commandArgs.split("/by "))[deadlineIndex];
            System.out.println(deadline);
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm");
            LocalDateTime deadlineTimeObj = null;
            try {
                deadlineTimeObj = LocalDateTime.parse(deadline, formatter);
            } catch (DateTimeParseException e) {
                System.out.println("Oh no! There is a problem with your deadline. It should be in the format of (yyyy-mm-dd HH:mm)");
                return new HelpCommand();
            }
            String deadlineName = commandArgs.substring(0,byKeywordIndex);
            return new DeadlineCommand(deadlineName,deadlineTimeObj);
        }
        return new HelpCommand();
    }

    private static Command prepareEvent(String[] commandWordArray, String commandArgs) throws InvalidEventException {
        boolean isValidEvent = verifyEvent(commandWordArray);
        if (isValidEvent) {
            int firstSlashIndex = commandArgs.indexOf("/");
            int numCharsFromKeyword = 5;
            String startAndEndTime = commandArgs.substring(firstSlashIndex + numCharsFromKeyword);
            int secondSlashIndex = startAndEndTime.indexOf("/");
            String startTime = startAndEndTime.substring(0,secondSlashIndex);
            int numCharsToKeyword = 3;
            String endTime = startAndEndTime.substring(secondSlashIndex + numCharsToKeyword);
            String eventName = commandWordArray[1];
            return new EventCommand(eventName,startTime,endTime);
        }
        return new HelpCommand();
    }

    public static boolean verifyTodo(String[] commandWords) throws InvalidTodoException {
        int numValidTodoArgs = 2;
        if (commandWords.length < numValidTodoArgs) {
            throw new InvalidTodoException();
        }
        return true;
    }

    public static boolean verifyDeadline(String[] commandWords) throws InvalidDeadlineException {
        boolean isValidDeadline = false;

        for (String commandWord : commandWords) {
            if (commandWord.equals("/by")) {
                isValidDeadline = true;
                break;
            }
        }

        int numValidDeadlineArgs = 5;
        if (commandWords.length < numValidDeadlineArgs || !isValidDeadline) {
            throw new InvalidDeadlineException();
        }
        return isValidDeadline;
    }



    public static boolean verifyEvent(String[] commandWords) throws InvalidEventException {
        boolean isValidEvent = false;
        boolean hasValidStart = false;
        boolean hasValidEnd = false;
        for (String commandWord : commandWords) {
            if (commandWord.equals("/from")) {
                hasValidStart = true;
            }
            if (commandWord.equals("/to")) {
                hasValidEnd = true;
            }
        }
        if (hasValidStart && hasValidEnd) {
            isValidEvent = true;
        }
        int numValidEventArgs = 6;
        if (commandWords.length < numValidEventArgs || !isValidEvent) {
            throw new InvalidEventException();
        }
        return isValidEvent;
    }

    public static boolean verifyDelete(String[] commandWords) throws InvalidDeleteException {
        if (commandWords.length < 2 || !isInteger(commandWords[1])) {
            throw new InvalidDeleteException();
        }
        return true;
    }

    public static boolean isInteger (String strNum) {
        if (strNum == null) {
            return false;
        }
        try {
            int i  = Integer.parseInt(strNum);
        } catch (NumberFormatException nfe) {
            return false;
        }
        return true;
    }

    public static void handleEmptyString () throws EmptyCommandException {
        throw new EmptyCommandException();
    }

    public static void handleInvalidCommand () throws InvalidCommandException {
        throw new InvalidCommandException();
    }


}
