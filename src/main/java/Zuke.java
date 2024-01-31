import java.util.Scanner;

public class Zuke {
    private static final String INDENTATION_LINE =
            "____________________________________________________________\n";

    private static boolean isNumeric(String string) {
        for (char c : string.toCharArray()) {
            if(!Character.isDigit(c)) {
                return false;
            }
        }
        return true;
    }

    public static void main(String[] args) {
        String logo =
                "███████╗██╗   ██╗██╗  ██╗███████╗\n" +
                "╚══███╔╝██║   ██║██║ ██╔╝██╔════╝\n" +
                "  ███╔╝ ██║   ██║█████╔╝ █████╗  \n" +
                " ███╔╝  ██║   ██║██╔═██╗ ██╔══╝  \n" +
                "███████╗╚██████╔╝██║  ██╗███████╗\n" +
                "╚══════╝ ╚═════╝ ╚═╝  ╚═╝╚══════╝\n";
        String receivedMessage;
        String markIndex;
        TaskList taskList = new TaskList();

        System.out.println("Hello from\n" + logo);
        System.out.println(INDENTATION_LINE +
                "Hello! I'm Zuke\n" +
                "What can I do for you?\n" +
                INDENTATION_LINE);

        Scanner userInput = new Scanner(System.in);
        while (userInput.hasNextLine()) {
            receivedMessage = userInput.nextLine();
            System.out.println(INDENTATION_LINE);
            String command = receivedMessage.split(" ")[0];

            switch(command) {
            case "bye":
                System.out.println("Bye. Hope to see you again soon!\n" +
                        INDENTATION_LINE);
                userInput.close();
                return;

            case "list":
                taskList.listTasks();
                System.out.println(INDENTATION_LINE);
                break;

            case "mark" :
                markIndex = receivedMessage.substring(4).trim();
                if (isNumeric(markIndex)) {
                    taskList.markTask(Integer.parseInt(markIndex));
                    System.out.println(INDENTATION_LINE);
                } else {
                    taskList.add(new Task(receivedMessage));
                    System.out.println("added: " +
                            receivedMessage + "\n" +
                            INDENTATION_LINE);
                }
                break;

            case "unmark" :
                markIndex = receivedMessage.substring(6).trim();
                if (isNumeric(markIndex)) {
                    taskList.unmarkTask(Integer.parseInt(markIndex));
                    System.out.println(INDENTATION_LINE);
                } else {
                    taskList.add(new Task(receivedMessage));
                    System.out.println("added: " +
                            receivedMessage + "\n" +
                            INDENTATION_LINE);
                }
                break;

            default:
                taskList.add(new Task(receivedMessage));
                System.out.println("added: " +
                        receivedMessage + "\n" +
                        INDENTATION_LINE);
                break;
            }
        }
    }
}
