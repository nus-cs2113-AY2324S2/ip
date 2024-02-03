import java.util.Scanner;

public class Joey {
    public static void main(String[] args) {
        System.out.println("Hey! I'm Joey");
        System.out.println("What can I do for you?");
        System.out.println("____________________________________________________________");

        Scanner scanner = new Scanner(System.in);

        while (true) {
            String userCommand = scanner.nextLine();
            if (userCommand.equalsIgnoreCase("bye")) {
                System.out.println("____________________________________________________________");
                System.out.println(" Bye. Hope to see you again soon!");
                System.out.println("____________________________________________________________");
                break;
            }
            System.out.println("____________________________________________________________");
            System.out.println(" " + userCommand);
            System.out.println("____________________________________________________________");
        }
        scanner.close();
    }
}

