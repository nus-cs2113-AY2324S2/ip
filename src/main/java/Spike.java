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

    public static void main(String[] args) {
        displayWelcomeMsg();

        Task[] inputList = new Task[MAX_TASK];
        int iter = 0;
        Scanner in = new Scanner(System.in);

        while (true) {
            String input = in.nextLine();
            if (input.contentEquals("list")) {
                displayList(inputList);
                iter -= 1;
            } else if (input.startsWith("mark")) {
                int index = Integer.parseInt(input.substring(MARK_TASK_INDEX)) - 1;
                inputList[index].setDone(true);
                displayMarkMsg(index, inputList);
            } else if (input.startsWith("unmark")) {
                int index = Integer.parseInt(input.substring(UNMARK_TASK_INDEX)) - 1;
                inputList[index].setDone(false);
                displayUnmarkMsg(index, inputList);
            } else if (input.startsWith("todo")) {
                inputList[iter] = new Todo(processTodo(input));
                displayAcknowledgement(inputList, iter);
            } else if (input.startsWith("deadline")) {
                inputList[iter] = new Deadline(processDeadline(input));
                displayAcknowledgement(inputList, iter);
            } else if (input.startsWith("event")) {
                inputList[iter] = new Event(processEvent(input));
                displayAcknowledgement(inputList, iter);
            } else if (input.contentEquals("bye")) {
                break;
            } else {
                System.out.println("Not valid");
                iter -= 1;
            }
            iter += 1;
        }
        displayByeMsg();
    }

    private static void displayAcknowledgement(Task[] inputList, int iter) {
        char Badge = getBadge(inputList[iter]);
        System.out.println(DIVIDER);
        System.out.println("Got it. I've added this task:");
        System.out.println(" [" + Badge + "]" + "[ ] " + inputList[iter].description);
        System.out.println("Now you have " + (iter + 1) + " tasks in the list.");
        System.out.println(DIVIDER);
    }

    private static String processEvent(String input) {
        String event = input.substring(EVENT_TASK_INDEX);
        String[] parts = event.split(" /from ");
        String[] time = parts[1].split(" /to ");
        return parts[0] + " (from: " + time[0] + " to: " + time[1] + ")";
    }

    private static String processDeadline(String input) {
        String deadline = input.substring(DEADLINE_TASK_INDEX);
        String[] parts = deadline.split(" /by ");
        return parts[0] + " (by: " + parts[1] + ")";
    }

    private static String processTodo(String input) {
        return input.substring(TODO_TASK_INDEX);
    }

    private static void displayAddedMsg(String input) {
        System.out.println(DIVIDER);
        System.out.println("added: " + input);
        System.out.println(DIVIDER);
    }

    private static void displayList(Task[] inputList) {
        System.out.println(DIVIDER);
        System.out.println("Here are the tasks in your list:");
        for (int i = 0; i < inputList.length; i++) {
            Task value = inputList[i];
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

    private static void displayUnmarkMsg(int index, Task[] inputList) {
        char Badge = getBadge(inputList[index]);
        System.out.println(DIVIDER);
        System.out.println("OK, I've marked this task as not done yet:");
        System.out.println((index + 1) + ".[" + Badge + "]" + "[ ] "
                + inputList[index].description);
        System.out.println(DIVIDER);
    }

    private static void displayMarkMsg(int index, Task[] inputList) {
        char Badge = getBadge(inputList[index]);
        System.out.println(DIVIDER);
        System.out.println("Nice! I've marked this task as done:");
        System.out.println((index + 1) + ".[" + Badge + "]" + "[X] "
                + inputList[index].description);
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
