import java.util.Scanner;

public class Ui {
    private static final String LINE_SEPARATOR = "____________________________________________________________";

    public static void printStartingMessage() {
        System.out.println(LINE_SEPARATOR);
        System.out.println("Sup! I'm DavinciBot! I was the smartest man alive, but now I am just a list maker.");
        System.out.println("Enter commands, and I will echo them back to you, as well as add them to your list.");
        System.out.println("If there were things in your list that you previously had," +
                " I will show what you have told me previously.");
        System.out.println("Type 'bye' to end the conversation.");
        System.out.println("Type 'list' to see your to-do list.");
        System.out.println("Type 'mark' to mark a task as done.");
        System.out.println("Type 'unmark' to mark a task as not done.");
        System.out.println("Type 'todo <work>' to add a task to the list.");
        System.out.println("Type 'deadline <description> /by <deadline>' to add a task with a deadline to the list.");
        System.out.println("Type 'event <description> /from <start> /to <end>' to add an event to the list.");
        System.out.println("Type 'delete <index>' to delete a task from your list.");
        System.out.println("See ya bucko!");
        System.out.println(LINE_SEPARATOR);
    }

    public static String getUserInput() {
        Scanner scanner = new Scanner(System.in);
        System.out.print("What do you want me to do? ");
        return scanner.nextLine().trim();
    }

    public static void displayTaskList(Task[] taskArray) {
        System.out.println(LINE_SEPARATOR);
        if (taskArray.length == 0) {
            System.out.println("No tasks entered yet.");
        } else {
            System.out.println("Here are the tasks in your list:");
            for (int i = 0; i < taskArray.length; i++) {
                String taskType;
                if (taskArray[i] instanceof Deadline) {
                    taskType = "[D]";
                } else if (taskArray[i] instanceof Todo) {
                    taskType = "[T]";
                } else {
                    taskType = "[E]";
                }
                System.out.println((i + 1) + ". " + taskType +
                        " [" + taskArray[i].getStatusIcon() + "] " +
                        taskArray[i].getDescription());
            }
        }
        System.out.println(LINE_SEPARATOR);
    }

    public static void printMessage(String message) {
        System.out.println(LINE_SEPARATOR);
        System.out.println(message);
        System.out.println(LINE_SEPARATOR);
    }

    public static void echoTask(Task[] taskArray) {
        System.out.println(LINE_SEPARATOR);
        System.out.println("Got it. I've added this task:");
        System.out.println(taskArray[taskArray.length - 1].toString());
        System.out.println("Now you have " + taskArray.length + " tasks in the list.");
        System.out.println(LINE_SEPARATOR);
    }
}
