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
            } else if (userCommand.contains("unmark ")) {
                int taskNumber = Task.extractNumber(userCommand);
                unmarkTask(tasks,taskNumber-1);
                userCommand = in.nextLine();
                continue;
            } else if (userCommand.contains("mark ")) {
                int taskNumber = Task.extractNumber(userCommand);
                markTask(tasks,taskNumber-1);
                userCommand = in.nextLine();
                continue;
            } else if (userCommand.contains("todo")) {
                arrayOfCommand = userCommand.split(" ", 2);
                tasks[taskCount] = new Todo(arrayOfCommand[1]);
            } else if (userCommand.contains("deadline")) {
                arrayOfCommand = userCommand.split("deadline | /by");
                tasks[taskCount] = new Deadline(arrayOfCommand[1], arrayOfCommand[2]);
            } else if (userCommand.contains("event")) {
                arrayOfCommand = userCommand.split("event | /from | /to ");
                tasks[taskCount] = new Event(arrayOfCommand[1], arrayOfCommand[2], arrayOfCommand[3]);
            } else {
                tasks[taskCount] = new Task(userCommand);
                taskCount++;
                System.out.println("added: " + userCommand);
                userCommand = in.nextLine();
                continue;
            }

            System.out.println("Got it. I've added this task:");
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
