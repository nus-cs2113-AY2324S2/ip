package logic;

import java.util.Scanner;

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