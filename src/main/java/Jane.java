import java.util.Scanner;
public class Jane {
    public static void processInput(String input, TaskList taskList) throws JaneException {
        try {
            String[] inputPart = input.split(" ", 2);
            if (inputPart.length < 2 || inputPart[1] == null) {
                switch (inputPart[0]) {
                case "todo" :
                case "deadline" :
                case "event" :
                    throw new JaneException("Description for a " + inputPart[0] + " cannot be empty");
                }
            }
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
                taskList.markAsDone(Integer.parseInt(inputPart[1]) - 1);
                break;
            case "unmark":
                taskList.markAsUndone(Integer.parseInt(inputPart[1]) - 1);
                break;
            default:
                throw new JaneException("OOPS!!! I'm sorry, but I don't know what that means :-(");
            }
        } catch (JaneException e) {
            throw new JaneException(e.getMessage());
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
        String EXIT_MESSAGE = "Bye. Hope to see you again soon!\n";

        System.out.print(LOGO + SEPARATOR);
        System.out.print(GREET_MESSAGE + SEPARATOR);

        Scanner in = new Scanner(System.in);
        String input = in.nextLine();
        TaskList taskList = new TaskList();

        while (!input.equals("bye")) {
            try {
                System.out.print(SEPARATOR);
                processInput(input, taskList);
                System.out.print(SEPARATOR);
            } catch (JaneException e) {
                System.out.println(e.getMessage());
                System.out.print(SEPARATOR);
            }
            input = in.nextLine();
        }
        System.out.print(SEPARATOR + EXIT_MESSAGE + SEPARATOR);
    }
}