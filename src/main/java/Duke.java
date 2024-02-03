import java.util.Scanner;
import java.util.ArrayList;

public class Duke {
    public static void main(String[] args) {
        System.out.println("____________________________________________________________");
        System.out.println("Hello! I'm LeoDas");
        System.out.println("What can I do for you?");
        System.out.println("____________________________________________________________");

        Scanner scanner = new Scanner(System.in);
        ArrayList<String> tasks = new ArrayList<>();

        while (true) {
            String userInput = scanner.nextLine();

            if ("bye".equalsIgnoreCase(userInput)) {
                break;
            }

            if ("list".equalsIgnoreCase(userInput)) {
                System.out.println("____________________________________________________________");
                for (int j = 0; j < tasks.size(); j++) {
                    System.out.println((j + 1) + ". " + tasks.get(j));
                }
                System.out.println("____________________________________________________________");
            } else {
                System.out.println("____________________________________________________________");
                System.out.println("added: " + userInput);
                System.out.println("____________________________________________________________");
                tasks.add(userInput);
            }
        }

        System.out.println("____________________________________________________________");
        System.out.println("Bye. Hope to see you again soon!");
        System.out.println("____________________________________________________________");
    }
}