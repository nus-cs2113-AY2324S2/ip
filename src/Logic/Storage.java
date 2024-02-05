package Logic;
import java.io.*;

import Templates.TaskList;
public class Storage {

    private void deleteFile(String filePath) {
        File myObj = new File(filePath);
        myObj.delete();
    }
    public void saveMario(Object obj, String filePath) {
        try {
            File file = new File(filePath);
            if (!file.exists()) {
                file.mkdirs();
            }

            deleteFile(filePath);

            FileOutputStream fileOut = new FileOutputStream(filePath);
            ObjectOutputStream objectOut = new ObjectOutputStream(fileOut);
            objectOut.writeObject(obj);
            objectOut.close();
            fileOut.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public TaskList loadMario(String filePath) {
        File myObj = new File(filePath);
        TaskList taskList = null;
        if (!myObj.exists()) {
            taskList = new TaskList();
            saveMario(taskList, filePath);
        } else {
            try {
                FileInputStream fileIn = new FileInputStream(filePath);
                ObjectInputStream objectIn = new ObjectInputStream(fileIn);
                taskList = (TaskList) objectIn.readObject();
                objectIn.close();
                fileIn.close();
            } catch (Exception e) {
                return new TaskList();
            }
        }
        return taskList;

    }
}
