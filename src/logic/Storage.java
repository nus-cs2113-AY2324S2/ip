package logic;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.util.Scanner;
import templates.TaskList;
import exceptions.MarioFileError;

/**
 * The Storage class manages file operations for the Mario chat application, specifically for saving and loading tasks.
 * It offers functionality to save the current state of tasks to a file and load them upon application startup.
 * The class provides methods to delete an existing file, save tasks to a file, and load tasks from a file,
 * ensuring data persistence across application sessions. Exception handling is implemented to manage potential file access errors.
 */

 
public class Storage{

    private static Scanner sc;

    private void deleteFile(String filePath) {
        File myObj = new File(filePath);
        myObj.delete();
    }

    public void saveMario(TaskList obj, String filePath) {
        try {
            deleteFile("Mario.txt");
            FileWriter myWriter = new FileWriter("Mario.txt");
            myWriter.write(obj.toString());
            myWriter.close();

        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    public TaskList loadMario(String filePath) throws Exception{
        TaskList taskList = new TaskList();
        try {
            File myObj = new File("Mario.txt");
            sc = new Scanner(myObj);
            while (sc.hasNextLine()) {
                String data = sc.nextLine();
                if (data.contains("[")) {
                    Parser.parseTaskFromString(data, taskList);
                }
            }
            sc.close();
        } catch (FileNotFoundException e) {
            throw new MarioFileError();
        }
        sc.close();
        return taskList;

    }
}
