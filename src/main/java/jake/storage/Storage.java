package jake.storage;

import java.util.Scanner;
import java.io.File; 
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import jake.task.TaskList;
import static jake.common.Messages.MESSAGE_INVALID_FILEPATH;
import static jake.common.Messages.MESSAGE_TASK_ERROR_ENCOUNTERED;

public class Storage {
    private String filePath;
    
    /**
     * Handles loading tasks from the file and saving tasks in the file.
     *
     * @param filePath Relative file path to the textfile where data is being saved, aka tasks.txt.
     */
    public Storage(String filePath) {
        this.filePath = filePath;
    }

    /**
     * Reads all current tasks saved in TaskList, and writes it to the file tasks.txt.
     * If tasks.txt is not found, print an error message.
     *
     * @param task TaskList of all current tasks.
     */
    public void saveTasks(TaskList tasks) {
        try {
            FileWriter writer = new FileWriter(filePath);
            for (int i = 0; i < tasks.size(); i++){
                writer.write(tasks.get(i) + System.lineSeparator());
            }
            writer.close();
        } catch (IOException e) {
            System.out.printf(MESSAGE_TASK_ERROR_ENCOUNTERED, e.getMessage());
        }
    }

    /**
     * Loads up all previous tasks saved in tasks.txt into the new TaskList.
     * If file is not found, print an error message.
     *
     * @param task TaskList of all previous tasks saved within tasks.txt.
     */
    public void loadTasks(TaskList tasks) {
        try {
            File savedFile = new File(filePath);
            Scanner savedFileScanner = new Scanner(savedFile);
            while (savedFileScanner.hasNext()) {
                String userInput = savedFileScanner.nextLine();
                char taskType = userInput.charAt(1);
                tasks.addSavedTask(userInput, taskType);               
            }
            savedFileScanner.close();
        } catch (FileNotFoundException e) {
            System.out.printf(MESSAGE_INVALID_FILEPATH, e.getMessage());
        }
    }
}
