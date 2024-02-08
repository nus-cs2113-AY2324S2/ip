import java.util.Scanner;

public class Artemis {
    private static String username;
    public static final Scanner scan = new Scanner(System.in);
    public static Task[] taskList = new Task[100];
    public static int listCount = 0;

    public static void printBanner() {
        String logo = "  __ _ _ __| |_ ___ _ __ ___ (_)___" + System.lineSeparator() +
                " / _` | '__| __/ _ \\ '_ ` _ \\| / __|" + System.lineSeparator() +
                "| (_| | |  | ||  __/ | | | | | \\__ \\"  + System.lineSeparator() +
                " \\__,_|_|   \\__\\___|_| |_| |_|_|___/" + System.lineSeparator();
        System.out.println("======================================" + System.lineSeparator());
        System.out.println(logo);
    }

    public static void printGoodbye() {
        System.out.println("======================================");
        System.out.println("goodbye! hope to see you again soon!");
        System.out.println("======================================");
    }

    public static void requestUsername() {
        System.out.println("hello! i'm artemis. what is your name?");
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
            System.out.printf("%d. %s\n", index+1, currentTask);
        }
    }

    public static void listHandler() {
        String userInput;

        System.out.printf("welcome to your personal list, %s!%s", username, System.lineSeparator());
        System.out.println("usage: todo [item]");
        System.out.println("       deadline [item] /by [due date]");
        System.out.println("       event [item] /from [start] /to [end]");
        System.out.println("you may also enter \"list\" to see your current list.");
        System.out.println("you may also exit by entering \"bye\".");

        for (;;) {
            System.out.printf("[%s]: ", username);
            userInput = scan.nextLine().toLowerCase();

            if (userInput.equals("bye")) {
                break;
            } else if (userInput.equals("list")) {
                printList();
            } else if (userInput.startsWith("todo")) {
                String[] parseInput = userInput.split(" ", 2);
                String taskName = parseInput[1];
                ToDo newToDo = new ToDo(taskName);
                taskList[listCount] = newToDo;
                listCount++;

                System.out.printf("[artemis]: you have added %s to the list%s", userInput, System.lineSeparator());
            } else if (userInput.startsWith("event")) {
                String[] eventDetails = Parser.eventParser(userInput);
                Event newEvent = new Event(eventDetails[0], eventDetails[1], eventDetails[2]);
                taskList[listCount] = newEvent;
                listCount++;
                System.out.printf("[artemis]: you have added this task:%s %s%s", System.lineSeparator(), newEvent, System.lineSeparator());

            } else if (userInput.startsWith("deadline")) {
                String[] deadlineDetails = Parser.deadlineParser(userInput);
                Deadline newDeadline = new Deadline(deadlineDetails[0], deadlineDetails[1]);
                taskList[listCount] = newDeadline;
                listCount++;

                System.out.printf("[artemis]: you have added this task:%s %s%s", System.lineSeparator(), newDeadline, System.lineSeparator());

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

                System.out.printf("alright! i have set \"%s\" as %s%s", taskList[markItem].getTaskName(), markAction.equals("mark") ? "complete" : "incomplete", System.lineSeparator());
            }
        }
    }

    public static void main(String[] args) {
        printBanner();
        requestUsername();
        listHandler();
        printGoodbye();
    }
}
