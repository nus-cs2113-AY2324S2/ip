import java.util.Scanner;

public class Soot {
    public static void main(String[] args) {
        String line;
        String lowerCase;
        Scanner in = new Scanner(System.in);

        drawLine(); //initial greeting
        System.out.println("Hello! I'm Soot, your personal chatbot companion :)");
        System.out.println("What can I help you with?");
        drawLine();

        line = in.nextLine(); //user input
        drawLine();
        lowerCase = line.toLowerCase();

        while (!lowerCase.contains("bye")) {
            System.out.println(line);
            drawLine();

            line = in.nextLine(); //user input
            drawLine();
            lowerCase = line.toLowerCase();
        }
        byeGreeting();
    }

    public static void drawLine() {
        int length = 60;
        for (int i = 0; i < length; i++) {
            System.out.print("_");
        }
        System.out.println("");
    }

    public static void byeGreeting() {
        System.out.println("Bye! Till the next time we meet...");
        drawLine();
    }
}
