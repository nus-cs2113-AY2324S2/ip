import java.util.Scanner;

public class Duke {
    public static void main(String[] args) {
        String name = "Stella";
        System.out.println("Hello! I'm " + name);
        System.out.println("What can I do for you?");
        System.out.println("-----------------------------------");

        Scanner in = new Scanner(System.in);
        String line = in.nextLine();
        while (!line.equals("bye")) {
            Task.response(line);
            line = in.nextLine();
        }
        System.out.println("-----------------------------------");
        System.out.println("Bye. Hope to see you again soon!");
        System.out.println("-----------------------------------");
    }
}
