import java.util.Scanner;

public class Hailey {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        System.out.println("Hello! I'm Hailey");
        System.out.println("What can I do for you?");

        while (true) {
            String input = scanner.nextLine();
            System.out.println(input);

            if (input.equalsIgnoreCase("bye")) {
                System.out.println("Bye. Hope to see you again soon!");
                break;
            }
        }

        scanner.close();
    }
}


