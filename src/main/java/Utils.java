public class Utils {
    public static void printLine() {
        System.out.println("---------------------------------------------");
    }

    public static void printWelcomeMessage() {
        printLine();
        System.out.println("I am Yuki, your personal chat bot and your evil twin.");
        System.out.println("Time to get grinding.");
        printLine();
    }

    public static void printExitMessage() {
        printLine();
        System.out.println("Breaks are only for the weak.");
        printLine();
    }

    public static void printInstructions() {
        System.out.println("Accepted commands are:");
        System.out.println("list: shows you list of tasks");
        System.out.println("mark: marks a task as done");
        System.out.println("unmark: marks a task as undone");
        System.out.println("todo: adds a todo");
        System.out.println("deadline: adds a deadline");
        System.out.println("event: adds an event");
    }
}
