import java.util.Scanner;

public class Xavier {
    public static void main(String[] args) {
        Scanner input = new Scanner(System.in);
        System.out.println("---------------------------------------------------------------------");
        System.out.println("Hello! I'm: " + "Xavier");
        System.out.println("What can I do for you?");
        System.out.println("---------------------------------------------------------------------");

        while (true) {
            String command = input.nextLine();
            if (command.equals("bye")) {
                System.out.println("---------------------------------------------------------------------");
                System.out.println("Bye. Hope to see you again soon!");
                System.out.println("---------------------------------------------------------------------");
                return;
            }
            else {
                System.out.println("---------------------------------------------------------------------");
                System.out.println(command);
                System.out.println("---------------------------------------------------------------------");
            }
        }
    }
}

