import java.util.Scanner;

public class Duke {
    public static void main(String[] args) {
        Ui userInterface = new Ui();
        userInterface.printLogo();
        userInterface.generateWelcome();

        TaskManager manager = new TaskManager();

        Scanner sc = new Scanner(System.in);
        boolean hasMoreInput = true;

        do {
            String userCommand = sc.nextLine();

            if (userCommand.equals("bye")) {
                hasMoreInput = false;
            } else if (userCommand.equals("list")){
                String result = manager.listTasks();
                userInterface.printResult(result);
            } else {
                String result = manager.addTask(userCommand);
                userInterface.printResult(result);
            }

        } while (hasMoreInput);

        userInterface.generateExit();
    }
}
