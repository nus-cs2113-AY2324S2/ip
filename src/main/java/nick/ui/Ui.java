package nick.ui;

import nick.Nick;
import nick.NickException;

import java.util.Scanner;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.logging.Logger;

public class Ui {

    private static final Logger LOGGER = Logger.getLogger(Nick.class.getName());
    Scanner input = new Scanner(System.in);
    public Ui() {

    }

    public void printByeMsg() {
        System.out.println("____________________________________________________________");
        System.out.println("\t" + "Bye. Hope to see you again soon!");
        System.out.println("____________________________________________________________");
    }

    public void printAddTaskMsg(String taskName, int taskCount) {
        System.out.println("____________________________________________________________");
        System.out.println("\t" + "Got it. I've added this task:");
        System.out.println("\t" + taskName);
        System.out.println("\t" + "Now you have " + taskCount + " tasks in the list.");
        System.out.println("____________________________________________________________");
    }

    public void printIntroName() {
        try {
            String name = new String(Files.readAllBytes(Paths.get("name.txt")));
            System.out.print(name);
        } catch (IOException exception) {
            LOGGER.severe(exception.toString());
        }
    }

    public void showWelcome() {
        printIntroName();
        System.out.println("____________________________________________________________");
        System.out.println("Welcome to the Ultimate Nick.Nick Bot!");
        System.out.println("What can I do for you?\n");
    }

    public void showLoadingError() {
        System.out.println("Oh no! It seems that I cannot locate your local storage file.");
    }

    public String readCommand() {
        return input.nextLine();
    }

    public void showLine() {
        System.out.println("____________________________________________________________");
    }

    public void showError() {
        System.out.println("Hmm I'm not sure what you're asking me to do.\nPlease refer to the user guide!");
    }
}
