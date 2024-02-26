import command.UserInput;
import command.TaskList;

public class John {

    public static void main(String[] args) {

        printWelcomeMessage();
        TaskList.readData();

        UserInput.loopInterface();

        TaskList.storeData();
        printExitMessage();

    }

    private static void printExitMessage() {
        System.out.println("Bye. Hope to see you again soon!");
    }

    private static void printWelcomeMessage() {
        System.out.println("Hello! I'm John Chadbot.\n" + "What can I do for you?\n");
    }
}
