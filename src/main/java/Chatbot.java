import java.util.Objects;
import java.util.Scanner;

public class Chatbot {
    private final String CHATBOT_NAME;
    private final Task[] taskList = new Task[100];
    private int listLength = 0;
    private boolean isExit = false;
    String input;
    Task selectedItem;
    Scanner in = new Scanner(System.in);

    public Chatbot(String name) {
        this.CHATBOT_NAME = name;
    }

    public static void printTask(Task task) {
        System.out.println(task.getData());
    }

    public static void printLength(int length) {
        System.out.println("Now you have " + length + " tasks in the list.");
    }

    public void printResponse(String command) {
        switch (command) {
        case "bye":
            System.out.println("Bye. Hope to see you again soon! ");
            return;
        case "list":
            if (this.listLength > 0) {
                System.out.println("Here are the tasks in your list: ");
            } else {
                System.out.println("There are no tasks in your list. ");
            }
            return;
        case "unmark":
            System.out.println("OK, I've marked this task as not done yet: ");
            return;
        case "mark":
            System.out.println("Nice! I've marked this task as done: ");
            return;
        case "todo":
        case "deadline":
        case "event":
            System.out.println("Got it. I've added this task: ");
            return;
        default:
        }
    }

    public void initiate() {
        System.out.println("Hello! I'm " + CHATBOT_NAME + ". ");
        System.out.println("What can I do for you? ");
    }

    public void execute(String command, String description) throws ChatbotException {
        int inputNum;
        switch (command) {
        case "bye":
            printResponse(command);
            this.isExit = true;
            break;
        case "list":
            printResponse(command);
            for (int i = 0; i < listLength; i += 1) {
                System.out.print((i + 1) + ". ");
                printTask(taskList[i]);
            }
            break;
        case "unmark":
            inputNum = new Scanner(input).useDelimiter("\\D+").nextInt();
            if (inputNum > listLength) {
                throw new ChatbotException("This task does not exist. Select another. ");
            }
            selectedItem = taskList[inputNum - 1];
            selectedItem.markAsNotDone();
            printResponse(command);
            printTask(selectedItem);
            break;
        case "mark":
            inputNum = new Scanner(input).useDelimiter("\\D+").nextInt();
            if (inputNum > listLength) {
                throw new ChatbotException("This task does not exist. Select another. ");
            }
            selectedItem = taskList[inputNum - 1];
            selectedItem.markAsDone();
            printResponse(command);
            printTask(selectedItem);
            break;
        case "todo":
            taskList[listLength] = new Todo(description);
            printResponse(command);
            printTask(taskList[listLength]);
            listLength += 1;
            printLength(listLength);
            break;
        case "deadline":
            taskList[listLength] = new Deadline(description);
            printResponse(command);
            printTask(taskList[listLength]);
            listLength += 1;
            printLength(listLength);
            break;
        case "event":
            taskList[listLength] = new Event(description);
            printResponse(command);
            printTask(taskList[listLength]);
            listLength += 1;
            printLength(listLength);
            break;
        default:
            throw new ChatbotException("Unknown command. ");
        }
    }

    public void run() {
        do {
            try {
                System.out.println("-------------------");
                //input
                input = in.nextLine();
                String[] inputArray = input.split(" ", 2);
                String command = inputArray[0];
                String description = "";
                boolean isAddingTask = (Objects.equals(command, "todo") ||
                        Objects.equals(command, "deadline") || Objects.equals(command, "event"));
                if (isAddingTask) {
                    if (inputArray.length == 1) {
                        throw new ChatbotException("Empty task description. ");
                    } else {
                        description = input.split(" ", 2)[1];
                    }
                }

                this.execute(command, description);
            } catch (ChatbotException e) {
                e.printDescription();
            }
        } while (!this.isExit);
    }
}
