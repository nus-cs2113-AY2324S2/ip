package jeff;

import jeff.commands.ByeCommand;
import jeff.commands.DeadlineCommand;
import jeff.commands.DeleteCommand;
import jeff.commands.EventCommand;
import jeff.commands.ListCommand;
import jeff.commands.MarkCommand;
import jeff.commands.TodoCommand;
import jeff.commands.UnmarkCommand;
import jeff.exceptions.InvalidCommandException;
import jeff.exceptions.InvalidDeadlineSyntaxException;
import jeff.exceptions.InvalidDeleteSyntaxException;
import jeff.exceptions.InvalidEventSyntaxException;
import jeff.exceptions.InvalidMarkSyntaxException;
import jeff.exceptions.InvalidTodoSyntaxException;
import jeff.exceptions.InvalidUnmarkSyntaxException;
import jeff.exceptions.UnableToDeleteException;
import jeff.exceptions.UnableToMarkException;
import jeff.exceptions.UnableToUnmarkException;

public class Parser {
    private static final int TODO_DESCRIPTION_INDEX = 5;
    private static final int DEADLINE_DESCRIPTION_INDEX = 9;
    private static final int DEADLINE_BY_INDEX_LENGTH = 4;
    private static final int EVENT_DESCRIPTION_INDEX = 6;
    private static final int EVENT_FROM_INDEX_LENGTH = 6;
    private static final int EVENT_TO_INDEX_LENGTH = 4;
    private static final int MARK_INDEX = 5;
    private static final int UNMARK_INDEX = 7;
    private static final String TODO_STRING = "todo";
    private static final String BY_STRING = " /by ";
    private static final String FROM_STRING = " /from ";
    private static final String TO_STRING = " /to ";
    private static final String MARK_STRING = "mark";
    private static final String UNMARK_STRING = "unmark";
    private static final String DELETE_STRING = "delete";

    public static Command parseCommand(String userInput) throws
            InvalidCommandException,
            InvalidTodoSyntaxException,
            InvalidDeadlineSyntaxException,
            InvalidEventSyntaxException,
            InvalidMarkSyntaxException,
            InvalidUnmarkSyntaxException,
            InvalidDeleteSyntaxException,
            UnableToMarkException,
            UnableToUnmarkException,
            UnableToDeleteException {
        userInput = userInput.trim();
        if (userInput.equals("list")) {
            return new ListCommand();
        } else if (userInput.equals("todo") || userInput.startsWith("todo ")) {
            return parseTodo(userInput);
        } else if (userInput.equals("deadline") || userInput.startsWith("deadline ")) {
            return parseDeadline(userInput);
        } else if (userInput.equals("event") || userInput.startsWith("event ")) {
            return parseEvent(userInput);
        } else if (userInput.equals("mark") || userInput.startsWith("mark ")) {
            return parseMark(userInput);
        } else if (userInput.equals("unmark") || userInput.startsWith("unmark ")) {
            return parseUnmark(userInput);
        } else if (userInput.equals("delete") || userInput.startsWith("delete ")) {
            return parseDelete(userInput);
        } else if (userInput.equals("bye")) {
            return new ByeCommand();
        } else {
            throw new InvalidCommandException();
        }
    }

    private static Command parseTodo(String userInput) throws InvalidTodoSyntaxException {
        if (userInput.equals(TODO_STRING)) {
            throw new InvalidTodoSyntaxException();
        }
        String description = userInput.substring(TODO_DESCRIPTION_INDEX);
        return new TodoCommand(description);
    }

    private static Command parseDeadline(String userInput) throws InvalidDeadlineSyntaxException {
        int byIndex = userInput.indexOf(BY_STRING) + 1;
        if (byIndex == 0) {
            throw new InvalidDeadlineSyntaxException();
        }
        String description = userInput.substring(DEADLINE_DESCRIPTION_INDEX, byIndex);
        String by = userInput.substring(byIndex + DEADLINE_BY_INDEX_LENGTH);
        if (description.isEmpty()) {
            throw new InvalidDeadlineSyntaxException();
        }
        return new DeadlineCommand(description, by);
    }

    private static Command parseEvent(String userInput) throws InvalidEventSyntaxException {
        int fromIndex = userInput.indexOf(FROM_STRING) + 1;
        int toIndex = userInput.indexOf(TO_STRING) + 1;
        if (fromIndex == 0 || toIndex == 0 || fromIndex >= toIndex) {
            throw new InvalidEventSyntaxException();
        }
        String description = userInput.substring(EVENT_DESCRIPTION_INDEX, fromIndex);
        String from = userInput.substring(fromIndex + EVENT_FROM_INDEX_LENGTH, toIndex);
        String to = userInput.substring(toIndex + EVENT_TO_INDEX_LENGTH);
        if (description.isEmpty() || from.isEmpty() || to.isEmpty()) {
            throw new InvalidEventSyntaxException();
        }
        return new EventCommand(description, from, to);
    }

    private static Command parseMark(String userInput) throws UnableToMarkException, InvalidMarkSyntaxException {
        if (TaskList.isEmpty()) {
            throw new UnableToMarkException();
        }
        if (userInput.equals(MARK_STRING)) {
            throw new InvalidMarkSyntaxException();
        }
        try {
            int currentIndex = Integer.parseInt(userInput.substring(MARK_INDEX)) - 1;
            Task currentTask = TaskList.get(currentIndex);
            return new MarkCommand(currentIndex, currentTask);
        } catch (Exception e) {
            throw new InvalidMarkSyntaxException();
        }
    }

    private static Command parseUnmark(String userInput) throws UnableToUnmarkException, InvalidUnmarkSyntaxException {
        if (TaskList.isEmpty()) {
            throw new UnableToUnmarkException();
        }
        if (userInput.equals(UNMARK_STRING)) {
            throw new InvalidUnmarkSyntaxException();
        }
        try {
            int currentIndex = Integer.parseInt(userInput.substring(UNMARK_INDEX)) - 1;
            Task currentTask = TaskList.get(currentIndex);
            return new UnmarkCommand(currentIndex, currentTask);
        } catch (Exception e) {
            throw new InvalidUnmarkSyntaxException();
        }
    }

    private static Command parseDelete(String userInput) throws UnableToDeleteException, InvalidDeleteSyntaxException {
        if (TaskList.isEmpty()) {
            throw new UnableToDeleteException();
        }
        if (userInput.equals(DELETE_STRING)) {
            throw new InvalidDeleteSyntaxException();
        }
        try {
            int currentIndex = Integer.parseInt(userInput.substring(UNMARK_INDEX)) - 1;
            Task currentTask = TaskList.get(currentIndex);
            return new DeleteCommand(currentIndex, currentTask);
        } catch (Exception e) {
            throw new InvalidDeleteSyntaxException();
        }
    }
}
