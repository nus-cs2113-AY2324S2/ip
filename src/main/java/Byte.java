import java.util.Scanner;

public class Byte {
    private static final int MAX_TASKS = 100;
    private static final Task[] tasks = new Task[MAX_TASKS];
    private static int taskCount = 0;
    private static final int TODO_COMMAND_LENGTH = "todo ".length();
    private static final int DEADLINE_COMMAND_LENGTH = "deadline ".length();
    private static final int EVENT_COMMAND_LENGTH = "event ".length();
    private static final int MARK_COMMAND_LENGTH = "mark ".length();
    private static final int UNMARK_COMMAND_LENGTH = "unmark ".length();


    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        printWelcomeMessage();

        while (true) {
            String userInput = scanner.nextLine();
            printLineSeparator();

            if (userInput.equals("bye")) {
                printGoodbyeMessage();
                break;
            } else if (userInput.startsWith("mark ")) {
                markTask(userInput.substring(MARK_COMMAND_LENGTH), true);
            } else if (userInput.startsWith("unmark ")) {
                markTask(userInput.substring(UNMARK_COMMAND_LENGTH), false);
            } else if (userInput.equals("list")) {
                listTasks();
            } else if (userInput.startsWith("todo ")){
                addTask(new ToDo(userInput.substring(TODO_COMMAND_LENGTH)));
            }else if(userInput.startsWith("deadline ")){
                String[] deadlineDetails = userInput.substring(DEADLINE_COMMAND_LENGTH).split(" /by ");
                addTask(new Deadline(deadlineDetails[0], deadlineDetails[1]));
            }else if(userInput.startsWith("event ")){
                String[] eventDetails  = userInput.substring(EVENT_COMMAND_LENGTH).split(" /from ");
                String[] eventTimes  = eventDetails[1].split(" /to ");
                addTask(new Event(eventDetails[0], eventTimes[0], eventTimes[1]));
            }
        }

        scanner.close();
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


