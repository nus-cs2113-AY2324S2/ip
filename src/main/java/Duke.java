import java.util.Scanner;

public class Duke {
    private static String username;
    public static final Scanner scan = new Scanner(System.in);
    public static String[] listData = new String[100];
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
        for (int index=0; index<listCount; index++) {
            System.out.printf("%d. %s\n", index+1, listData[index]);
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
            userInput = scan.nextLine();

            if (userInput.equalsIgnoreCase("bye")) {
                break;
            }

            if (userInput.equalsIgnoreCase("list")) {
                printList();
                continue;
            }

            listData[listCount] = userInput;
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
