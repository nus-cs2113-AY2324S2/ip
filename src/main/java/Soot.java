import java.util.Scanner;

public class Soot {
    public static void main(String[] args) {
        String line;
        String lowerCase;
        Scanner in = new Scanner(System.in);
        CommandManager commandManager = new CommandManager();

        boolean isBye = false;

        drawLine(); //initial greeting
        greetUser();

        while (!isBye) {
            line = in.nextLine(); //user input
            drawLine();
            isBye = commandManager.isInputBye(line);
        }
        greetGoodbye();
    }

    public static void drawLine() {
        int LINE_LENGTH = 60;
        for (int i = 0; i < LINE_LENGTH; i++) {
            System.out.print("_");
        }
        System.out.println("");
    }
    public static void greetUser() {
        System.out.println("Hello! I'm Soot, your personal chatbot companion :)");
        System.out.println("What can I help you with?");
        drawLine();
    }
    public static void greetGoodbye() {
        System.out.println("Bye! Till the next time we meet...");
        drawLine();
    }
}
