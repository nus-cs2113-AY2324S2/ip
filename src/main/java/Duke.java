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

    public static void main(String[] args) {
        System.out.println("Hello! I'm Ms Chatty :)");
        System.out.println("What can I do for you?");


        String userCommand;
        Task[] tasks = new Task[100];
        int taskCount = 0;
        Scanner in = new Scanner(System.in);
        userCommand = in.nextLine();

        while (!(userCommand.equals("bye"))) {
            if (userCommand.equals("list")) {
                printTasks(tasks, taskCount);
                userCommand = in.nextLine();
                continue;
            }
            else if (userCommand.contains("unmark ")) {
                String numberExtracted = userCommand.replaceAll("[^0-9]", "");
                int taskNumber = Integer.parseInt(numberExtracted);
                unmarkTask(tasks,taskNumber-1);
                userCommand = in.nextLine();
                continue;
            }
            else if (userCommand.contains("mark ")) {
                String numberExtracted = userCommand.replaceAll("[^0-9]", "");
                int taskNumber = Integer.parseInt(numberExtracted);
                markTask(tasks,taskNumber-1);
                userCommand = in.nextLine();
                continue;
            }
            else if (userCommand.contains("todo")) {
                String[] arrayOfCommand = new String[2];
                arrayOfCommand = userCommand.split(" ", 2);
                tasks[taskCount] = new Todo(arrayOfCommand[1]);
                System.out.println("Got it. I've added this task:");
                tasks[taskCount].printTask();
            }
            else if (userCommand.contains("deadline")) {
                String[] arrayOfCommand = new String[2];
                String[] tempArray = new String[2];
                String temp;
                arrayOfCommand = userCommand.split(" ",2);
                temp = arrayOfCommand[1];
                tempArray = temp.split("/by", 2);
                tasks[taskCount] = new Deadline(tempArray[0], tempArray[1]);
                System.out.println("Got it. I've added this task:");
                tasks[taskCount].printTask();
            }
            else if (userCommand.contains("event")) {
                String[] arrayOfCommand = new String[2];
                String[] tempArray = new String[3];
                String temp;
                arrayOfCommand = userCommand.split(" ",2);
                temp = arrayOfCommand[1];
                tempArray = temp.split(" /from | /to ");
                tasks[taskCount] = new Event(tempArray[0], tempArray[1], tempArray[2]);
                System.out.println("Got it. I've added this task:");
                tasks[taskCount].printTask();
            }

            else {
                tasks[taskCount] = new Task(userCommand);
                taskCount++;
                System.out.println("added: " + userCommand);
                userCommand = in.nextLine();
                continue;
            }

            taskCount++;
            System.out.println("Now you have " + taskCount + " task(s) in the list.");
            userCommand = in.nextLine();
        }


        System.out.println("Bye! Hope to see you again :)");
    }
}

