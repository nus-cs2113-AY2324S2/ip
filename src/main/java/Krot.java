import java.util.Scanner;

public class Krot {
    static String botName = "Krot";

    public static String readInput() {
        System.out.println("You:");
        Scanner in = new Scanner(System.in);
        return in.nextLine();
    }

    public static void greeting() {
        System.out.println("Hello! I'm " + botName);
        System.out.println("What can I do for you?");
        printSeparator();
    }

    public static void printSeparator() {
        System.out.println("-");
    }

    public static void endChat() {
        System.out.println("Bye. Hope to see you again soon!");
    }

    public static void echo(String line) {
        printSeparator();
        printName();
        System.out.println(line);
        printSeparator();
    }

    public static void printName() {
        System.out.println(botName + ":");
    }

    public static void main(String[] args) {
        String line = "";
        printSeparator();
        greeting();
        while (true) {
            line = readInput();
            if (line.equalsIgnoreCase("bye")) {
                break;
            }
            echo(line);
        }
        printSeparator();
        endChat();
    }
}
