package logic;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.util.Scanner;
import templates.TaskList;
import exceptions.MarioFileError;

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
