import java.util.Scanner;

public class Artemis {
    private static String username;
    public static final Scanner scan = new Scanner(System.in);
    public static Task[] taskList = new Task[100];
    public static int listCount = 0;

    public static void printBanner() {
        String logo = "  __ _ _ __| |_ ___ _ __ ___ (_)___ \n" +
                " / _` | '__| __/ _ \\ '_ ` _ \\| / __|\n" +
                "| (_| | |  | ||  __/ | | | | | \\__ \\\n" +
                " \\__,_|_|   \\__\\___|_| |_| |_|_|___/\n" +
                "                                    ";
        System.out.println("======================================\n");
        System.out.println(logo);
    }

    public static void printGoodbye() {
        System.out.println("======================================\n");
        System.out.println("goodbye! hope to see you again soon!\n");
        System.out.println("======================================\n");
    }

    public static void requestUsername() {
        System.out.println("hello! i'm artemis. what is your name?\n");
        System.out.print("[unknown user]: ");
        username = scan.nextLine();
    }

    public static void printList() {
        if (listCount == 0) {
            System.out.println("[Artemis]: Your list is empty!");
            return;
        }

        System.out.println("[Artemis]: Your list is as such:");
        for (int index = 0; index < listCount; index++) {
            Task currentTask = taskList[index];
            String doneStatus = currentTask.getStatusIcon();
            String taskName = currentTask.getTaskName();
            System.out.printf("%d. [%s] %s\n", index+1, doneStatus, taskName);
        }
    }

    public static void listHandler() {
        String userInput;

        System.out.printf("welcome to your personal list, %s!\n", username);
        System.out.println("enter any item to be added to the list.");
        System.out.println("you may also enter \"list\" to see your current list.");
        System.out.println("you may also exit by entering \"bye\".");

        for (;;) {
            System.out.printf("[%s]: ", username);
            userInput = scan.nextLine().toLowerCase();

            if (userInput.equals("bye")) {
                break;
            } else if (userInput.equals("list")) {
                printList();
                continue;
            } else if (userInput.startsWith("mark") || userInput.startsWith("unmark")) {
                String[] markList = userInput.split(" ");
                if (markList.length != 2) {
                    System.out.println("please enter \"mark/unmark <list item number>\".");
                    continue;
                }
                int markItem = Integer.parseInt(markList[1]) - 1;
                if (markItem > listCount) {
                    System.out.println("list item number given is invalid!");
                    continue;
                }

                String markAction = markList[0];
                taskList[markItem].markAsDone(markAction.equals("mark"));

                System.out.printf("alright! i have set \"%s\" as %s\n", taskList[markItem].getTaskName(), markAction.equals("mark") ? "complete" : "incomplete");

                continue;
            }

            Task newTask = new Task(userInput);
            taskList[listCount] = newTask;
            listCount++;

            System.out.printf("[artemis]: you have added %s to the list\n", userInput);
        }
    }

    public static void main(String[] args) {
        printBanner();
        requestUsername();
        listHandler();
        printGoodbye();
    }
}
