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
    
    public Storage(String filePath) {
        this.filePath = filePath;
    }

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
            System.out.println(MESSAGE_INVALID_FILEPATH);
            System.out.println(e);
        }
    }

}
