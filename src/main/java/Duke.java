import java.util.Scanner;

public class Duke {
    public static void main(String[] args) {

        // Greeting Message
        System.out.println(Constants.LOGO);
        System.out.println(Constants.LINE);
        System.out.println("Hello! I'm Orion!\n" + "What can I do for you?");
        System.out.println(Constants.LINE);

        Scanner scanner = new Scanner(System.in);
        String input;

        // Echo loop
        do {
            input = scanner.nextLine();
            System.out.println(Constants.LINE);
            
            if (!input.equalsIgnoreCase("bye")) {
                System.out.println(input);
            } else {
                System.out.println("Bye. Hope to see you again soon!");
            }

            System.out.println(Constants.LINE);
        } while (!input.equalsIgnoreCase("bye"));

        scanner.close();
    }
}