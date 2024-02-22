import java.util.Scanner;

public class Zoro {
    public static final int MARK_BEGIN_INDEX = 5;
    public static final int UNMARK_BEGIN_INDEX = 7;
    public static final int COMMAND_START_INDEX = 0;
    public static final int INDEX_OFFSET = 1;

    public static void main(String[] args) {
        TaskManager taskManager = new TaskManager();
        Scanner in = new Scanner(System.in);
        UserInterface userInterface = new UserInterface();
        userInterface.greetUser();

        while (true) {
            String input = UserInterface.getUserInput(in);
            String inputType = input.split(" ")[COMMAND_START_INDEX];
            if (inputType.equalsIgnoreCase("bye")) {
                break;
            }
            try {
                switch (inputType) {
                case "mark":
                    taskManager.markTask(Integer.parseInt(input.substring(MARK_BEGIN_INDEX).trim()) - INDEX_OFFSET);
                    break;
                case "unmark":
                    taskManager.unmarkTask(
                            Integer.parseInt(input.substring(UNMARK_BEGIN_INDEX).trim()) - INDEX_OFFSET);
                    break;
                case "list":
                    taskManager.printTaskList();
                    break;
                default:
                    taskManager.addTask(input);
                    break;
                }
            } catch (IndexOutOfBoundsException e) {
                userInterface.printInvalidTaskIndex(e);
            } catch (NumberFormatException e) {
                userInterface.printInvalidInputIndex(e);
            } finally {
                taskManager.saveTasksToFile();
            }
        }

        in.close();
        userInterface.sayGoodbye();
    }
}
