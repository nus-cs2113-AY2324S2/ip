import tasks.Task;

import java.io.IOException;
import java.util.ArrayList;

/** Start of main class of CLI application
 * It shows a welcome message, creates and updates data file duck.txt, and takes in user input to execute tasks
 */
public class Duck {

    public static void main(String[] args) throws IOException {
        Ui.welcomeMessage();

        ArrayList<Task> tasks = new ArrayList<>(); //stores Tasks in ArrayList called tasks
        int index = 0; //index of where the userInput is stored in texts
        Storage.createNewFile();
        index = Storage.readFile(tasks,index);
        Parser.handleUserInput(tasks, index);
    }

}
