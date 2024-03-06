package daisy.storage;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;

import daisy.tasklist.TaskList;

public class Storage {

    private String storageLocation;

    public Storage(String filePath) {
        this.storageLocation = filePath;
    }

    public void loadData(TaskList tasks) {
        File taskFile = new File(storageLocation);
        System.out.println("Please wait while Daisy loads your previous data!");
        try {
            Scanner entryReader = new Scanner(taskFile);
            while (entryReader.hasNextLine()) {
                String[] entryData = entryReader.nextLine().split(",");
                boolean setDone = "1".equals(entryData[1]);
                switch(entryData[0]) {
                    case "T":
                        tasks.createTodo(entryData[2], false, setDone);
                        break;
                    case "D":
                        tasks.createDeadline(entryData[2], entryData[3], false, setDone);
                        break;
                    case "E":
                        tasks.createEvent(entryData[2], entryData[3], entryData[4], false, setDone);
                        break;
                }
            }
            entryReader.close();
            System.out.println("Data is successfully loaded! Program will now begin.");
        } catch (FileNotFoundException e) {
            System.out.println("No previous data found! Daisy will create a new one for this entry!");
        }
    }

    public void saveData(TaskList tasks) {
        System.out.println("Daisy will begin saving the data for this entry!");
        try {
            FileWriter entryWriter = new FileWriter(storageLocation);
            for (int i = 0; i < tasks.getSize(); i++) {
                String entryInput = tasks.getTask(i).save() + "\n";
                entryWriter.write(entryInput);
            }
            entryWriter.close();
            System.out.println("Successfully saved file. Program will now exit.");
        } catch (IOException e) {
            System.out.println("Error has occurred when saving. This entry will not be saved!");
        }

    }
}
