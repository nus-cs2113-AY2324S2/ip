import java.util.Scanner;

public class BobBot {

    public static void echoCommand(String lineString) {
        drawLine(true);
        System.out.println("\t" + lineString);
        drawLine(true);
        System.out.println();
    }

    public static void drawLine(Boolean includeIndentation) {
        if (includeIndentation) {
            System.out.print("\t");
        }
        else {
            System.out.print("________");
        }
        System.out.println("__________________________________");
    }

    public static void greet() {
        drawLine(false);
        System.out.println("Hello! I'm BobBot");
        System.out.println("Type in anything!");
        drawLine(false);
    }
    public static void main(String[] args) {
        greet();

        String line;
        Scanner in = new Scanner(System.in);

        line = in.nextLine();
        
        while (!line.contains("bye")) {
            echoCommand(line);
            line = in.nextLine();
        }

        drawLine(true);
        System.out.println("\tBye. Hope to see you again soon!");
        drawLine(true);
    }
}
