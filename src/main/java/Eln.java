
/**
 * Implements a Task Managing CLI application that takes in user inputs to create
 * a task list and manage tasks through certain commands.
 *
 * @author nigelheng
 * @since February 2024
 * @version 1.0
 */
public class Eln {

    public static void main(String[] args) {
        UI.greeting();
        Storage.loadFile();
        Parser.taskManager(); // taskManager ends when user inputs "bye"
        UI.farewell();
    }
}