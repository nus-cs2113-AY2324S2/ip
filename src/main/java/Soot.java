import java.util.Scanner;

public class Soot {
    private static int listCounter = 0;
    public static void main(String[] args) {
        String line;
        String lowerCase;
        Scanner in = new Scanner(System.in);
        String[] list = new String[100];

        boolean isBye = false;

        drawLine(); //initial greeting
        System.out.println("Hello! I'm Soot, your personal chatbot companion :)");
        System.out.println("What can I help you with?");
        drawLine();

        while (!isBye) {
            line = in.nextLine(); //user input
            drawLine();
            lowerCase = line.toLowerCase();
            isBye = verifyInput(list, line);
        }
    }

    public static boolean verifyInput(String[] list, String input) {
        String lowerCase = input.toLowerCase();

        switch (lowerCase) {
        case "bye":
            byeGreeting();
            return true;
        case "list":
            System.out.println(list);
        default:
            System.out.println("added: " + input);
            drawLine();

            list[listCounter] = input;
            listCounter++;
        }
        return false;
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
