import java.util.Scanner;
public class Mona {

    public static void printHorizontalLine() {
        for (int i = 0; i < 59; i++) {
            System.out.print("_");
        }
        System.out.println("_");
    }
    public static void greet() {
        printHorizontalLine();

        System.out.println("Hello! I'm Mona");
        System.out.println("What can I do for you?");

        printHorizontalLine();
    }

    public static void exit() {
        printHorizontalLine();

        System.out.println("Bye. Hope to see you again soon!");

        printHorizontalLine();
    }
    public static void main(String[] args) {
        String logo = "___  ___                  \n"
                + "|  \\/  |                  \n"
                + "| .  . | ___  _ __   __ _ \n"
                + "| |\\/| |/ _ \\| '_ \\ / _` |\n"
                + "| |  | | (_) | | | | (_| |\n"
                + "\\_|  |_/\\___/|_| |_|\\__,_|\n";
        System.out.println("Hello from\n" + logo);

        greet();

        Scanner in = new Scanner(System.in);
        String line = in.nextLine();

        while (true) {
            if (line.equals("bye")) {
                exit();
                break;
            }
            printHorizontalLine();
            System.out.println(line);
            printHorizontalLine();
            line = in.nextLine();
        }
    }
}
