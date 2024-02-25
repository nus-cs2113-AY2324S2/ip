package jeff;

import jeff.exceptions.InvalidDeadlineSyntaxException;
import jeff.exceptions.InvalidDeleteSyntaxException;
import jeff.exceptions.InvalidEventSyntaxException;
import jeff.exceptions.InvalidMarkSyntaxException;
import jeff.exceptions.InvalidTodoSyntaxException;
import jeff.exceptions.InvalidUnmarkSyntaxException;

public class CommandHandler {
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

    public static void handleList() {
        Printer.printTasks();
    }

    public static void handleTodo(String userInput) throws InvalidTodoSyntaxException {
        if (userInput.equals(TODO_STRING)) {
            throw new InvalidTodoSyntaxException();
        }
        String description = userInput.substring(TODO_DESCRIPTION_INDEX);
        Todo todo = new Todo(description);
        TaskList.add(todo);
        Storage.appendTask(todo);
        Printer.printAddTask();
    }

    public static void handleDeadline(String userInput) throws InvalidDeadlineSyntaxException {
        int byIndex = userInput.indexOf(BY_STRING) + 1;
        if (byIndex == 0) {
            throw new InvalidDeadlineSyntaxException();
        }
        String description = userInput.substring(DEADLINE_DESCRIPTION_INDEX, byIndex);
        String by = userInput.substring(byIndex + DEADLINE_BY_INDEX_LENGTH);
        if (description.isEmpty()) {
            throw new InvalidDeadlineSyntaxException();
        }
        Deadline deadline = new Deadline(description, by);
        TaskList.add(deadline);
        Storage.appendTask(deadline);
        Printer.printAddTask();
    }

    public static void handleEvent(String userInput) throws InvalidEventSyntaxException {
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
        Event event = new Event(description, from, to);
        TaskList.add(event);
        Storage.appendTask(event);
        Printer.printAddTask();
    }

    public static void handleMark(String userInput) throws InvalidMarkSyntaxException {
        if (TaskList.isEmpty()) {
            Printer.printUnableToMark();
            return;
        }
        if (userInput.equals(MARK_STRING)) {
            throw new InvalidMarkSyntaxException();
        }
        try {
            int currentIndex = Integer.parseInt(userInput.substring(MARK_INDEX)) - 1;
            Task currentTask = TaskList.get(currentIndex);
            currentTask.mark();
            Storage.updateMarkStatus(currentIndex, true);
            Printer.printMarkTask(currentTask);
        } catch (Exception e) {
            throw new InvalidMarkSyntaxException();
        }
    }

    public static void handleUnmark(String userInput) throws InvalidUnmarkSyntaxException {
        if (TaskList.isEmpty()) {
            Printer.printUnableToUnmark();
            return;
        }
        if (userInput.equals(UNMARK_STRING)) {
            throw new InvalidUnmarkSyntaxException();
        }
        try {
            int currentIndex = Integer.parseInt(userInput.substring(UNMARK_INDEX)) - 1;
            Task currentTask = TaskList.get(currentIndex);
            currentTask.unmark();
            Storage.updateMarkStatus(currentIndex, false);
            Printer.printUnmarkTask(currentTask);
        } catch (Exception e) {
            throw new InvalidUnmarkSyntaxException();
        }
    }

    public static void handleDelete(String userInput) throws InvalidDeleteSyntaxException {
        if (TaskList.isEmpty()) {
            Printer.printUnableToDelete();
            return;
        }
        if (userInput.equals(DELETE_STRING)) {
            throw new InvalidDeleteSyntaxException();
        }
        try {
            int currentIndex = Integer.parseInt(userInput.substring(UNMARK_INDEX)) - 1;
            Task deletedTask = TaskList.remove(currentIndex);
            Storage.deleteTask(currentIndex);
            Printer.printDeleteTask(deletedTask);
        } catch (Exception e) {
            throw new InvalidDeleteSyntaxException();
        }
    }

    public static void handleBye() {
        Printer.printBye();
    }
}
