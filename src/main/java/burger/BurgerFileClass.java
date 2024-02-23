package burger;

import burger.list.List;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Scanner;

public class BurgerFileClass {
    static final int TDEINDEX = 0;
    static final int MARKINDEX = 1;

    static final int TASKINDEX = 2;
    static final String PATHNAME = java.nio.file.Paths.get("data","burger.txt")
              .normalize().toString();

    public static void readFromFile(String filePath, List list) throws IOException {
        Files.createDirectories(Paths.get("data"));
        File f = new File(filePath); // create a File for the given file path
        Scanner s = new Scanner(f); // create a Scanner using the File as the source
        while (s.hasNext()) {
            String[] currLineArray = s.nextLine().split("\\|");
            char currTDE = currLineArray[TDEINDEX].charAt(0);
            char currMark = currLineArray[MARKINDEX].charAt(0);
            String currTask = currLineArray[TASKINDEX];
            list.addFromSaveFile(currTDE, currMark, currTask);
            list.totalTasks++;
        }
    }

    public static void setSaveFile(String filePath, List newList) {
        try {
            FileWriter fw = new FileWriter(filePath);
            int i = 0;
            String textToWrite;
            while (i < newList.totalTasks) {
                textToWrite = newList.getTask(i).getTDE() + "|" + newList.getTask(i).getTick() + "|" + newList.getTask(i).getName();
                fw.write(textToWrite + System.lineSeparator());
                System.out.print(".");
                i++;
            }
            fw.close();
            System.out.println();
            System.out.println("File successfully saved!");
        } catch (IOException e) {
            System.out.println();
            System.out.println("OH NO! File not saved!");
        }

    }

    public static List getSaveFile() {
        List newList = new List();
        try {
            readFromFile(PATHNAME, newList);
            System.out.println("File is found!");
            System.out.println("Current List: ");
            newList.printTaskList();
        } catch (IOException e) {
            System.out.println("File is not found! But we will create one for you!");
        }
        return newList;
    }
}
