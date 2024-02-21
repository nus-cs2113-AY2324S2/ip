import java.util.Scanner;
public class Jane {
    public static void processInput(String input, TaskList taskList) {
        String[] inputPart = input.split(" ", 2);
        switch (inputPart[0]) {
        case "todo":
            taskList.processTodo(inputPart[1]);
            break;
        case "deadline":
            taskList.processDeadline(inputPart[1]);
            break;
        case "event":
            taskList.processEvent(inputPart[1]);
            break;
        case "list":
            taskList.printList();
            break;
        case "mark":
            taskList.markAsDone(Integer.parseInt(inputPart[1])-1);
            break;
        case "unmark":
            taskList.markAsUndone(Integer.parseInt(inputPart[1])-1);
            break;
        default:
            // handle errors for input not starting with the correct keyword
            System.out.println("Usage: Please enter in the form of:\n" + "todo <TASK>\n" +
                    "deadline <TASK>\n" + "event <TASK>\n" + "mark <TASK_SEQUENCE>\n" + "unmark <TASK_SEQUENCE>");
            break;
        }
    }

    public static void main(String[] args) {
        String LOGO = " _____    _____    ____ _    _____ \n"
                + "|____ |  |     |  |    | |  | ____|\n"
                + "    | |  |  |  |  | |  | |  | |___ \n"
                + " _  | |  |  _  |  | |  | |  |  ___|\n"
                + "| |_| |  | | | |  | |  | |  | |___ \n"
                + "|_____|  |_| |_|  |_| ___|  |_____|\n";
        String SEPARATOR = "____________________________________________________________\n";
        String GREET_MESSAGE = "Hello! I am Jane.\nWhat can I do for you?\n";
        String EXIT_MESSAGE = "Bye. Hope to see you again soon!";

        System.out.print(LOGO + SEPARATOR);
        System.out.print(GREET_MESSAGE + SEPARATOR);

        Scanner in = new Scanner(System.in);
        String input = in.nextLine();
        TaskList taskList = new TaskList();

        while (!input.equals("bye")) {
            System.out.print(SEPARATOR);
            processInput(input, taskList);
            System.out.print(SEPARATOR);
            input = in.nextLine();
        }
        System.out.print(SEPARATOR + EXIT_MESSAGE + SEPARATOR);
    }
}