package uwunzhe.util;

import java.util.Scanner;
import uwunzhe.util.Printer;

public final class Ui {
    // Scanner object to get user input
    private Scanner sc = new Scanner(System.in);

    /**
     * Prints logo and welcome message.
     * 
     * @param None
     * @return None
     */
    public void printInitMsg() {
        Printer.printInitMsg();
    }

    /**
     * Prints exit message.
     * 
     * @param None
     * @return None
     */
    public void printExitMsg() {
        Printer.printExitMsg();
    }

    /**
     * Gets user input.
     * 
     * @param None
     * @return The user input.
     */
    public String getInput() {
        Printer.addLineBreak();
        System.out.print(": ");
        String input = this.sc.nextLine();
        Printer.addLineBreak();
        return input;
    }

    /**
     * Gets user input.
     * Overloaded method.
     * 
     * @param leadingString The string to print before the user input.
     * @return The user input.
     */
    public String getInput(String leadingString) {
        Printer.addLineBreak();
        System.out.print(leadingString);
        String input = sc.nextLine();
        Printer.addLineBreak();
        return input;
    }
    
    /**
     * Closes the scanner object.
     * 
     * @param None
     * @return None
     */
    public void closeScanner() {
        this.sc.close();
    }
}
