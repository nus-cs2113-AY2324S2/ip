import Exceptions.TaskNotFoundException;
import Tasks.Deadline;
import Tasks.Event;
import Tasks.Task;
import Tasks.Todo;
import Exceptions.ArgumentNotFoundException;
import Exceptions.CommandNotFoundException;

import java.util.ArrayList;
import java.util.Scanner;

public class Spike {
    private static final String CHATBOT = "Spike";
    public static final String DIVIDER = "_________________________________________________";
    public static final int MAX_TASK = 100;
    public static final int MARK_TASK_INDEX = 5;
    public static final int UNMARK_TASK_INDEX = 7;
    public static final int EVENT_TASK_INDEX = 6;
    public static final int DEADLINE_TASK_INDEX = 9;
    public static final int TODO_TASK_INDEX = 5;
    public static final int DELETE_TASK_INDEX = 7;

    public static void main(String[] args) {
        displayWelcomeMsg();

        ArrayList<Task> inputList = new ArrayList<>();
        Scanner in = new Scanner(System.in);

        while (true) {
            try {
                startChatbot(in, inputList);
                displayByeMsg();
                break;
            } catch (CommandNotFoundException e) {
                System.out.println("Command Not Found! Please Try Again");
            } catch (ArgumentNotFoundException e) {
                System.out.println("Argument Not Found! Please Try Again");
            } catch (TaskNotFoundException e){
                System.out.println("Task Not Found! Please Try Again");
            }
        }
    }

    private static void startChatbot(Scanner in, ArrayList<Task> inputList)
            throws CommandNotFoundException, ArgumentNotFoundException, TaskNotFoundException {
        outerLoop:
        while (true) {
            String input = in.nextLine();
            switch (input.split(" ")[0]) {
            case "list":
                displayList(inputList);
                break;
            case "mark":
                if (MARK_TASK_INDEX > input.length()) {
                    throw new ArgumentNotFoundException();
                }
                int indexMark = Integer.parseInt(input.substring(MARK_TASK_INDEX)) - 1;
                if (inputList.get(indexMark) == null){
                    throw new TaskNotFoundException();
                }
                inputList.get(indexMark).setDone(true);
                displayMarkMsg(indexMark, inputList);
                break;
            case "unmark":
                if (UNMARK_TASK_INDEX > input.length()) {
                    throw new ArgumentNotFoundException();
                }
                int indexUnmark = Integer.parseInt(input.substring(UNMARK_TASK_INDEX)) - 1;
                if (inputList.get(indexUnmark) == null){
                    throw new TaskNotFoundException();
                }
                inputList.get(indexUnmark).setDone(false);
                displayUnmarkMsg(indexUnmark, inputList);
                break;
            case "todo":
                Task newTodo = new Todo(processTodo(input));
                inputList.add(newTodo);
                displayAcknowledgement(newTodo, inputList.size());
                break;
            case "deadline":
                Task newDeadline = new Todo(processDeadline(input));
                inputList.add(newDeadline);
                displayAcknowledgement(newDeadline, inputList.size());
                break;
            case "event":
                Task newEvent = new Todo(processEvent(input));
                inputList.add(newEvent);
                displayAcknowledgement(newEvent, inputList.size());
                break;
            case "delete":
                int indexDelete = Integer.parseInt(input.substring(DELETE_TASK_INDEX)) - 1;
                inputList.remove(indexDelete);
                break;
            case "bye":
                break outerLoop;
            default:
                throw new CommandNotFoundException();
                //System.out.println("Not valid");
            }
        }
    }

    private static void displayAcknowledgement(Task inputObj, int arrayLength) {
        char Badge = getBadge(inputObj);
        System.out.println(DIVIDER);
        System.out.println("Got it. I've added this task:");
        System.out.println(" [" + Badge + "]" + "[ ] " + inputObj.description);
        System.out.println("Now you have " + arrayLength + " tasks in the list.");
        System.out.println(DIVIDER);
    }

    private static String processEvent(String input) throws ArgumentNotFoundException {
        if (EVENT_TASK_INDEX > input.length()) {
            throw new ArgumentNotFoundException();
        }
        String event = input.substring(EVENT_TASK_INDEX);
        String[] parts = event.split(" /from ");
        String[] time = parts[1].split(" /to ");
        return parts[0] + " (from: " + time[0] + " to: " + time[1] + ")";
    }

    private static String processDeadline(String input) throws ArgumentNotFoundException {
        if (DEADLINE_TASK_INDEX > input.length()) {
            throw new ArgumentNotFoundException();
        }
        String deadline = input.substring(DEADLINE_TASK_INDEX);
        String[] parts = deadline.split(" /by ");
        return parts[0] + " (by: " + parts[1] + ")";
    }

    private static String processTodo(String input) throws ArgumentNotFoundException {
        if (TODO_TASK_INDEX > input.length()) {
            throw new ArgumentNotFoundException();
        }
        return input.substring(TODO_TASK_INDEX);
    }

    private static void displayAddedMsg(String input) {
        System.out.println(DIVIDER);
        System.out.println("added: " + input);
        System.out.println(DIVIDER);
    }

    private static void displayList(ArrayList<Task> inputList) {
        System.out.println(DIVIDER);
        System.out.println("Here are the tasks in your list:");
        for (int i = 0; i < inputList.size(); i++) {
            Task value = inputList.get(i);
            if (value == null) {
                break;
            }
            char Badge = getBadge(value);
            System.out.println((i + 1) + ".[" + Badge + "]"
                    + "[" + value.getStatusIcon() + "] " + value.description);
        }
        System.out.println(DIVIDER);
    }

    private static char getBadge(Task value) {
        char Badge = ' ';
        if (value instanceof Todo) {
            Badge = ((Todo) value).getBadge();
        } else if (value instanceof Deadline) {
            Badge = ((Deadline) value).getBadge();
        } else if (value instanceof Event) {
            Badge = ((Event) value).getBadge();
        }
        return Badge;
    }

    private static void displayUnmarkMsg(int index, ArrayList<Task> inputList) {
        char Badge = getBadge(inputList.get(index));
        System.out.println(DIVIDER);
        System.out.println("OK, I've marked this task as not done yet:");
        System.out.println((index + 1) + ".[" + Badge + "]" + "[ ] "
                + inputList.get(index).description);
        System.out.println(DIVIDER);
    }

    private static void displayMarkMsg(int index, ArrayList<Task> inputList) {
        char Badge = getBadge(inputList.get(index));
        System.out.println(DIVIDER);
        System.out.println("Nice! I've marked this task as done:");
        System.out.println((index + 1) + ".[" + Badge + "]" + "[X] "
                + inputList.get(index).description);
        System.out.println(DIVIDER);
    }

    private static void displayByeMsg() {
        System.out.println(DIVIDER);
        System.out.println("Bye. Hope to see you again soon!");
        System.out.println(DIVIDER);
    }

    private static void displayWelcomeMsg() {
        System.out.println(DIVIDER);
        System.out.println("Hello! I'm " + CHATBOT);
        System.out.println("What can I do for you?\n");
        System.out.println(DIVIDER);
    }
}
