import java.util.Scanner;

public class Byte {
    private static final int MAX_TASKS = 100;
    private static final Task[] tasks = new Task[MAX_TASKS];
    private static int taskCount = 0;

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        runByte(scanner);
        scanner.close();
    }

    public static void runByte(Scanner scanner){
        printWelcomeMessage();
        while(true){
            String userInput = scanner.nextLine();
            printLineSeparator();
            if (processUserInput(userInput)) {
                break;
            }
        }
    }

    public static boolean processUserInput(String userInput){
        if (userInput.equals("bye")) {
            printGoodbyeMessage();
            return true;
        } else if (userInput.startsWith("mark ")) {
            markTask(userInput.substring("mark ".length()), true);
        } else if (userInput.startsWith("unmark ")) {
            markTask(userInput.substring("unmark ".length()), false);
        } else if (userInput.equals("list")) {
            listTasks();
        } else if (userInput.startsWith("todo ")) {
            handleToDoCommand(userInput);
        }else if (userInput.startsWith("deadline ")) {
            handleDeadlineCommand(userInput);
        }else if (userInput.startsWith("event ")) {
            handleEventCommand(userInput);
        }
        return false;
    }

    private static void handleToDoCommand(String userInput) {
        addTask(new ToDo(userInput.substring("todo ".length())));
    }

    private static void handleDeadlineCommand(String userInput) {
        String[] deadlineDetails = userInput.substring("deadline ".length()).split(" /by ");
        addTask(new Deadline(deadlineDetails[0], deadlineDetails[1]));
    }

    private static void handleEventCommand(String userInput) {
        String[] eventDetails = userInput.substring("event ".length()).split(" /from ");
        String[] eventTimes = eventDetails[1].split(" /to ");
        addTask(new Event(eventDetails[0], eventTimes[0], eventTimes[1]));
    }

    private static void printWelcomeMessage() {
        printLineSeparator();
        System.out.println("Hello! I'm Byte, your friendly chat assistant!");
        System.out.println("What can I do for you?");
        printLineSeparator();
    }

    private static void printGoodbyeMessage() {
        System.out.println("Bye. Hope to see you again soon!");
        printLineSeparator();
    }

    private static void addTask(Task task) {
        if (taskCount < MAX_TASKS) {
            tasks[taskCount++] = task;
            System.out.println("Got it. I've added this task:\n " + task);
            System.out.println("Now you have " + taskCount + " tasks in the list.");
        } else {
            System.out.println("Sorry, I can only handle up to " + MAX_TASKS + " tasks!");
        }
        printLineSeparator();
    }

    private static void listTasks() {
        for (int i = 0; i < taskCount; i++) {
            System.out.println((i + 1) + ". " + tasks[i]);
        }
        printLineSeparator();
    }

    private static void markTask(String taskNumberStr, boolean isDone) {
        int taskNumber;
        try {
            taskNumber = Integer.parseInt(taskNumberStr)-1;
            Task task = tasks[taskNumber];
            if (isDone) {
                task.markAsDone();
                System.out.println("Nice! I've marked this task as done:\n  " + tasks[taskNumber]);
            } else {
                task.markAsNotDone();
                System.out.println("OK, I've marked this task as not done yet:\n  " + tasks[taskNumber]);
            }
        } catch (NumberFormatException e) {
            System.out.println("Please enter a valid task number.");
        } catch (IndexOutOfBoundsException e) {
            System.out.println(e.getMessage());
        }
        printLineSeparator();
    }

    private static void printLineSeparator() {
        System.out.println("____________________________________________________________");
    }
}


