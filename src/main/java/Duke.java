import java.util.Scanner;

public class Duke {
    public static void main(String[] args) {

        System.out.println("____________________________________________________________");
        System.out.println("Hello! I'm Colin");
        System.out.println("What can I do for you?");
        System.out.println("____________________________________________________________");


        Scanner scanner = new Scanner(System.in);
        String userInput;


        while (true) {
            userInput = scanner.nextLine();

            if (userInput.equalsIgnoreCase("bye")) {
                break;
            }

            System.out.println("____________________________________________________________");
            System.out.println(userInput);
            System.out.println("____________________________________________________________");

        }

        System.out.println("____________________________________________________________");
        System.out.println("Bye. Hope to see you again soon!");
        System.out.println("____________________________________________________________");

        scanner.close();
    }
}