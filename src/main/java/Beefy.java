import java.util.Scanner;
public class Beefy {
    private static final String BOTNAME = "BEEFY";
    private Scanner userInput = new Scanner(System.in);

    /**
     * Prints a separation row of n "-", where n = WIDTH
     */
    private void printSeparation() {
        int WIDTH = 59;
        for (int i = 0; i < WIDTH; i++) {
            System.out.print("_");
        }
        System.out.println("_");
    }

    public void startChat() {
        String userLine;
        printSeparation();
        System.out.println(BOTNAME + ": Hello! I'm " + BOTNAME);
        System.out.println(BOTNAME + ": What can I do for you?");
        printSeparation();
        do {
            System.out.print("You: ");
            userLine = userInput.nextLine();
            printSeparation();
            if (!userLine.equalsIgnoreCase("bye")) {
                System.out.println(BOTNAME + ": " + userLine);
                printSeparation();
            }
        } while(!userLine.equalsIgnoreCase("bye"));
    }

    public void endChat() {
        System.out.println(BOTNAME + ": Bye. Hope to see you again soon!");
        printSeparation();
    }
}
