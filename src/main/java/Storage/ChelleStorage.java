package Storage;

import ChelleCommands.Task;
import java.util.ArrayList;

public class ChelleStorage {

    public String FILE_PATH = "./ChelleTasks.txt";

    public ChelleStorage(){
    }

    public ArrayList<Task> loadTasks() {
        FileDecoder decoder = new FileDecoder(FILE_PATH);
        ArrayList<Task> tasks = new ArrayList<>();
        decoder.loadTasksFromFile(tasks);
        return tasks;
    }

    public void saveTasks(ArrayList<Task> tasks) {
        FileEncoder encoder = new FileEncoder(FILE_PATH);
        encoder.saveTasksToFile(tasks);
    }
}
