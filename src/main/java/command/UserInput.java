package command;
import java.util.Scanner;
import java.util.InputMismatchException;
import java.util.List;
import task.Deadline;
import task.Event;
import task.Task;
import task.ToDo;


public class UserInput {

    public final static String LIST_COMMAND = "list";
    public final static String BYE_COMMAND = "bye";
    public final static String MARK_COMMAND = "mark";
    public final static String UNMARK_COMMAND = "unmark";
    public final static String TODO_COMMAND = "todo";
    public final static String DEADLINE_COMMAND = "deadline";
    public final static String EVENT_COMMAND = "event";
    public final static String DELETE_COMMAND = "delete";

    public static void parseInput(String input, Scanner in, List<Task> taskList) {

        int taskId;

        switch (input.toLowerCase()) {

        case LIST_COMMAND:
            printLists(taskList);
            break;

        case BYE_COMMAND:
            break;

        case MARK_COMMAND:
            try {
                taskId = in.nextInt();
                markTask(taskList, taskId);
            } catch (InputMismatchException e) {
                System.out.println("A number please");
            }
            break;

        case UNMARK_COMMAND:
            try {
                taskId = in.nextInt();
                unmarkTask(taskList, taskId);
            } catch (InputMismatchException e) {
                System.out.println("A number please");
            }
            break;

        case TODO_COMMAND:
            input = in.nextLine();
            parseToDo(input, taskList);
            break;

        case DEADLINE_COMMAND:
            input = in.nextLine();
            parseDeadline(input, taskList);
            break;

        case EVENT_COMMAND:
            input = in.nextLine();
            parseEvent(input, taskList);
            break;

        case DELETE_COMMAND:
            try {
                taskId = in.nextInt();
                deleteTask(taskId, taskList);
            } catch (InputMismatchException e) {
                System.out.println("A number please");
            }
            break;

        default:
            System.out.println("Please input a valid command");
            break;
        }
    }

    private static void printLists(List<Task> taskList) {

        for (int i = 1; i <= taskList.size(); i += 1) {
            System.out.print(i + ".");
            System.out.println(taskList.get(i - 1));
        }
    }

    private static void markTask(List<Task> taskList, int taskID) {
        try {

            taskList.get(taskID - 1).markCompleted();
            System.out.println("Marking as done:");
            System.out.println(taskList.get(taskID - 1));

        } catch (IndexOutOfBoundsException e) {

            System.out.println("Invalid Index");

        }
    }

    private static void unmarkTask(List<Task> taskList, int taskID) {
        try {

            taskList.get(taskID - 1).markUncompleted();
            System.out.println("Marking as undone:");
            System.out.println(taskList.get(taskID - 1));

        } catch (IndexOutOfBoundsException e) {

            System.out.println("Invalid index");

        }
    }

    private static void parseToDo(String input, List<Task> taskList) {

        taskList.add(new ToDo(input.trim()));
        System.out.println("Added ToDo: " + input);

    }

    private static void parseDeadline(String input, List<Task> taskList) {
        try {

            taskList.add(new Deadline(input));
            System.out.println("Added Deadline: " + taskList.get(taskList.size() - 1).getName());

        } catch (JohnException e) {

            System.out.println("Invald input");

        }
    }

    private static void parseEvent(String input, List<Task> taskList) {
        try {

            taskList.add(new Event(input));
            System.out.println("Added Event: " + taskList.get(taskList.size() - 1).getName());

        } catch (JohnException e) {

            System.out.println("Invalid input");

        }
    }

    private static void deleteTask(int taskId, List<Task> taskList) {
        
        try {
            System.out.println("Removing Task: ");
            System.out.println(taskList.get(taskId - 1));
            taskList.remove(taskId - 1);
        } catch (IndexOutOfBoundsException e) {
            System.out.println("Invalid range");
        }
    }

}
