package ui;

import java.util.Scanner;
import logic.LogicManager;
import util.HorizontalGenerator;

public class UserInterface {
    private final Scanner scanner = new Scanner(System.in);
    private final LogicManager logicManager;
    private String currentInput;

    public UserInterface(LogicManager logicManager) {
        this.logicManager = logicManager;
    }

    private void readNextLine() {
        do {
            currentInput = scanner.nextLine();
        } while (currentInput.isEmpty());
    }
    
    private boolean isExitCommandGiven() {
        return currentInput.startsWith(Keywords.BYE);
    }

    private void formatCurrentInput() {
        currentInput = currentInput.trim();
    }

    private void printCurrentInput() {
        HorizontalGenerator.printHorizontal();
        System.out.println(currentInput);
        HorizontalGenerator.printHorizontal();
    }

    /**
     * Echoes the user input back to the user.
     */
    public void echo() {
        while (true) {
            readNextLine();
            if (isExitCommandGiven()) {
                return;
            }
            printCurrentInput();
        }
    }

    /**
     * Runs the main loop of the program.
     * Reads the user input and passes it to the LogicManager for processing.
     * Exits when the user inputs the exit command.
     */
    public void manageList() {
        while (true) {
            readNextLine();
            if (isExitCommandGiven()) {
                return;
            }
            formatCurrentInput();
            logicManager.processCommand(currentInput);
            HorizontalGenerator.printHorizontal();
        }
    }

}
