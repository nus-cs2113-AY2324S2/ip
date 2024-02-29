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

    private void formatCurrentInput() {
        this.currentInput = this.currentInput.trim();
    }
    private void readNextLine() {
        do {
            this.currentInput = scanner.nextLine();
            this.formatCurrentInput();
        } while (this.currentInput.isEmpty());
    }
    
    private boolean isExitCommandGiven() {
        return this.currentInput.startsWith(Keywords.BYE);
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
            this.logicManager.processCommand(currentInput);
            HorizontalGenerator.printHorizontal();
        }
    }

}
