import java.util.Objects;
import java.util.Scanner;
public class Duke {
    private static final String CHATBOT_NAME = "Horizon";
    public static void printTask(Task task) {
        System.out.println(task.getData());
    }
    public static void printResponse(String command) {
        switch (command) {
        case "bye":
            System.out.println("Bye. Hope to see you again soon! ");
            return;
        case "list":
            System.out.println("Here are the tasks in your list: ");
            return;
        case "unmark":
            System.out.println("OK, I've marked this task as not done yet: ");
            return;
        case "mark":
            System.out.println("Nice! I've marked this task as done: ");
            return;
        default:
            System.out.println("Got it. I've added this task:");
        }
    }
    public static void printLength(int length) {
        System.out.println("Now you have " + length + " tasks in the list.");
    }
    public static void main(String[] args) {
        System.out.println("Hello! I'm " + CHATBOT_NAME + ". ");
        System.out.println("What can I do for you? ");

        String input;
        Task[] taskList = new Task[100];
        int listLength = 0;
        Task selectedItem;
        Scanner in = new Scanner(System.in);
        do {
            System.out.println("-------------------");
            //input
            input = in.nextLine();
            String command = input.split(" ", 2)[0];
            String description = "";
            String startTime = "";
            String endTime = "";

            //splitting the data
            boolean isAddingTask = !(Objects.equals(command, "list") || Objects.equals(command, "bye"));
            if (isAddingTask) {
                String[] tempDataArray = input.split("/");
                System.out.println(tempDataArray[0]);
                String[] dataArray = {null, null, null};
                System.arraycopy(tempDataArray, 0, dataArray, 0, tempDataArray.length);
                description = dataArray[0];
                startTime = dataArray[1]; //if any
                endTime = dataArray[2]; //if any
            }

            printResponse(command);
            switch (command) {
            case "bye":
                return;
            case "list":
                for (int i = 0; i < listLength; i += 1) {
                    System.out.print((i + 1) + ". ");
                    printTask(taskList[i]);
                }
                break;
            case "unmark":
                selectedItem = taskList[new Scanner(input).useDelimiter("\\D+").nextInt() - 1];
                selectedItem.markAsNotDone();
                printTask(selectedItem);
                break;
            case "mark":
                selectedItem = taskList[new Scanner(input).useDelimiter("\\D+").nextInt() - 1];
                selectedItem.markAsDone();
                printTask(selectedItem);
                break;
            case "todo":
                taskList[listLength] = new Task(description);
                printTask(taskList[listLength]);
                listLength += 1;
                printLength(listLength);
                break;
            case "deadline":
                taskList[listLength] = new Deadline(description, startTime);
                printTask(taskList[listLength]);
                listLength += 1;
                printLength(listLength);
                break;
            case "event":
                taskList[listLength] = new Event(description, startTime, endTime);
                printTask(taskList[listLength]);
                listLength += 1;
                printLength(listLength);
                break;
            default:
                break;
            }

        } while (true);

    }
}
