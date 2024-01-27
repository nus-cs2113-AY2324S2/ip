import java.util.Scanner;
public class Yuki {

    public static void printLine() {
        System.out.println("---------------------------------------------");
    }
    public static void main(String[] args) {
        printLine();
        System.out.println("Hello! I am Yuki, your personal chat bot.");
        System.out.println("What can I do for you?");
        printLine();

        String line;
        Scanner in = new Scanner(System.in);
        line = in.nextLine();

        printLine();
        if (line.equals("bye")) {
            System.out.println("Bye! See you again.");
        } else {
            System.out.println("You said:" + line);
        }
        printLine();

    }
}
