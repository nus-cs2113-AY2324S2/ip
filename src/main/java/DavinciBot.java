/**
 * The DavinciBot class is a simple to-do list application that allows users
 * to manage tasks, including adding, marking as done, deleting, listing tasks
 * and finding tasks.
 */
public class DavinciBot {
    public static void readFile() {
        TaskList.readFile();
    }

    private static void printStartingMessage() {
        Ui.printStartingMessage();
    }

    private static void userCommand() {
       Ui.userCommand();
    }

    public static void writeFile() {
        TaskList.writeFile();
    }

    public static void main(String[] args) {
        readFile();
        printStartingMessage();
        userCommand();
        writeFile();
    }
}
