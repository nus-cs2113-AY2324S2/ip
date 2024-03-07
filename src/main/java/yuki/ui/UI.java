package yuki.ui;

import yuki.Utils;

import java.io.InputStream;
import java.util.Scanner;

/**
 * Handles interactions with user.
 */
public class UI {
    private final Scanner in;

    public UI() {
        this(System.in);
    }

    public UI (InputStream in) {
        this.in = new Scanner(in);
    }

    public void greetUser() {
        Utils.printWelcomeMessage();
    }

    public void sayByeToUser() {
        Utils.printExitMessage();
    }

    /**
     * Greets user, then accepts input that the user keys into the keyboard.
     */
    public String getUserInput() {
        System.out.println("Enter command:");
        String fullInputLine = in.nextLine();

        System.out.println("[Command entered: " + fullInputLine + "]");;
        return fullInputLine;
    }
}
