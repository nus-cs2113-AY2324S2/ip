import java.util.Scanner;

public class Duke {
    public static void printTasks(Task[] tasks, int taskCount) {
        System.out.println("Here are the tasks in your list:");
        int taskNumber = 1;
        for (int i = 0; i < taskCount; i++) {
            System.out.print(taskNumber + ".");
            tasks[i].printTask();
            taskNumber++;
        }
    }

    public static void markTask(Task[] tasks, int taskNumber) {
        System.out.println("Nice! I've marked this task as done:");
        tasks[taskNumber].markAsDone();
        System.out.print(" ");
        tasks[taskNumber].printTask();
    }

    public static void unmarkTask(Task[] tasks, int taskNumber) {
        System.out.println("OK, I've marked this task as not done yet:");
        tasks[taskNumber].markAsNotDone();
        System.out.print(" ");
        tasks[taskNumber].printTask();
    }

    public static void handleMarkAndUnmarkRequest(String userCommand,String[] arrayOfCommand,Task[] tasks) {
        arrayOfCommand = userCommand.split(" ",2);
        if (arrayOfCommand.length < 2 || arrayOfCommand[1].isEmpty()) {
            System.out.print(Guide.MARK_AND_UNMARK_REQUEST_FORMAT);
            throw new StringIndexOutOfBoundsException();
        }
        int taskNumber = Integer.parseInt(arrayOfCommand[1]);
        if (arrayOfCommand[0].equals("mark")) {
            markTask(tasks, taskNumber - 1);
        } else {
            unmarkTask(tasks, taskNumber - 1);
        }
    }

    public static void handleTodoDeadlineAndEvent(String userCommand,int taskCount,String[] arrayOfCommand,Task[] tasks){
        if (userCommand.startsWith("todo")) {
            arrayOfCommand = userCommand.split(" ", 2);
            if (arrayOfCommand.length < 2 || arrayOfCommand[1].isEmpty()) {
                System.out.println(Guide.TODO_REQUEST_FORMAT);
                throw new StringIndexOutOfBoundsException();
            }
            tasks[taskCount] = new Todo(arrayOfCommand[1]);
        }

        if (userCommand.startsWith("deadline")) {
            arrayOfCommand = userCommand.split("deadline | /by");
            if (arrayOfCommand.length < 2 || arrayOfCommand[1].isEmpty()) {
                System.out.println(Guide.DEADLINE_REQUEST_FORMAT);
                throw new StringIndexOutOfBoundsException();
            }
            tasks[taskCount] = new Deadline(arrayOfCommand[1], arrayOfCommand[2]);
        }

        if (userCommand.startsWith("event")) {
            arrayOfCommand = userCommand.split("event | /from | /to ");
            if (arrayOfCommand.length < 3 || arrayOfCommand[1].isEmpty() || arrayOfCommand[2].isEmpty()) {
                System.out.println(Guide.EVENT_REQUEST_FORMAT);
                throw new StringIndexOutOfBoundsException();
            }
            tasks[taskCount] = new Event(arrayOfCommand[1], arrayOfCommand[2], arrayOfCommand[3]);
        }

        System.out.println("Got it. I've added this task:");
    }

    public static void handleCommand(String userCommand) {
        Task[] tasks = new Task[100];
        String[] arrayOfCommand = new String[4];
        int taskCount = 0;
        Scanner in = new Scanner(System.in);

        while (!(userCommand.equals("bye"))) {
            if (userCommand.equals("list")) {
                printTasks(tasks, taskCount);
                userCommand = in.nextLine();
                continue;
            } else if (userCommand.contains("unmark")) {
                handleMarkAndUnmarkRequest(userCommand, arrayOfCommand, tasks);
                userCommand = in.nextLine();
                continue;
            } else if (userCommand.contains("mark")) {
                handleMarkAndUnmarkRequest(userCommand, arrayOfCommand, tasks);
                userCommand = in.nextLine();
                continue;
            } else if (userCommand.contains("todo") || userCommand.contains("deadline") || userCommand.contains("event")) {
                handleTodoDeadlineAndEvent(userCommand, taskCount, arrayOfCommand, tasks);
            } else { System.out.println(Guide.REQUESTS_FORMAT);
                throw new IllegalArgumentException();
            }

            tasks[taskCount].printTask();
            taskCount++;
            System.out.println("Now you have " + taskCount + " task(s) in the list.");
            userCommand = in.nextLine();
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
