import tasks.Task;

import java.io.IOException;
import java.util.ArrayList;

public class Duck {

    public static void main(String[] args) throws IOException {
        Ui.welcomeMessage();

        ArrayList<Task> tasks = new ArrayList<>(); //stores Tasks in ArrayList called tasks
        int index = 0; //index of where the userInput is stored in texts
        Save.createNewFile();
        index = Save.readFile(tasks,index);
        Ui.handleUserInput(tasks, index);
    }

}
