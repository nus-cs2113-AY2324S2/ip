/*public class Duke {
    public static void main(String[] args) {
        String logo = " ____        _        \n"
                + "|  _ \\ _   _| | _____ \n"
                + "| | | | | | | |/ / _ \\\n"
                + "| |_| | |_| |   <  __/\n"
                + "|____/ \\__,_|_|\\_\\___|\n";
        System.out.println("Hello from\n" + logo);
    }
}*/
import java.util.Objects;
import java.util.Scanner;
public class Duke {
    private static final String chatbotName = "Horizon";
    public static void printTask(Task task) {
        System.out.println(task.getData());
    }

    public static void printConfirmation(Task task) {
        System.out.println("Got it. I've added this task:");
        printTask(task);
    }
    public static void printLength(int length) {
        System.out.println("Now you have " + length + " tasks in the list.");
    }
    public static void main(String[] args) {
        System.out.println("Hello! I'm " + chatbotName + ". ");
        System.out.println("What can I do for you? ");

        String input;
        Task[] list;
        int listLength = 0;
        Task selectedItem;
        list = new Task[100];
        Scanner in = new Scanner(System.in);
        boolean exitCalled = false;
        do {
            System.out.println("-------------------");
            //input
            input = in.nextLine();
            String command = input.split(" ", 2)[0];
            String description = "";
            String startTime = "";
            String endTime = "";

            //splitting the data
            boolean addsTask = !(Objects.equals(command, "list") || Objects.equals(command, "bye"));
            if (addsTask) {
                String[] tempDataArray = input.split("/");
                System.out.println(tempDataArray[0]);
                String[] dataArray = {null, null, null};
                for (int i = 0; i < tempDataArray.length; i++) {
                    dataArray[i] = tempDataArray[i];
                }
                description = dataArray[0];
                startTime = dataArray[1];
                endTime = dataArray[2];
            }

            switch (command) {
            case "bye":
                System.out.println("Bye. Hope to see you again soon! ");
                exitCalled = true;
                break;
            case "list":
                System.out.println("Here are the tasks in your list: ");
                for (int i = 0; i < listLength; i += 1) {
                    System.out.println((i + 1) + ". " + list[i].getData());
                }
                break;
            case "unmark":
                System.out.println("OK, I've marked this task as not done yet: ");
                selectedItem = list[new Scanner(input).useDelimiter("\\D+").nextInt() - 1];
                selectedItem.markAsNotDone();
                printTask(selectedItem);
                break;
            case "mark":
                System.out.println("Nice! I've marked this task as done: ");
                selectedItem = list[new Scanner(input).useDelimiter("\\D+").nextInt() - 1];
                selectedItem.markAsDone();
                printTask(selectedItem);
                break;
            case "todo":
                list[listLength] = new Task(description);
                printConfirmation(list[listLength]);
                listLength += 1;
                printLength(listLength);
                break;
            case "deadline":
                list[listLength] = new Deadline(description, startTime);
                printConfirmation(list[listLength]);
                listLength += 1;
                printLength(listLength);
                break;
            case "event":
                list[listLength] = new Event(description, startTime, endTime);
                printConfirmation(list[listLength]);
                listLength += 1;
                printLength(listLength);
                break;
            default:
                break;
            }

        } while (!exitCalled);

    }
}
