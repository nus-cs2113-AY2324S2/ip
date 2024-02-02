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
        LogicManager logicManager = new LogicManager(listKeeper);
        UserInterface userInterface = new UserInterface(logicManager);
        printWelcomeMessage();
        userInterface.manageList();
        printExitMessage();
    }
}
