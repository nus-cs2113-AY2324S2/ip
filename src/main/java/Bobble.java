import java.util.Arrays;
import java.util.Scanner;

public class Bobble {
    public static void main(String[] args) {
        start();
        Scanner input = new Scanner(System.in);
        String userInput = input.nextLine();
        String userList[] = new String[100];
        int taskCount = 0;

        while (!userInput.equals("bye")) {
            switch (userInput) {
                case "list":
                    System.out.println("____________________________________________________________");
                    for (int i = 0; i < taskCount; i++) {
                        System.out.println((i + 1) + ". " + userList[i]);
                    }
                    System.out.println("____________________________________________________________\n");
                    break;
                default:
                    userList[taskCount++] = userInput;
                    System.out.println("____________________________________________________________\n" +
                            "added: " + userInput +
                            "\n____________________________________________________________\n");
            }
            userInput = input.nextLine();
        }
        goodbye();
    }

    //Greets user

    public static void start() {
        System.out.println("____________________________________________________________\n" +
                "Hello! I'm Bobble.\n" +
                "What can I do for you?" +
                "\n____________________________________________________________\n");
    }
    //Exits

    public static void goodbye() {
        System.out.println("____________________________________________________________\n" +
                "Bye. Hope to see you again soon!\n" +
                "____________________________________________________________\n");
    }

    //Echos commands entered by the user
    public static void echo(String message) {
        System.out.println("____________________________________________________________\n" +
                message +
                "\n____________________________________________________________\n");
    }
}
