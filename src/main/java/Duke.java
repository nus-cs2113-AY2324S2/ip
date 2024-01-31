import java.util.Scanner;

public class Duke {
    public static void printBanner() {
        String logo = "  __ _ _ __| |_ ___ _ __ ___ (_)___ \n" +
                " / _` | '__| __/ _ \\ '_ ` _ \\| / __|\n" +
                "| (_| | |  | ||  __/ | | | | | \\__ \\\n" +
                " \\__,_|_|   \\__\\___|_| |_| |_|_|___/\n" +
                "                                    ";
        System.out.println("======================================\n");
        System.out.println(logo);
    }

    public static void main(String[] args) {
        printBanner();

        Scanner scan = new Scanner(System.in);
        String username;
        String userInput;

        System.out.println("Hello! I'm Artemis. What is your name?\n");
        System.out.print("[User]: ");
        username = scan.nextLine();
        System.out.printf("What can I do for you, %s?\n", username);

        for (;;) {
            System.out.printf("[%s]: ", username);
            userInput = scan.nextLine();

            if (userInput.equalsIgnoreCase("bye")) {
                break;
            }
            System.out.printf("[Artemis]: %s\n", userInput);
        }

        System.out.println("======================================\n");
        System.out.println("Bye. Hope to see you again soon!\n");
        System.out.println("======================================\n");
    }
}
