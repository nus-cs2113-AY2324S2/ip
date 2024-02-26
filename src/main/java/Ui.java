public class Ui {
    public static void showWelcomeMessage() {
        printLine();
        System.out.println("Hello! I'm Colin");
        System.out.println("Here are your current tasks:");
        Storage.loadTasksFromFile();
        System.out.println("What can I do for you?");
        printLine();
    }

    public static void showGoodbyeMessage() {
        System.out.println("Bye. Hope to see you again soon!");
    }

    public static void printLine() {
        System.out.println("____________________________________________________________");
    }
}
