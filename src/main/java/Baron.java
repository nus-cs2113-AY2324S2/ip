import java.util.Scanner;

public class Baron {
    public static void main(String[] args) {
        System.out.println("Hello! I'm Baron");
        System.out.print("What can I do for you?\n");
        while (true) {
            Scanner userInput = new Scanner(System.in);
            String input = userInput.nextLine();
            if (input.equalsIgnoreCase("bye")) {
                System.out.println("Bye. Hope to see you again soon!");
                break;
            }
            System.out.println(input + "\n");
        }
    }
}