import java.util.Scanner;

public class RoleyPoley {
    public static void main(String[] args) {
        greet();
        echo();
    }

    public static void createLine() {
        for (int i = 0; i < 20; i++) {
            System.out.print('-');
        }
        System.out.print('\n');
    }

    public static void greet() {
        System.out.println("Hello! I'm RoleyPoley \nWhat can I do for you today?");
        createLine();
    }

    public static void echo() {
        String line;
        Scanner in = new Scanner(System.in);
        while (true) {
            line = in.nextLine();
            if (line.equals("bye")) {
                System.out.println("Bye. Hope to see you again soon!");
                createLine();
                break;
            } else {
                System.out.println('\t' + line);
                createLine();
            }
        }
    }
}
