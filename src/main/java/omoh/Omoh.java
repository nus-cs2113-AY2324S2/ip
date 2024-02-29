package omoh;

import omoh.tasktypes.Task;

import java.io.IOException;


public class Omoh {
    public static void main(String[] args) {
        Storage.createFileDirectory();
        Storage.createOrReadOutputFile();
        printWelcomeMessage();
        //initialise the size 100 array if it was not initialised previously
        //because if tasks were already initialised from reading output.txt, we don't need to initialise the array again
        if(Task.totalTasks == 0) {
            Task.initArray();
        }
        Ui.readUserInput();
        bye();
    }

    /**
     * Prints a horizontal line consisting of "_" characters.
     * The line is 50 characters long.
     */
    public static void printHorizontalLine() {
        for (int i = 0; i < 50; i++) {
            System.out.print("_");
        }
        System.out.println("");
    }

    /**
     * Prints a welcome message with the Omoh name.
     * The message includes a horizontal line above and below it.
     */
    public static void printWelcomeMessage() {
        printHorizontalLine();
        System.out.println("Hello! I'm Omoh");
        System.out.println("What can I do for you?");
        printHorizontalLine();
    }

    //Method that prints the bye message
    public static void bye() {
        printHorizontalLine();
        System.out.println("Bye. Hope to see you again soon!");
        printHorizontalLine();
        try {
            Task.writeToFile();
        } catch (IOException e) {
            System.out.println("Something went wrong" + e.getMessage());
        }
    }
}
