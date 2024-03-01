package ui;

import java.util.Scanner;
import logic.LogicManager;
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

    public void startAtomicSection() {
        HorizontalGenerator.printHorizontal();
    }

    public void print(String message) {
        if (this.isUILocked) {
            return;
        }
        System.out.println(message);
    }

    public void endAtomicSection() {
        HorizontalGenerator.printHorizontal();
    }

    private void formatCurrentInput() {
        this.currentInput = this.currentInput.trim();
    }
    public void readNextLine() {
        do {
            this.currentInput = scanner.nextLine();
            this.formatCurrentInput();
        } while (this.currentInput.isEmpty());
    }
    
    public boolean isExitCommandGiven() {
        return this.currentInput.startsWith(Keywords.BYE);
    }
}
