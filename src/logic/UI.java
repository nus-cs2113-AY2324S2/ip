package logic;

import java.util.Scanner;

/**
 * The UI class in the Mario chat application provides methods for displaying messages and reading commands from the console.
 * It includes functionalities to print messages with indentations, handle error messages, and display welcome and goodbye messages.
 * This class encapsulates all user interface interactions through the console, utilizing a Scanner for input reading, 
 * thereby abstracting the details of console-based user interaction away from the main application logic.
 */

 
public class UI {
    public static String WELCOME_MESSAGE = "Hello! I'm Mario!\nWhat can I do for you?";
    public static String BYE_MESSAGE = "Bye. Hope to see you again soon!";
    private static final Scanner sc = new Scanner(System.in);
    
    public void printWithIndent(String args) {
        String lineIndent = "\n____________________________________________________________\n";
        System.out.println((lineIndent + "\n" + args + lineIndent).replace("\n", "\n\t"));
    }

    public void printError(Exception e) {
        printWithIndent("OOPS!! " + e.getMessage());
    }

    public void showWelcome(){
        printWithIndent(WELCOME_MESSAGE);
    }

    public void showBye(){
        printWithIndent(BYE_MESSAGE);
    }

    public String readCommand(){
        return sc.nextLine();
    }
}