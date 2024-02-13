import java.util.Scanner;

public class Gandalf {
    // Constants
    public static final String LINE = "____________________________________________________________";
    public static final String BYE_STATEMENT = "bye";
    public static final String MAKE_LIST_STATEMENT = "make list";

    // Scanner object
    static Scanner in = new Scanner(System.in);

    public static void main(String[] args) {
        startMessage();
        startProgram();
        endMessage();
    }

    private static void startProgram() {
        while (true) {
            String userInput = getUserInput();
            if (hasSaidBye(userInput)) {
                System.out.println(LINE);
                return;
            }
            else if (isMakeList(userInput)) {
                makeList();
                return;
            } else {
                echoMessage(userInput);
            }
            System.out.println(LINE);
        }
    }

    private static void makeList() {
        makeListWelcomeMessage();

        Task[] listTasks = new Task[100];
        int insertIndex = 0;

        while (true) {
            String userInput = getUserInput();
            if (hasSaidBye(userInput)) {
                System.out.println(LINE);
                return;
            } else if (hasSaidList(userInput)) {
                TaskManager.printList(listTasks);
            } else if (hasSaidMarkOrUnmark(userInput)) {
                TaskManager.handleTasksMarkings(userInput, listTasks);
            } else {
                TaskManager.handleUserTasks(userInput, listTasks, insertIndex);
            }
        }
    }

    private static void makeListWelcomeMessage() {
        System.out.println(LINE);
        System.out.println("What would you like to be added to the list?");
        System.out.println(LINE);
    }

    private static boolean hasSaidMarkOrUnmark(String userInput) {
        return (userInput.startsWith("mark ") || userInput.startsWith("unmark "));
    }

    private static boolean hasSaidList(String userInput) {
        return userInput.equals("list");
    }

    private static boolean hasSaidBye(String text) {
        return text.equals(BYE_STATEMENT);
    }

    private static boolean isMakeList(String text) {
        return text.equals(MAKE_LIST_STATEMENT);
    }

    private static String getUserInput() {
        return in.nextLine();
    }

    private static void startMessage() {
        System.out.println(LINE);
        System.out.println("Hello! I'm Gandalf");
        System.out.println("What can I do for you? I'll start by repeating your words.");
        System.out.println("Type (make list) to create a To-Do List.");
        System.out.println(LINE);
    }

    private static void echoMessage(String text) {
        System.out.println(LINE);
        System.out.println(text);
    }

    private static void endMessage() {
        System.out.println("Bye. Hope to see you again soon!");
        System.out.println(LINE);
        in.close();
    }

}
