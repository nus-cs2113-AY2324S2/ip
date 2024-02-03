import java.util.Scanner;

public class Joey {
    public static void main(String[] args) {
        System.out.println("Hello! I'm Joey");
        System.out.println("What can I do for you?");
        System.out.println("____________________________________________________________");

        Scanner scanner = new Scanner(System.in);
        String[] newTasks = new String[100];
        int taskCount = 0;

        while (true) {
            String userCommand = scanner.nextLine();
            System.out.println("____________________________________________________________");

            if (userCommand.equalsIgnoreCase("bye")) {
                System.out.println(" bye bye, take care:)!");
                System.out.println("____________________________________________________________");
                break;
            } else if (userCommand.equalsIgnoreCase("list")) {
                for (int i = 0; i < taskCount; i++) {
                    System.out.println(" " + (i + 1) + ". " + newTasks[i]);
                }
                System.out.println("____________________________________________________________");
            } else {
                newTasks[taskCount] = userCommand;
                taskCount++;
                System.out.println(" added: " + userCommand);
                System.out.println("____________________________________________________________");
            }
        }

        scanner.close();
    }
}
