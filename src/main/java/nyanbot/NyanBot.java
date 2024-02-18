package nyanbot;

import java.util.Scanner;
import java.util.ArrayList;
import nyanbot.task.Task;
import nyanbot.task.Todo;
import nyanbot.task.Deadline;
import nyanbot.task.Event;
import nyanbot.printer.Printer;

public class NyanBot {
    private static final String LIST_COMMAND = "LIST";
    private static final String MARK_COMMAND = "MARK";
    private static final String UNMARK_COMMAND = "UNMARK";
    private static final String TODO_COMMAND = "TODO";
    private static final String DEADLINE_COMMAND = "DEADLINE";
    private static final String EVENT_COMMAND = "EVENT";
    private static final String BYE_COMMAND = "BYE";
    private static final String HELP_COMMAND = "HELP";
    private static final String DELETE_COMMAND = "DELETE";
    private static String LINE = "____________";
    private static ArrayList<Task> tasks = new ArrayList<>();


    public static void addTask(Task task) {
        tasks.add(task);
        Printer.printAddTaskSuccess(task);
    }

    public static void deleteTask(String input) {
        try {
            String[] splitInputs = input.split(" ");
            int index = Integer.parseInt(splitInputs[1]) - 1;
            tasks.remove(index);
            Printer.printDeleteSuccess();
            Printer.printTasks(tasks);
        } catch (IndexOutOfBoundsException | NullPointerException e) {
            Printer.printNullError();
        } catch (NumberFormatException e) {
            Printer.printDeleteUsage();
        }
    }

    private static void markTask(String input) {
        try {
            String[] splitInputs = input.split(" ");
            int index = Integer.parseInt(splitInputs[1]) - 1;
            //tasks[index].markAsDone();
            tasks.get(index).markAsDone();
            Printer.printMarkSuccess();
        } catch (IndexOutOfBoundsException e) {
            Printer.printNullError();
        } catch (NumberFormatException e) {
            Printer.printMarkUsage();
        }
    }

    private static void unmarkTask(String input) {
        try {
            String[] splitInputs = input.split(" ");
            int index = Integer.parseInt(splitInputs[1]) - 1;
            tasks.get(index).markAsUndone();
            Printer.printUnmarkSuccess();
        } catch (IndexOutOfBoundsException e) {
            Printer.printNullError();
        } catch (NumberFormatException e) {
            Printer.printUnmarkUsage();
        }
    }

    private static void addTodo(String input) {
        try {
            String description = input.substring(5);
            Todo todo = new Todo(description);
            addTask(todo);
        } catch (StringIndexOutOfBoundsException e) {
            Printer.printMissingDescription();
            Printer.printTodoUsage();
        }
    }

    private static void addDeadline(String input) {
        try {
            String[] splitInputs = input.split("/");
            String description = splitInputs[0].substring(9);
            String date = splitInputs[1];
            Deadline deadline = new Deadline(description, date);
            addTask(deadline);
        } catch (StringIndexOutOfBoundsException e) {
            Printer.printMissingDescription();
            Printer.printDeadlineUsage();
        } catch (ArrayIndexOutOfBoundsException e) {
            Printer.printMissingDate();
            Printer.printDeadlineUsage();
        }
    }

    private static void addEvent(String input) {
        try {
            String[] splitInputs = input.split("/");
            String description = splitInputs[0].substring(6);
            String start = splitInputs[1];
            String end = splitInputs[2];
            Event event = new Event(description, start, end);
            addTask(event);
        } catch (StringIndexOutOfBoundsException e) {
            Printer.printMissingDescription();
            Printer.printEventUsage();
        } catch (ArrayIndexOutOfBoundsException e) {
            Printer.printMissingStartEnd();
            Printer.printEventUsage();
        }
    }

    private static String parseCommand(String input) {
        String[] splitInputs = input.split(" ");
        return splitInputs[0].toUpperCase();
    }

    private static void getInput() {
        Scanner scanner = new Scanner(System.in);
        String[] splitInputs = new String[2];

        while (true) {
            String input = scanner.nextLine();
            splitInputs = input.split(" ");

            switch (parseCommand(input)) {
                case BYE_COMMAND:
                    scanner.close();
                    return;
                case LIST_COMMAND:
                    System.out.println(LINE);
                    Printer.printTasks(tasks);
                    break;
                case MARK_COMMAND:
                    int markIndex;
                    markTask(input);
                    break;
                case UNMARK_COMMAND:
                    unmarkTask(input);
                    break;
                case DELETE_COMMAND:
                    deleteTask(input);
                    break;
                case TODO_COMMAND:
                    addTodo(input);
                    break;
                case DEADLINE_COMMAND:
                    addDeadline(input);
                    break;
                case EVENT_COMMAND:
                    addEvent(input);
                    break;
                case HELP_COMMAND:
                    Printer.printSike();
                    break;
                default:
                    Printer.printInvalidInput();
                    break;
            }
        }
    }

    public static void main(String[] args) {

        Printer.printGreet();
        getInput();
        Printer.printBye();
    }
}
