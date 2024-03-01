package ui;

import java.util.Scanner;
import util.HorizontalGenerator;

public class UserInterface {
    private final Scanner scanner = new Scanner(System.in);
    private String currentInput;
    private boolean isUILocked;

    public UserInterface() {
        this.isUILocked = false;
    }

    /**
     * Locks or unlocks the UI.
     * @param isUILocked whether the UI is to be locked or unlocked
     */
    public void setUILockStatus(boolean isUILocked) {
        this.isUILocked = isUILocked;
    }

    public String getCurrentInput() {
        return this.currentInput;
    }

    /**
     * Prints a horizontal line to the console.
     */
    public void startAtomicSection() {
        HorizontalGenerator.printHorizontal();
    }

    /**
     * Prints a message to the console.
     * If the UI is locked, the message will not be printed.
     * @param message the message to be printed
     */
    public void print(String message) {
        if (this.isUILocked) {
            return;
        }
        System.out.println(message);
    }

    /**
     * Prints a horizontal line to the console.
     */
    public void endAtomicSection() {
        HorizontalGenerator.printHorizontal();
    }

    private void formatCurrentInput() {
        this.currentInput = this.currentInput.trim();
    }
    /**
     * Reads the next line of input from the user.
     * If the input is empty, the method will continue to read the next line until a non-empty input is obtained.
     */
    public void readNextLine() {
        do {
            this.currentInput = scanner.nextLine();
            this.formatCurrentInput();
        } while (this.currentInput.isEmpty());
    }
    
    /**
     * Checks if the user has given the exit command.
     * @return true if the user has given the exit command
     */
    public boolean isExitCommandGiven() {
        return this.currentInput.startsWith(Keywords.BYE);
    }
}
