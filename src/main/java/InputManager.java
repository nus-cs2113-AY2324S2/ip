import java.util.Scanner;

public class InputManager {
    private Scanner scanner;

    public InputManager() {
        this.scanner = new Scanner(System.in);
    }

    public void listenForInput(LogicManager logicManager) {
        while (scanner.hasNextLine()) {
            String receivedMessage = scanner.nextLine().trim();
            String[] commandParts = receivedMessage.split(" ", 2);
            String command = commandParts[0];
            String details = commandParts.length > 1 ? commandParts[1] : "";

            // Decode the details based on the command type
            switch (command) {
            case "deadline":
                String[] deadlineParts = details.split(" /by ", 2);
                if (deadlineParts.length == 2) {
                    logicManager.addDeadline(deadlineParts[0], deadlineParts[1]);
                }
                break;
            case "event":
                String[] eventParts = details.split(" /from ", 2);
                if (eventParts.length == 2) {
                    String[] timeParts = eventParts[1].split(" /to ", 2);
                    if (timeParts.length == 2) {
                        logicManager.addEvent(eventParts[0], timeParts[0], timeParts[1]);
                    }
                }
                break;
            default:
                // For commands that do not require special decoding, pass them directly
                logicManager.executeCommand(command, details);
                break;
            }
        }
    }
}
