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
        ListKeeper listKeeper = new ListKeeper();
        SaveManager saveManager = new SaveManager();
        LogicManager logicManager = new LogicManager(listKeeper);
        UserInterface userInterface = new UserInterface(logicManager);

        saveManager.loadData(logicManager);

        printWelcomeMessage();
        userInterface.manageList();
        printExitMessage();

        listKeeper.saveTasksData(saveManager);
    }
}
