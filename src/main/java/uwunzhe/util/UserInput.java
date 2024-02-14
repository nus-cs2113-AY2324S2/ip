package uwunzhe.util;

import java.util.Scanner;

public final class UserInput {
    // Scanner object to get user input
    private static Scanner sc = new Scanner(System.in);    

    /**
     * Gets user input.
     * 
     * @param None
     * @return The user input.
     */
    public static String getInput() {
        Printer.addLineBreak();
        System.out.print(": ");
        String input = sc.nextLine();
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
    public static String getInput(String leadingString) {
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
    public static void closeScanner() {
        sc.close();
    }
}
