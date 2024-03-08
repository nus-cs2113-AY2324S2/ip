package storage;

import java.util.Scanner;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import task.TaskList;

/**
 * This Storage class is a file manager that stores the list of tasks.
 */
public class Storage {

    private static final String filePath = "tasks.txt";

    public Storage() {}

    /**
     * Saves the tasks before ending the chatbot session.
     *
     * @param tasks contain the tasks that were added during the session.
     * @throws IOException if the text file cannot be opened or written to etc.
     */
    public void writeToFile(TaskList tasks) throws IOException {
        FileWriter fw = new FileWriter(filePath);
        for(int i = 0; i < tasks.getTaskCount(); i++){
            fw.write(tasks.getTask(i).toString() + "\n");
        }
        fw.close();
    };

    /**
     * Loads pre-existing tasks from a text file.
     *
     * @return TaskList object containing the loaded tasks.
     * @throws FileNotFoundException if the text file cannot be found.
     */
    public TaskList readFromFile() throws FileNotFoundException {
        File f = new File(filePath);
        Scanner s = new Scanner(f);
        TaskList tasks = new TaskList();
        while(s.hasNext()) {
            String currentLine = s.nextLine();
            tasks.addTaskFromFile(currentLine);
        }
        return tasks;
    };
}
