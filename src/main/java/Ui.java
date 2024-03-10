public class Ui {
    protected static void printLine() {
        System.out.println("____________________________________________________________");
    }

    protected static void displayGreetings() {
        printLine();
        System.out.println("Hala habibi! Shlonik? Shakhbarak?");
        System.out.println("I'm Nehsik, What can I do for you?");
        printLine();
    }

    protected static void displayExitMessage() {
        printLine();
        System.out.println("Yalla bye. Ka Mal Lah!");
        printLine();
    }

    protected static void displayErrorMessage(String errorMessage) {
        Ui.printLine();
        System.out.println(errorMessage);
        Ui.printLine();
    }
}
