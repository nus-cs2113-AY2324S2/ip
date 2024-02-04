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

        while (userInput.hasNextLine()) {
            String receivedMessage = userInput.nextLine().trim();
            String[] commandParts = receivedMessage.split(" ", 2);
            String receivedCommand = commandParts[0];
            String details = commandParts.length > 1 ? commandParts[1] : "";

            switch (receivedCommand) {
            case "list":
                System.out.println(HORIZONTAL_LINE);
                taskList.listTasks();
                System.out.println(HORIZONTAL_LINE);
                break;

            case "todo":
                addTask(taskList, new Todo(details));
                break;

            case "deadline":
                String[] deadlineParts = details.split(" /by ", 2);
                if (deadlineParts.length == 2) {
                    addTask(taskList, new Deadline(deadlineParts[0], deadlineParts[1]));
                }
                break;

            case "event":
                String[] eventParts = details.split(" /from ", 2);
                if (eventParts.length == 2) {
                    String[] timeParts = eventParts[1].split(" /to ", 2);
                    if (timeParts.length == 2) {
                        addTask(taskList, new Event(eventParts[0], timeParts[0], timeParts[1]));
                    }
                }
                break;

            case "mark":
                int taskIndexMark = Integer.parseInt(details) - 1;

                if (taskList.isCountValid(taskIndexMark)) {
                    System.out.print(HORIZONTAL_LINE);
                    taskList.markTask(taskIndexMark);
                    System.out.print(HORIZONTAL_LINE);
                }
                else {
                    System.out.print(HORIZONTAL_LINE);
                    System.out.println("invalid index");
                    System.out.print(HORIZONTAL_LINE);
                }
                break;

            case "unmark":
                int taskIndexUnmark = Integer.parseInt(details) - 1;

                if (taskList.isCountValid(taskIndexUnmark)) {
                    System.out.print(HORIZONTAL_LINE);
                    taskList.unmarkTask(taskIndexUnmark);
                    System.out.print(HORIZONTAL_LINE);
                }
                else {
                    System.out.print(HORIZONTAL_LINE);
                    System.out.println("invalid index");
                    System.out.print(HORIZONTAL_LINE);
                }
                break;

            case "bye":
                System.out.println(HORIZONTAL_LINE);
                System.out.println(EXIT);
                System.out.println(HORIZONTAL_LINE);
                return;

            default:
                addTask(taskList, new Task(receivedMessage));
            }
        }
    }

    private static void addTask(TaskList taskList, Task task) {
        taskList.add(task);
        System.out.println(HORIZONTAL_LINE);
        System.out.println("Got it. I've added this task: " + System.lineSeparator() + task);
        System.out.println("Now you have " + taskList.getListCount() + " tasks in the list.");
        System.out.println(HORIZONTAL_LINE);
    }

    public static void startConversation(){

        System.out.println("Hello from\n" + LOGO);
        System.out.print(HORIZONTAL_LINE + GREET + HORIZONTAL_LINE);
        executeTask();
    }
}