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
        Scanner in = new Scanner(System.in);
        Printer.printGreeting();

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
                    Printer.printDefaultError();
                    break;
                }
                hasExitInput = true;
                break;
            case LIST_COMMAND:
                if (!message.isEmpty()) {
                    Printer.printDefaultError();
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
                if (message.isEmpty()) {
                    Printer.printToDoEmptyError();
                    break;
                }
                try {
                    taskManager.addToDo(message);
                } catch (Exception e) {
                    Printer.printTaskOvercapacityError();
                }
                break;
            case NEW_DEADLINE_COMMAND:
                try {
                    String by = InputParser.getDeadlineTime(message);
                    String taskName = InputParser.getTaskName(message);
                    taskManager.addDeadline(taskName, by);
                } catch (IllegalArgumentException e) {
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
                } catch (IllegalArgumentException e) {
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
        
        Printer.printExitMessage();
    }
}
