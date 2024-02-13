import java.util.Scanner;

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
        return currentInput.startsWith("bye");
    }

    private void formatCurrentInput() {
        currentInput = currentInput.trim();
    }

    private void printCurrentInput() {
        HorizontalGenerator.printHorizontal();
        System.out.println(currentInput);
        HorizontalGenerator.printHorizontal();
    }

    public void echo() {
        while (true) {
            readNextLine();
            if (isExitCommandGiven()) {
                return;
            }
            printCurrentInput();
        }
    }

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
