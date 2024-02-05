package Logic;

import java.io.Serializable;
import java.util.Scanner;

public class UI implements Serializable{
    public static String welcomeMessage = "Hello! I'm Mario!\nWhat can I do for you?";
    public static String byeMessage = "Bye. Hope to see you again soon!";
    private static final Scanner sc = new Scanner(System.in);
    
    public void printWithIndent(String args) {
        String lineIndent = "\n____________________________________________________________\n";
        System.out.println((lineIndent + "\n" + args + lineIndent).replace("\n", "\n\t"));
    }

    public void printError(Exception e) {
        printWithIndent("OOPS!! " + e.getMessage());
    }

    public void showWelcome(){
        printWithIndent(welcomeMessage);
    }

    public void showBye(){
        printWithIndent(byeMessage);
    }

    public String readCommand(){
        return sc.nextLine();
    }
}