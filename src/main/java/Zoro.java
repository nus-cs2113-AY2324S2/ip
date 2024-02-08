import java.util.Scanner;

public class Zoro {
    public static final int MARK_BEGIN_INDEX = 5;
    public static final int UNMARK_BEGIN_INDEX = 7;

    public static void main(String[] args) {
        TaskManager taskManager = new TaskManager();
        Scanner in = new Scanner(System.in);
        UserInterface userInterface = new UserInterface();
        userInterface.greetUser();

        while (true) {
            String input = UserInterface.getUserInput(in);
            String inputType = input.split(" ")[0];
            if (inputType.equals("bye")) {
                break;
            }

            switch (inputType) {
            case "mark":
                taskManager.markTask(Integer.parseInt(input.substring(MARK_BEGIN_INDEX).trim()) - 1);
                break;
            case "unmark":
                taskManager.unmarkTask(Integer.parseInt(input.substring(UNMARK_BEGIN_INDEX).trim()) - 1);
                break;
            case "list":
                taskManager.printTaskList();
                break;
            default:
                taskManager.addTask(input);
                break;
            }
        }

        in.close();
        userInterface.sayGoodbye();
    }
}
