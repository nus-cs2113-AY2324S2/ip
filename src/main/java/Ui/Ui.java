package Ui;

import java.util.Scanner;

public class Ui {
    private static final Scanner IN = new Scanner(System.in);

    /**
     * Captures and splits the user's input into a command and arguments.
     *
     * @return An array of strings, where the first element is the command, and subsequent elements are arguments.
     */
    public String readUserInput(){
        return IN.nextLine();
    }
    /**
     * Prints a greeting message to the user.
     */
    public void printGreetMessage(){
        print(new String[]{"Hi, I am here. Greets from Ruby.", "What can I do for you?"});
    }

    /**
     * Prints an exit message and terminates the chatbot.
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
