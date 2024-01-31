import java.util.Scanner;

public class Omoh {
    public static void main(String[] args) {
        printHorizontalLine();
        System.out.println("     Hello! I'm Omoh");
        System.out.println("     What can I do for you?");
        printHorizontalLine();
        echo();
        printHorizontalLine();
        System.out.println("     Bye. Hope to see you again soon!");
        printHorizontalLine();
    }

    //Method that prints horizontal line using "_" char
    private static void printHorizontalLine() {
        for (int j = 0; j < 4; j++) {
            System.out.print(" ");
        }
        for (int i = 0; i < 50; i++) {
            System.out.print("_");
        }
        System.out.println("");
        return;
    }

    //Method that echoes whatever user types.
    //Stops when string "bye" is entered.
    public static void echo() {
        String line;
        Scanner in = new Scanner(System.in);
        line = in.nextLine();
        while (!line.equalsIgnoreCase("bye")) {
            printHorizontalLine();
            System.out.print("     ");
            System.out.println(line);
            printHorizontalLine();
            line = in.nextLine();
        }
    }
}
