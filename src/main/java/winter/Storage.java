package winter;

import winter.checkedexceptions.WinterException;
import winter.task.Deadline;
import winter.task.Event;
import winter.task.Task;
import winter.task.ToDo;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

public class Storage {
    private static String filePath;

    public Storage(String acceptedFilePath) {
        try {
            filePath = acceptedFilePath;
            readFile();

        } catch (IOException e) {
            System.out.println("There's no file in storage!");
        }
    }

    protected ArrayList<Task> readFile () throws IOException {
        File f = new File(filePath);
        Scanner s = new Scanner(f);
        ArrayList <Task> taskArrayList = new ArrayList<>();
        int taskIndex = 0;
        while (s.hasNext()) {
            String inputLine = s.nextLine();
            String[] inputArray = inputLine.split(" \\| ");
            int taskType = 0;
            int isMarkedIndex  = 1;
            int taskNameIndex = 2;
            int firstTimeIndex = 3;
            int endTimeIndex = 4;
            switch(inputArray[taskType]) {
            case "E":
                taskArrayList.add(new Event(taskIndex,Boolean.parseBoolean(inputArray[isMarkedIndex]),
                            inputArray[taskNameIndex],inputArray[firstTimeIndex],inputArray[endTimeIndex]));
                break;
            case "D":
                taskArrayList.add(new Deadline(taskIndex,Boolean.parseBoolean(inputArray[isMarkedIndex]),
                            inputArray[taskNameIndex],inputArray[firstTimeIndex]));
                break;
            case "T":
                taskArrayList.add(new ToDo(taskIndex,Boolean.parseBoolean(inputArray[isMarkedIndex]),inputArray[taskNameIndex]));
                break;
            default:
                System.out.println("Error retrieving task from file.");
            }
            taskIndex++;
        }
        return taskArrayList;
    }

    public void writeToFile(String textToAdd, boolean appendFlag) throws IOException {
        FileWriter fw = new FileWriter(filePath, appendFlag);
        fw.write(textToAdd);
        fw.close();

    }

}
