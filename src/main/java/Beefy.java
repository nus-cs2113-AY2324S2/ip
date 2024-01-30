import java.util.Scanner;
import java.util.ArrayList;
public class Beefy {
    private static final String BOTNAME = "BEEFY";
    private Scanner userInput = new Scanner(System.in);
    private ArrayList<String> tasks = new ArrayList<String>();

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
        System.out.println(BOTNAME);
        System.out.println("Hello! I'm " + BOTNAME);
        System.out.println("What can I do for you?");
        printSeparation();
        do {
            System.out.println("You");
            userLine = userInput.nextLine();
            printSeparation();
            if (userLine.equalsIgnoreCase("bye")) {
                break;
            }
            else if (userLine.equalsIgnoreCase("list")) {
                System.out.println(BOTNAME);
                System.out.println("Here are the things you said so far:");
                for (int i = 0; i < tasks.size(); i++) {
                    System.out.println((i + 1) + ". " + tasks.get(i));
                }
                printSeparation();
            }
            else {
                tasks.add(userLine);
                System.out.println(BOTNAME);
                System.out.println( "---" + userLine + " has been added to task list!---");
                printSeparation();
            }
        } while(!userLine.equalsIgnoreCase("bye"));
    }

    public void endChat() {
        System.out.println(BOTNAME + ": Bye. Hope to see you again soon!");
        printSeparation();
    }
}
