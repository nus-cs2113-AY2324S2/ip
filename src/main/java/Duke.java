import java.util.Scanner;

public class Duke {
    public static void printItems(Task[] tasks, int itemCount) {
        System.out.println("Here are the tasks in your list:");
        int itemNumber = 1;
        for (int i = 0; i < itemCount; i++) {
            System.out.print(itemNumber + ".");
            tasks[i].printTask();
            itemNumber++;
        }
    }

    public static void markTask(Task[] tasks, int itemNumber) {
        System.out.println("Nice! I've marked this task as done:");
        tasks[itemNumber].markAsDone();
        System.out.print(" ");
        tasks[itemNumber].printTask();
    }

    public static void main(String[] args) {
        System.out.println("Hello! I'm Ms Chatty :)");
        System.out.println("What can I do for you?");

        String userCommand;
        Task[] tasks = new Task[100];
        int itemCount = 0;
        Scanner in = new Scanner(System.in);
        userCommand = in.nextLine();

        while (!(userCommand.equals("bye"))) {
            if (userCommand.equals("list")) {
                printItems(tasks, itemCount);
                userCommand = in.nextLine();
                continue;
            }

            if (userCommand.contains("mark ")) {
                String number = userCommand.replaceAll("[^0-9]", "");
                int itemNumber = Integer.parseInt(number);
                markTask(tasks,itemNumber-1);
                userCommand = in.nextLine();
                continue;
            }

            tasks[itemCount] = new Task(userCommand);
            itemCount++;

            System.out.println("added: " + userCommand);
            userCommand = in.nextLine();
        }

        System.out.println("Bye! Hope to see you again :)");
    }
}
