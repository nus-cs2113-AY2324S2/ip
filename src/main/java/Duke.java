import java.util.Scanner;


public class Duke {
    private static List inputList = new List();
    public static void main(String[] args) {
        String name = "Brad";
        System.out.println("Hello I am " + name + ".\n");
        System.out.println("How can I help you today?\n");
        Scanner userInput = new Scanner(System.in);
        while (true) {
            String input = userInput.nextLine();
            boolean canExit = input.equals("bye");
            boolean canList = input.equals("list");
            boolean canMark = input.startsWith("mark");
            boolean canUnmark = input.startsWith("unmark");
            if (canExit) {
                printOutput("Bye. Hope to see you again soon!");
                break;
            } else if (canList) {
                printOutput("Here are the tasks in your list:\n" + inputList.getList());
            } else if (canMark) {
                doMarkAction(input);
            } else if (canUnmark) {
                doUnmarkAction(input);
            } else if (input.startsWith("todo")) {
                doTodoAction(input);
            } else if (input.startsWith("deadline")) {
                doDeadlineAction(input);
            } else if (input.startsWith("event")) {
                doEventAction(input);
            } else {
                doTaskAction(input);
            }
        }
    }

    private static void printOutput(String message) {
        String separator = "____________________________________________________________";
        System.out.println(separator);
        System.out.println(message);
        System.out.println(separator);
    }

    private static void doMarkAction(String input) {
        int taskNum = Integer.parseInt(input.split(" ")[1]);
        if (taskNum > inputList.listSize()) {
            System.out.println("Exceeded existing list size of: " + inputList.listSize() + ". Please enter a valid number" + "\n");
        } else {
            inputList.markAsDone(taskNum, true);
            String message = "Nice! I've marked this task as done: \n" + inputList.getTask(taskNum);
            printOutput(message);
        }
    }

    private static void doUnmarkAction(String input) {
        int itemNo = Integer.parseInt(input.split(" ")[1]);
        inputList.markAsDone(itemNo, false);
        String message = "OK, I've marked this task as not done yet: \n" + inputList.getTask(itemNo);
        printOutput(message);
    }

    private static void doTodoAction(String input) {
        String description = input.substring("todo ".length());
        inputList.addToList(description, TaskType.TODO);
        int size = inputList.listSize();
        String message = "Got it. I've added this task:\n" + inputList.getTask(size)
                + "\n Now you have " + size + " tasks in the list.";
        printOutput(message);
    }

    private static void doDeadlineAction(String input) {
        String description = input.substring("deadline ".length());
        inputList.addToList(description, TaskType.DEADLINE);
        int size = inputList.listSize();
        String message = "Got it. I've added this task:\n" + inputList.getTask(size)
                + "\n Now you have " + size + " tasks in the list.";
        printOutput(message);
    }

    private static void doEventAction(String input) {
        String description = input.substring("event ".length());
        inputList.addToList(description, TaskType.EVENT);
        int size = inputList.listSize();
        String message = "Got it. I've added this task:\n" + inputList.getTask(size)
                + "\n Now you have " + size + " tasks in the list.";
        printOutput(message);
    }

    private static void doTaskAction(String input) {
        inputList.addToList(input, TaskType.OTHER);
        int size = inputList.listSize();
        String message = "Got it. I've added this task:\n" + inputList.getTask(size)
                + "\n Now you have " + size + " tasks in the list.";
        printOutput(message);
    }
}