import java.util.Objects;
import java.util.Scanner;

public class Burger {
    static final String CHATBOT_NAME = "Burger";

    public static void main(String[] args) {
        printLine();
        System.out.println("Hello! I'm " + CHATBOT_NAME);
        System.out.println("What can I do for you?");
        printLine();
        Scanner input = new Scanner(System.in);
        while (true) {
            String text = input.nextLine();
            if (text.equalsIgnoreCase("bye")) {
                break;
            }
            echo(text);
        }
        goodbye();
    }

    public static void printLine() {
        System.out.println("---------------------------------");
    }

    public static void echo(String text) {
        printLine();
        System.out.println(text);
        printLine();
    }

    public static void goodbye() {
        printLine();
        System.out.println("Bye. Hope to see you again soon!");
        printLine();
    }

}
