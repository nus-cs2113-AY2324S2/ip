import java.util.Scanner;

public class Duke {
    public static void main(String[] args) {
        Ui userInterface = new Ui();
        userInterface.printLogo();
        userInterface.printWelcome();

        TaskManager manager = new TaskManager();
        Scanner sc = new Scanner(System.in);

        while (true) {
            String userCommand = sc.next();
            String result;

            if (userCommand.equals("bye")) {
                break;
            } else if (userCommand.equals("list")) {
                result = manager.listTasks();
            } else if (userCommand.equals("mark") || userCommand.equals("unmark")) {
                int taskId = sc.nextInt();
                result = manager.updateTaskProgress(taskId, userCommand);
            } else {
                userCommand += sc.nextLine(); // Restore input as one line
                result = manager.addTask(userCommand);
            }
            userInterface.print(result);
        }

        userInterface.printExit();
    }
}
