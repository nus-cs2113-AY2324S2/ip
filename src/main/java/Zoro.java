import java.util.Scanner;

public class Zoro {
    public static void main(String[] args) {
        TaskManager taskManager = new TaskManager();
        Scanner in = new Scanner(System.in);
        UserInterface userInterface = new UserInterface();
        userInterface.greetUser();

        while (true) {
            String input = UserInterface.getUserInput(in);

            if (input.equalsIgnoreCase("bye")) {
                break;
            }

            if (input.equalsIgnoreCase("list")) {
                taskManager.printTaskList();

            } else if (input.startsWith("mark ")) {
                taskManager.markTask(Integer.parseInt(input.substring(5).trim()) - 1);

            } else if (input.startsWith("unmark ")) {
                taskManager.unmarkTask(Integer.parseInt(input.substring(7).trim()) - 1);
            } else {
                taskManager.addTask(input);
            }
        }

        in.close();
        userInterface.sayGoodbye();
    }
}
