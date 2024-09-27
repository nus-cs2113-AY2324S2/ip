package Storage;

import ChelleCommands.Task;
import ChelleExceptions.SaveFileNotFoundException;

import java.util.ArrayList;

public class ChelleStorage {

    public String FILE_PATH = "./ChelleTasks.txt";

    public ChelleStorage(){
    }

    /**
     * Retrieves saved data from save file and runs it through the decoder.
     * Subsequently, loads the data of the tasks
     *
     * @return list of tasks from save file
     */
    public ArrayList<Task> loadTasks(){
        FileDecoder decoder = new FileDecoder(FILE_PATH);
        ArrayList<Task> tasks = new ArrayList<>();
        try {
            decoder.loadTasksFromFile(tasks);
        } catch (SaveFileNotFoundException ignored){
        }
        return tasks;
    }

    /**
     * Runs the existing task list through the encoder and saves it in the save file
     *
     * @param tasks task list
     */
    public void saveTasks(ArrayList<Task> tasks) {
        FileEncoder encoder = new FileEncoder(FILE_PATH);
        encoder.saveTasksToFile(tasks);
    }
}
