import java.util.Scanner;

public class Duke {
    private static final int MAX_TASKS = 100;
    private static Task[] tasks = new Task[MAX_TASKS];
    private static int taskCount = 0;


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
                markTask(userInput, true);
            } else if (userInput.startsWith("unmark ")) {
                markTask(userInput, false);
            } else if (userInput.equals("list")) {
                listTasks();
            } else {
                addTask(userInput);
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

    private static void addTask(String taskDescription) {
        if (taskCount < MAX_TASKS) {
            Task newTask = new Task(taskDescription);
            tasks[taskCount] = newTask;
            taskCount++;
            System.out.println("added: " + taskDescription);
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

    private static void markTask(String userInput, boolean isDone) {
        int taskNumber;
        try {
            taskNumber = Integer.parseInt(userInput.split(" ")[1]) - 1;
            if (taskNumber < 0 || taskNumber >= taskCount) {
                throw new IndexOutOfBoundsException("Task number is out of range");
            }
            if (isDone) {
                tasks[taskNumber].markAsDone();
                System.out.println("Nice! I've marked this task as done:\n  " + tasks[taskNumber]);
            } else {
                tasks[taskNumber].markAsNotDone();
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


