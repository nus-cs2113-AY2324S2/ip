import java.util.Scanner;

public class MsChatty {
    public static void printTasks(Task[] tasks, int taskCount) {
        System.out.println("Here are the tasks in your list:");
        int taskNumber = 1;
        for (int i = 0; i < taskCount; i++) {
            System.out.print(taskNumber + ".");
            tasks[i].printTask();
            taskNumber++;
        }
    }

    public static void markAndUnmarkTask(Task[] tasks, int taskNumber, String[] arrayOfCommand) {
        if (arrayOfCommand[0].equals("mark")) {
            System.out.println("Nice! I've marked this task as done:");
            tasks[taskNumber].markAsDoneOrNotDone(arrayOfCommand);
        } else {
            System.out.println("OK, I've marked this task as not done yet:");
            tasks[taskNumber].markAsDoneOrNotDone(arrayOfCommand);
        }
        System.out.print(" ");
        tasks[taskNumber].printTask();
    }

    public static void handleMarkAndUnmarkRequest(String userCommand, String[] arrayOfCommand, Task[] tasks, int taskCount) {
        arrayOfCommand = userCommand.split(" ", 2);
        try {
            if (arrayOfCommand.length < 2 || arrayOfCommand[1].isEmpty()) {
                throw new StringIndexOutOfBoundsException();
            }
            int taskNumber = Integer.parseInt(arrayOfCommand[1]);
            if (taskNumber > taskCount) {
                throw new NullPointerException();
            }
            markAndUnmarkTask(tasks, taskNumber - 1, arrayOfCommand);
        } catch (StringIndexOutOfBoundsException e) {
            System.out.print(Guide.MARK_AND_UNMARK_REQUEST_FORMAT);
        } catch (NullPointerException e) {
            System.out.print(Guide.OUT_OF_BOUND);
        }
    }

    public static void handleTodoDeadlineAndEvent(String userCommand, int taskCount, String[] arrayOfCommand, Task[] tasks) {
        if (userCommand.startsWith("todo")) {
            arrayOfCommand = userCommand.split(" ", 2);
            tasks[taskCount] = new Todo(arrayOfCommand[1]);
        }

        if (userCommand.startsWith("deadline")) {
            arrayOfCommand = userCommand.split("deadline | /by");
            tasks[taskCount] = new Deadline(arrayOfCommand[1], arrayOfCommand[2]);
        }

        if (userCommand.startsWith("event")) {
            arrayOfCommand = userCommand.split("event | /from | /to ");
            tasks[taskCount] = new Event(arrayOfCommand[1], arrayOfCommand[2], arrayOfCommand[3]);
        }

        System.out.println("Got it. I've added this task:");
        tasks[taskCount].printTask();
    }

    public static void handleCommand(String userCommand) {
        final int MAX_SIZE = 100;
        Task[] tasks = new Task[MAX_SIZE];
        String[] arrayOfCommand = new String[4];
        int taskCount = 0;
        Scanner in = new Scanner(System.in);

        while (!userCommand.equals("bye")) {
            String[] words = userCommand.split(" ");
            try {
                switch (words[0]) {
                case "list":
                    printTasks(tasks, taskCount);
                    userCommand = in.nextLine();
                    break;
                case "unmark":
                    handleMarkAndUnmarkRequest(userCommand, arrayOfCommand, tasks, taskCount);
                    userCommand = in.nextLine();
                    break;
                case "mark":
                    handleMarkAndUnmarkRequest(userCommand, arrayOfCommand, tasks, taskCount);
                    userCommand = in.nextLine();
                    break;
                case "todo":
                    handleTodoDeadlineAndEvent(userCommand, taskCount, arrayOfCommand, tasks);
                    taskCount++;
                    System.out.println("Now you have " + taskCount + " task(s) in the list.");
                    userCommand = in.nextLine();
                    break;
                case "deadline":
                    handleTodoDeadlineAndEvent(userCommand, taskCount, arrayOfCommand, tasks);
                    taskCount++;
                    System.out.println("Now you have " + taskCount + " task(s) in the list.");
                    userCommand = in.nextLine();
                    break;
                case "event":
                    handleTodoDeadlineAndEvent(userCommand, taskCount, arrayOfCommand, tasks);
                    taskCount++;
                    System.out.println("Now you have " + taskCount + " task(s) in the list.");
                    userCommand = in.nextLine();
                    break;
                default:
                    System.out.println(Guide.REQUESTS_FORMAT);
                    userCommand = in.nextLine();
                }
            } catch (IndexOutOfBoundsException e) {
                switch (words[0]) {
                case "todo":
                    System.out.println(Guide.TODO_REQUEST_FORMAT);
                    userCommand = in.nextLine();
                    break;
                case "deadline":
                    System.out.println(Guide.DEADLINE_REQUEST_FORMAT);
                    userCommand = in.nextLine();
                    break;
                case "event":
                    System.out.println(Guide.EVENT_REQUEST_FORMAT);
                    userCommand = in.nextLine();
                    break;
                default:
                    break;
                }
            }
        }
        System.out.println("Bye! Hope to see you again :)");
    }

    public static void main(String[] args) {
        System.out.println("Hello! I'm Ms Chatty :)");
        System.out.println("What can I do for you?");

        Scanner in = new Scanner(System.in);
        String userCommand = in.nextLine();
        handleCommand(userCommand);
    }
}