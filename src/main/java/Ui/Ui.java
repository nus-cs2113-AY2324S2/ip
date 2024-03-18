package Ui;

import java.util.Scanner;

/**
 * Handles user interface interactions for the chatbot.
 * Provides methods for reading user input and printing messages to the console.
 */
public class Ui {
    private static final Scanner IN = new Scanner(System.in);

    /**
     * Reads a line of input from the user.
     *
     * @return The user's input as a single string.
     */
    public String readUserInput(){
        return IN.nextLine();
    }

    /**
     * Prints a greeting message to the user at the start of the session.
     */
    public void printGreetMessage(){
        print(new String[]{"Hi, I am here. Greets from Ruby.", "What can I do for you?"});
    }

    /**
     * Prints a goodbye message to the user at the end of the session.
     */
    public void printByeMessage(){
        print("Bye. Always feels good work with you.");
    }

    /**
     * Prints a formatted message to the console.
     *
     * @param thingToPrint The message to be printed.
     */
    private static void print(String thingToPrint){
        System.out.println("    " + "-----RUBY-----");
        System.out.println("    " + thingToPrint);
        System.out.println("    " + "--------------");
    }

    /**
     * Prints multiple formatted messages to the console.
     *
     * @param thingsToPrint An array of messages to be printed.
     */
    private static void print(String[] thingsToPrint){
        System.out.println("    " + "-----RUBY-----");
        for (String thing: thingsToPrint){
            System.out.println("    " + thing);
        }
        System.out.println("    " + "--------------");
    }
}
