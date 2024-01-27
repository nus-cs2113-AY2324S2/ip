import java.util.Scanner;

public class Joe {
    public static final String H_LINE = "____________________________________________________________\n";

    private static String getCommand(String input) {
        if (!input.contains(" ")) {
            return input;
        }
        return input.substring(0, input.indexOf(' '));
    }

    private static int convertMessageToInteger(String message) {
        int number;
        try {
            number = Integer.parseInt(message);
        } catch (NumberFormatException e) {
            number = -69;
        }
        return number;
    }

    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        System.out.println(H_LINE + "HI I'M JOE\n" + "WHAT CAN I DO FOR YOU\n" + H_LINE);

        TaskManager taskManager = new TaskManager();
        boolean hasExitInput = false;
        while (!hasExitInput) {
            String input = in.nextLine();
            input = input.trim();
            String inputCommand = getCommand(input);
            String message = input.replace(inputCommand, "").trim();
            switch (inputCommand) {
            case "":
                System.out.println(H_LINE + "PLEASE ENTER SOMETHING :(" + '\n' + H_LINE);
                break;
            case "bye":
                hasExitInput = true;
                break;
            case "list":
                taskManager.listTasks();
                break;
            case "mark":
            case "unmark":
                int taskNumber = convertMessageToInteger(message);
                taskManager.toggleTaskMarkedStatus(taskNumber,inputCommand);
                break;
            default:
                taskManager.addTask(input);
                System.out.println(H_LINE + "ADDED: " + input + '\n' + H_LINE);
                break;
            }
        }
        
        System.out.println(H_LINE + "GOODBYE. PLEASE COME BACK AGAIN :)\n" + H_LINE);
    }
}
