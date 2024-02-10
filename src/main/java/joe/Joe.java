package joe;

import joe.util.InputParser;
import joe.util.Printer;
import joe.task.TaskManager;

import java.util.Scanner;

public class Joe {
    protected static final String EXIT_COMMAND = "bye";
    protected static final String LIST_COMMAND = "list";
    protected static final String MARK_COMMAND = "mark";
    protected static final String UNMARK_COMMAND = "unmark";
    protected static final String NEW_TODO_COMMAND = "todo";
    protected static final String NEW_DEADLINE_COMMAND = "deadline";
    protected static final String NEW_EVENT_COMMAND = "event";

    public static void main(String[] args) {
        Printer.printGreeting();
        runJoe();
        Printer.printExitMessage();
    }

    private static void runJoe() {
        Scanner in = new Scanner(System.in);
        TaskManager taskManager = new TaskManager();

        boolean hasExitInput = false;
        while (!hasExitInput) {
            String input = in.nextLine();
            input = input.trim();
            String command = InputParser.getCommand(input);
            String message = InputParser.getMessage(input);
            switch (command) {
            case EXIT_COMMAND:
                if (!message.isEmpty()) {
                    Printer.printExitError();
                    break;
                }
                hasExitInput = true;
                break;
            case LIST_COMMAND:
                if (!message.isEmpty()) {
                    Printer.printListError();
                    break;
                }
                taskManager.listTasks();
                break;
            case MARK_COMMAND:
            case UNMARK_COMMAND:
                try {
                    int taskNumber = Integer.parseInt(message);
                    taskManager.toggleTaskMarkedStatus(taskNumber, command.equals(MARK_COMMAND));
                } catch (NumberFormatException | JoeException e) {
                    Printer.printInvalidMarkError();
                }
                break;
            case NEW_TODO_COMMAND:
                try {
                    taskManager.addToDo(message);
                } catch (JoeException e) {
                    Printer.printToDoEmptyError();
                } catch (ArrayIndexOutOfBoundsException e) {
                    Printer.printTaskOvercapacityError();
                }
                break;
            case NEW_DEADLINE_COMMAND:
                try {
                    String by = InputParser.getDeadlineTime(message);
                    String taskName = InputParser.getTaskName(message);
                    taskManager.addDeadline(taskName, by);
                } catch (JoeException e) {
                    Printer.printDeadlineInputError();
                } catch (ArrayIndexOutOfBoundsException e) {
                    Printer.printTaskOvercapacityError();
                }
                break;
            case NEW_EVENT_COMMAND:
                try {
                    String[] eventDurations = InputParser.getEventTime(message);
                    String taskName = InputParser.getTaskName(message);
                    taskManager.addEvent(taskName, eventDurations[0], eventDurations[1]);
                } catch (JoeException e) {
                    Printer.printEventInputError();
                } catch (ArrayIndexOutOfBoundsException e) {
                    Printer.printTaskOvercapacityError();
                }
                break;
            default:
                Printer.printDefaultError();
                break;
            }
        }
    }
}
