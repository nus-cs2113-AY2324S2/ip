import java.util.Scanner;
public class Conversation {
    private static final String LOGO = "           _      _____        _____          \n" +
        "     /\\   | |    |  __ \\ /\\   / ____|   /\\    \n" +
        "    /  \\  | |    | |__) /  \\ | |       /  \\   \n" +
        "   / /\\ \\ | |    |  ___/ /\\ \\| |      / /\\ \\  \n" +
        "  / ____ \\| |____| |  / ____ \\ |____ / ____ \\ \n" +
        " /_/    \\_\\______|_| /_/    \\_\\_____/_/    \\_\\\n" +
        "                                              \n" +
        "                                              ";

    private static final String HORIZONTAL_LINE = "_____________" +
        "_______________________________________________\n";

    private static final String GREET = "Baa-baa-baa, I'm Alpaca!\n"
        + "How can I assist you today?\n";
    private static final String EXIT = "Bye. Hope to see you again soon, baa-baa-baa!\n";

    public static void executeTask() {
        TaskList taskList = new TaskList();

        Scanner userInput = new Scanner(System.in);
        String receivedMessage;
        String receivedCommand;
        String tempLine;
        int taskIndex;

        while (userInput.hasNextLine()) {
            receivedMessage = userInput.nextLine();
            receivedCommand = receivedMessage.split(" ")[0];

            switch (receivedCommand) {
            case "list":
                System.out.print(HORIZONTAL_LINE);
                taskList.listTasks();
                System.out.println(HORIZONTAL_LINE);
                break;

            case "mark":
                tempLine = receivedMessage.substring(5);
                taskIndex = Integer.parseInt(tempLine) - 1;
                if (taskList.isCountValid(taskIndex)) {
                    System.out.print(HORIZONTAL_LINE);
                    taskList.markTask(taskIndex);
                    System.out.print(HORIZONTAL_LINE);
                }
                else {
                    System.out.print(HORIZONTAL_LINE);
                    System.out.println("invalid index");
                    System.out.print(HORIZONTAL_LINE);
                }
                break;

            case "unmark":
                tempLine = receivedMessage.substring(7);
                taskIndex = Integer.parseInt(tempLine) - 1;
                if (taskList.isCountValid(taskIndex)) {
                    System.out.print(HORIZONTAL_LINE);
                    taskList.unmarkTask(taskIndex);
                    System.out.print(HORIZONTAL_LINE);
                }
                else {
                    System.out.print(HORIZONTAL_LINE);
                    System.out.println("invalid index");
                    System.out.print(HORIZONTAL_LINE);
                }
                break;

            case "bye":
                System.out.print(HORIZONTAL_LINE);
                System.out.print(EXIT + HORIZONTAL_LINE);
                return;

            default:
                Task newTask = new Task(receivedMessage);
                taskList.add(newTask);
                System.out.print(HORIZONTAL_LINE);
                System.out.println("added: " + receivedMessage);
                System.out.print(HORIZONTAL_LINE);
            }
        }
    }

    public static void startConversation(){

        System.out.println("Hello from\n" + LOGO);
        System.out.print(HORIZONTAL_LINE + GREET + HORIZONTAL_LINE);
        executeTask();
    }
}