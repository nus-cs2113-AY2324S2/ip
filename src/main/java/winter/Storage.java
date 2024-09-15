package winter;

import winter.task.Deadline;
import winter.task.Event;
import winter.task.Task;
import winter.task.Todo;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Scanner;

/**
 * Responsible for reading and writing to a file to be stored in memory
 */
public class Storage {
    private static String filePath;

    public Storage(String acceptedFilePath) {
        try {
            filePath = acceptedFilePath;
            readFile();

        } catch (IOException e) {
            System.out.println("There's no file in storage!");
            File winterFile = new File("winter.txt");
            filePath = winterFile.getPath();
        }
    }

    /**
     * Returns an ArrayList representing a list of all the tasks that are stored
     * within the stored file
     * @return List of the tasks stored in memory
     * @throws IOException If the file cannot be read using the Scanner
     */
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
                taskIndex++;
                break;
            case "D":
                taskArrayList.add(new Deadline(taskIndex,Boolean.parseBoolean(inputArray[isMarkedIndex]),

                inputArray[taskNameIndex], LocalDateTime.parse(inputArray[firstTimeIndex])));
                taskIndex++;
                break;
            case "T":

                taskArrayList.add(new Todo(taskIndex,Boolean.parseBoolean(inputArray[isMarkedIndex]),inputArray[taskNameIndex]));
                taskIndex++;

                break;
            default:
                break;
            }

        }
        return taskArrayList;
    }

    /**
     * Write to the file to be stored in memory if any changes to the list was made
     * @param textToAdd The text to be written to the file
     * @param appendFlag true if appending to the file in storage, false if overwriting the file
     * @throws IOException If there was an error writing to the file using the Filewriter
     */
    public void writeToFile(String textToAdd, boolean appendFlag) throws IOException {
        FileWriter fw = new FileWriter(filePath, appendFlag);
        fw.write(textToAdd);
        fw.close();

    }

}
