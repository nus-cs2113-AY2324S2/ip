package jeff;

import java.io.File;

public class CommandHandler {
    private static final int TODO_DESCRIPTION_INDEX = 5;
    private static final int DEADLINE_DESCRIPTION_INDEX = 9;
    private static final int DEADLINE_BY_INDEX_LENGTH = 4;
    private static final int EVENT_DESCRIPTION_INDEX = 6;
    private static final int EVENT_FROM_INDEX_LENGTH = 6;
    private static final int EVENT_TO_INDEX_LENGTH = 4;
    private static final int MARK_INDEX = 5;
    private static final int UNMARK_INDEX = 7;

    public static void handleList() {
        Printer.printTasks();
    }

    public static void handleTodo(String userInput) throws InvalidTodoSyntaxException {
        if (userInput.equals("todo")) {
            throw new InvalidTodoSyntaxException();
        }
        String description = userInput.substring(TODO_DESCRIPTION_INDEX);
        Todo todo = new Todo(description);
        TaskList.add(todo);
        FileManager.appendTask(todo);
        Printer.printAddTask();
    }

    public static void handleDeadline(String userInput) throws InvalidDeadlineSyntaxException {
        int byIndex = userInput.indexOf(" /by ") + 1;
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
        FileManager.appendTask(deadline);
        Printer.printAddTask();
    }

    public static void handleEvent(String userInput) throws InvalidEventSyntaxException {
        int fromIndex = userInput.indexOf(" /from ") + 1;
        int toIndex = userInput.indexOf(" /to ") + 1;
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
        FileManager.appendTask(event);
        Printer.printAddTask();
    }

    public static void handleMark(String userInput) throws InvalidMarkSyntaxException {
        if (TaskList.isEmpty()) {
            Printer.printUnableToMark();
            return;
        }
        if (userInput.equals("mark")) {
            throw new InvalidMarkSyntaxException();
        }
        try {
            int currentIndex = Integer.parseInt(userInput.substring(MARK_INDEX)) - 1;
            Task currentTask = TaskList.get(currentIndex);
            currentTask.mark();
            FileManager.updateMarkStatus(currentIndex, true);
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
        if (userInput.equals("unmark")) {
            throw new InvalidUnmarkSyntaxException();
        }
        try {
            int currentIndex = Integer.parseInt(userInput.substring(UNMARK_INDEX)) - 1;
            Task currentTask = TaskList.get(currentIndex);
            currentTask.unmark();
            FileManager.updateMarkStatus(currentIndex, false);
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
        if (userInput.equals("delete")) {
            throw new InvalidDeleteSyntaxException();
        }
        try {
            int currentIndex = Integer.parseInt(userInput.substring(UNMARK_INDEX)) - 1;
            Task deletedTask = TaskList.remove(currentIndex);
            Printer.printDeleteTask(deletedTask);
        } catch (Exception e) {
            throw new InvalidDeleteSyntaxException();
        }
    }

    public static void handleBye() {
        Printer.printBye();
    }
}
