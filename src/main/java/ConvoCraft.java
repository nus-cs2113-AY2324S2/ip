import logic.LogicManager;
import storage.SaveManager;
import tasks.ListKeeper;
import ui.UserInterface;
import util.HorizontalGenerator;

public class ConvoCraft {
    private static void printWelcomeMessage() {
        HorizontalGenerator.printHorizontal();
        System.out.println("Hello! I'm ConvoCraft");
        System.out.println("What can I do for you?");
        HorizontalGenerator.printHorizontal();
    }

    private static void printExitMessage() {
        HorizontalGenerator.printHorizontal();
        System.out.println("Bye. Hope to see you again soon!");
        HorizontalGenerator.printHorizontal();
    }

    public static void main(String[] args) {
        UserInterface userInterface = new UserInterface();
        ListKeeper listKeeper = new ListKeeper(userInterface);
        SaveManager saveManager = new SaveManager();
        LogicManager logicManager = new LogicManager(listKeeper, userInterface);

        saveManager.loadData(logicManager);

        printWelcomeMessage();
        while (true) {
            userInterface.readNextLine();
            if (userInterface.isExitCommandGiven()) {
                break;
            }

            userInterface.startAtomicSection();
            logicManager.processCommand(userInterface.getCurrentInput());

            userInterface.setUILockStatus(true);
            listKeeper.saveTasksData(saveManager);
            userInterface.setUILockStatus(false);

            userInterface.endAtomicSection();
        }
        printExitMessage();

        listKeeper.saveTasksData(saveManager);
    }
}
