import java.util.Scanner;
public class Katleen {
    public static final String line = "____________________________________________________________";

public static void echo(String text) {
    System.out.println(line);
    System.out.println(text);
    System.out.println(line);
}
    public static void main(String[] args) {
        System.out.println(line);
        System.out.println("Hello! I'm Katleen.");
        System.out.println("What can I do for you?");
        System.out.println(line);

        Scanner in = new Scanner(System.in);
        String text = in.nextLine();
        while (!text.equals("bye")) {
            echo(text);
            text = in.nextLine();
        }
        System.out.println(line);
        System.out.println("Bye, see you again!");
        System.out.println(line);

        return;
    }
}
