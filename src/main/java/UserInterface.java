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

    // Assume currentInput is updated;
    private boolean isExitCommandGiven() {
        return currentInput.startsWith("bye");
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
            currentInput = currentInput.trim();
            logicManager.processCommand(currentInput);
            HorizontalGenerator.printHorizontal();
        }
    }

}
