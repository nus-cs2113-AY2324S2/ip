/**
 * The DavinciBot class is a simple to-do list application that allows users
 * to manage tasks, including adding, marking as done, deleting, and listing tasks.
 */
public class DavinciBot {

    /**
     * Handles user commands and performs corresponding actions based on the input, calls the Ui
     * class.
     */
    private static void userCommand() {
       Ui.userCommand();
    }

    /**
     * Writes the current task list to a file.
     */
    public static void writeFile() {
        TaskList.writeFile();
    }

    /**
     * Reads tasks from a file and adds them to the task list.
     */
    public static void readFile() {
        TaskList.readFile();
    }

    public static void main(String[] args) {
        readFile();
        Ui.printStartingMessage();
        userCommand();
        writeFile();
    }
}
